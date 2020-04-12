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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import comjava.webbanhang.model.ProductDTO;
import comjava.webbanhang.other.ProcessUrlImage;
import comjava.webbanhang.other.UpLoadFile;
import comjava.webbanhang.service.CategoryService;
import comjava.webbanhang.service.ProductService;

@Controller
@RequestMapping(value = "/admin/QuanLySanPham/")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping(value = "/DanhSach")
	public String listProduct(HttpServletRequest request) {
		List<ProductDTO> listProductDTOs = productService.getList();
		request.setAttribute("list", listProductDTOs);
		return "admin/product/list";
	}

	@GetMapping(value = "/ChiTiet/{id}")
	public String detailProduct(HttpServletRequest request, @PathVariable("id") int id) {
		ProductDTO productDTO = productService.getById(id);
		request.setAttribute("product", productDTO);
		return "admin/product/detail";
	}

	@GetMapping(value = "/Them")
	public String addProduct(Model model) {
		ProductDTO productDTO = new ProductDTO();
		model.addAttribute("product", productDTO);
		model.addAttribute("listCategory", categoryService.getList());
		return "admin/product/formAdd";
	}

	@PostMapping(value = "/Them")
	public String addProduct(@ModelAttribute("product") ProductDTO productDTO, @RequestParam("file") MultipartFile file) {
		String urlImage = ProcessUrlImage.processUrlImae(file.getOriginalFilename(), productDTO.getName());
		UpLoadFile.saveFile(file, urlImage);		
		productDTO.setUrlImage(urlImage);
		productService.addOrUpDate(productDTO);
		return "redirect:/admin/QuanLySanPham/DanhSach";
	}

	@GetMapping(value = "/Sua/{id}")
	public String updateProduct(Model model, @PathVariable("id") int id) {
		ProductDTO productDTO = productService.getById(id);
		model.addAttribute("product", productDTO);
		model.addAttribute("listCategory", categoryService.getList());
		return "admin/product/formUpdate";
	}

	@PostMapping(value = "/Sua")
	public String updateProduct(@ModelAttribute("product") ProductDTO productDTO, @RequestParam("file") MultipartFile file) {
		UpLoadFile.deleteFile(productService.getById(productDTO.getId()).getUrlImage());
		String urlImage = ProcessUrlImage.processUrlImae(file.getOriginalFilename(), productDTO.getName());
		UpLoadFile.saveFile(file, urlImage);	
		productDTO.setUrlImage(urlImage);
		productService.addOrUpDate(productDTO);
		return "redirect:/admin/QuanLySanPham/DanhSach";
	}

	@GetMapping(value = "/Xoa/{id}")
	public String deleteProduct(@PathVariable("id") int id) {
		ProductDTO productDTO = productService.getById(id);
		UpLoadFile.deleteFile(productDTO.getUrlImage());
		productService.delete(id);
		return "redirect:/admin/QuanLySanPham/DanhSach";
	}

}
