package comjava.webbanhang.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import comjava.webbanhang.model.AccountDTO;
import comjava.webbanhang.service.AccountService;

@Controller
public class RegisterController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JavaMailSender javaMailSender;

	@GetMapping(value = "/register")
	public String register(Model model) {
		AccountDTO accountDTO = new AccountDTO();
		model.addAttribute("account", accountDTO);
		return "user/formRegister";
	}

	@PostMapping(value = "/register")
	public String register(HttpServletRequest request, @ModelAttribute(name = "account") AccountDTO accountDTO) {
		AccountDTO account = accountService.getByUsernameOrEmail(accountDTO.getUsername(), accountDTO.getEmail());

		if (account == null) {
			accountDTO.setPw(accountDTO.getPassword());
			accountDTO.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
			List<String> roles = new ArrayList<String>();
			roles.add("User");
			accountDTO.setActive(false);
			accountDTO.setRoles(roles);

			String key = RandomStringUtils.randomAlphanumeric(10);
			accountDTO.setKey(key);

			accountService.addOrUpdate(accountDTO);

			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			String to = accountDTO.getEmail();
			String subject = "Xác thực email";
			String text = "Link xác nhận:http://localhost:8080/XacNhanEmail?email=" + accountDTO.getEmail() + "&key="
					+ key;
			simpleMailMessage.setTo(to);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(text);
			javaMailSender.send(simpleMailMessage);

			request.setAttribute("status", "Đăng kí thành công");
		} else {
			if (account.getUsername().equalsIgnoreCase(accountDTO.getUsername())) {
				request.setAttribute("errusername", "Tài khoản đã tồn tại");
			}

			if (account.getEmail().equalsIgnoreCase(accountDTO.getEmail())) {
				request.setAttribute("erremail", "Email đã tồn tại");
			}

			request.setAttribute("status", "Đăng kí thất bại");
		}

		return "user/formRegister";
	}

	@GetMapping(value = "/XacNhanEmail")
	public String validateEmail(@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "key", required = true) String key) {

		AccountDTO accountDTO = accountService.getByEmail(email);
		if (accountDTO.getKey().equals(key)) {
			accountDTO.setActive(true);
			accountService.addOrUpdate(accountDTO);
		}
		return "redirect:/home";
	}

}
