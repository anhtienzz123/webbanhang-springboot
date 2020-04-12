package comjava.webbanhang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import comjava.webbanhang.model.CategoryDTO;
import comjava.webbanhang.model.ProductDTO;
import comjava.webbanhang.service.CategoryService;
import comjava.webbanhang.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value = "/")
	public String index() {
		return "redirect:/home";
	}
	
	@GetMapping(value = "/home")
	public String home(HttpServletRequest request) {
		List<CategoryDTO> categoryDTOs = categoryService.getList();
		request.setAttribute("listCategory", categoryDTOs);
		
		int page = 1;
		request.setAttribute("page", page);
        List<ProductDTO> productDTOs = productService.getList();
		request.setAttribute("listProduct", productDTOs);		
			   
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(!authentication.getName().equalsIgnoreCase("anonymousUser")) {
			request.setAttribute("authentication", authentication.getName());
		}
		return "home";	
	}
	
	@GetMapping(value = "/home/pages")
	public String homePage(HttpServletRequest request, @RequestParam("page")int page) {				
		List<CategoryDTO> categoryDTOs = categoryService.getList();
		request.setAttribute("listCategory", categoryDTOs);
				
		List<ProductDTO> productDTOs = productService.getList(page,15);
		request.setAttribute("listProduct", productDTOs);
		
		
		request.setAttribute("page", page);
		return "home";		
	}
	
	@GetMapping(value = "/home/{category}")
	public String home(HttpServletRequest request, @PathVariable("category") String nameCategory) {
		List<CategoryDTO> categoryDTOs = categoryService.getList();
		request.setAttribute("listCategory", categoryDTOs);
			
        List<ProductDTO> productDTOs = categoryService.getByName(nameCategory).getProductDTOs();
		request.setAttribute("listProduct", productDTOs);
		return "home";	
	}
	
	@GetMapping(value = "/403")
	public String err403() {
		return "403";
	}
	
	@GetMapping(value = "/admin")
	public String home() {
		return "admin";
	}
}
