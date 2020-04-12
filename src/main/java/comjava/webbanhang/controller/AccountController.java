package comjava.webbanhang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import comjava.webbanhang.model.AccountDTO;
import comjava.webbanhang.other.ProcessUrlImage;
import comjava.webbanhang.other.UpLoadFile;
import comjava.webbanhang.service.AccountService;
import comjava.webbanhang.service.RoleService;

@Controller
@RequestMapping(value = "/admin/QuanLyTaiKhoan")
public class AccountController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private AccountService accountService;

	@GetMapping(value = "/DanhSach")
	public String list(HttpServletRequest request) {
		List<AccountDTO> accountDTOs = accountService.getList();
		request.setAttribute("list", accountDTOs);
		return "admin/account/list";
	}

	@GetMapping(value = "/ChiTiet/{id}")
	public String detail(HttpServletRequest request, @PathVariable(name = "id") int id) {

		AccountDTO accountDTO = accountService.getById(id);
		
		request.setAttribute("account", accountDTO);
		
		return "admin/account/detail";
	}
	
	@GetMapping(value = "/Them")
	public String add(Model model) {
		AccountDTO accountDTO = new AccountDTO();

		model.addAttribute("account", accountDTO);
        model.addAttribute("roles", roleService.getList());
		return "admin/account/formAdd";
	}

	@PostMapping(value = "/Them")
	public String add(@ModelAttribute(name = "account") AccountDTO accountDTO, @RequestParam("file")MultipartFile multipartFile) {
		String urlImage = ProcessUrlImage.processUrlImae(multipartFile.getOriginalFilename(), accountDTO.getUsername());
		UpLoadFile.saveFile(multipartFile, urlImage);
		
		accountDTO.setAvatar(urlImage);
		accountDTO.setPw(accountDTO.getPassword());
		accountDTO.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
		accountService.addOrUpdate(accountDTO);
		return "redirect:/admin/QuanLyTaiKhoan/DanhSach";
	}

	@GetMapping(value = "/Sua/{id}")
	public String update(Model model, @PathVariable(name = "id") int id) {
		AccountDTO accountDTO = accountService.getById(id);

		model.addAttribute("account", accountDTO);
		model.addAttribute("roles", roleService.getList());
		return "admin/account/formUpdate";
	}

	@PostMapping(value = "/Sua")
	public String update(@ModelAttribute(name = "account") AccountDTO accountDTO, @RequestParam("file") MultipartFile multipartFile) {
		UpLoadFile.deleteFile(accountService.getById(accountDTO.getId()).getAvatar());
		
		String urlImage = ProcessUrlImage.processUrlImae(multipartFile.getOriginalFilename(), accountDTO.getUsername());
		UpLoadFile.saveFile(multipartFile, urlImage);
		
		accountDTO.setAvatar(urlImage);
		
		accountDTO.setPassword(passwordEncoder.encode(accountDTO.getPw()));
		accountService.addOrUpdate(accountDTO);

		return "redirect:/admin/QuanLyTaiKhoan/DanhSach";
	}

	@GetMapping(value = "/Xoa/{id}")
	public String delete(HttpServletRequest request, @PathVariable(name = "id") int id) {

		accountService.delete(id);

		return "redirect:/admin/QuanLyTaiKhoan/DanhSach";
	}
}
