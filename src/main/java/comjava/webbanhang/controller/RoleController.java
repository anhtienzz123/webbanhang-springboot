package comjava.webbanhang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import comjava.webbanhang.model.RoleDTO;
import comjava.webbanhang.service.RoleService;

@Controller
@RequestMapping(value = "/admin/QuanLyVaiTro")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping(value = "/DanhSach")
	public String list(HttpServletRequest request) {
		List<RoleDTO> roleDTOs = roleService.getList();
		request.setAttribute("list", roleDTOs);
		return "admin/role/list";
	}

	@GetMapping(value = "/ChiTiet/{id}")
	public String detail(HttpServletRequest request, @PathVariable("id") int id) {
		RoleDTO roleDTO = roleService.getById(id);
		request.setAttribute("role", roleDTO);
		return "admin/role/detail";
	}

	@GetMapping(value = "/Them")
	public String add(Model model) {
		RoleDTO roleDTO = new RoleDTO();
		model.addAttribute("role", roleDTO);
		return "admin/role/formAdd";
	}

	@PostMapping(value = "/Them")
	public String add(Model model, @ModelAttribute("role") RoleDTO roleDTO) {
		roleService.addOrUpdate(roleDTO);
		return "redirect:/admin/QuanLyVaiTro/DanhSach";
	}

	@GetMapping(value = "/Sua/{id}")
	public String update(Model model, @PathVariable("id") int id) {
		RoleDTO roleDTO = roleService.getById(id);
		model.addAttribute("role", roleDTO);
		return "admin/role/formUpdate";
	}

	@PostMapping(value = "/Sua")
	public String update(Model model, @ModelAttribute("role") RoleDTO roleDTO) {
		roleService.addOrUpdate(roleDTO);
		return "redirect:/admin/QuanLyVaiTro/DanhSach";
	}

	@GetMapping(value = "/Xoa/{id}")
	public String delete(@PathVariable("id") int id) {
		roleService.delete(id);
		return "redirect:/admin/QuanLyVaiTro/DanhSach";
	}
}
