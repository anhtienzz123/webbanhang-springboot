package comjava.webbanhang.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import comjava.webbanhang.model.AccountDTO;
import comjava.webbanhang.service.AccountService;

@Controller
@RequestMapping(value = "/forgotpassword")
public class ForgotPasswordController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping(value = "/")
	public String forgotPassword() {
		return "user/forgotPassword";
	}

	@GetMapping(value = "/sendLinkValidate")
	public String sendLinkValidate(HttpServletRequest request,
			@RequestParam(name = "email", required = true) String email) {
		AccountDTO accountDTO = accountService.getByEmail(email);

		if (accountDTO == null) {
			request.setAttribute("erremail", "Email không tồn tại");
			return "user/forgotPassword";
		}

		String key = RandomStringUtils.randomAlphanumeric(20);
		accountDTO.setKey(key);
		accountService.addOrUpdate(accountDTO);

		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		String to = email;
		String subject = "Đặt lại mật khẩu";
		String text = "Nhấn vào đây để đặt lại mật khẩu:http://localhost:8080/forgotpassword/validate?email=" + email
				+ "&key=" + key;
	    simpleMessage.setTo(to);
	    simpleMessage.setSubject(subject);
	    simpleMessage.setText(text);
	    javaMailSender.send(simpleMessage);

		return "redirect:/login";
	}

	@GetMapping(value = "/validate")
	public String validate(HttpServletRequest request, @RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "key", required = true) String key) {
		AccountDTO accountDTO = accountService.getByEmail(email);
		if (accountDTO.getKey().equals(key)) {
           request.setAttribute("id", accountDTO.getId());
           return "user/recoverPassword";
		}

		return "404";
	}

	@PostMapping(value = "/validate")
	public String validate(HttpServletRequest request, @RequestParam(value = "id", required = true) int id,
			                @RequestParam(value = "password", required = true) String password
			               ) {
		AccountDTO  accountDTO = accountService.getById(id);
		
		accountDTO.setPw(password);
		accountDTO.setPassword(passwordEncoder.encode(password));
		
		accountService.addOrUpdate(accountDTO);
	
		return "redirect:/login";
	}
}
