package comjava.webbanhang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import comjava.webbanhang.model.ProductDTO;
import comjava.webbanhang.service.ProductService;

@Controller
public class SearchController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value = "/searchInput")
	public String search(HttpServletRequest request, @RequestParam("name") String name) {
		String result = "%"+name+"%";
		
		List<ProductDTO> productDTOs = productService.getListByNameLike(result);
		request.setAttribute("listProduct", productDTOs);
		
		return "user/searchInput";
	}
	
	@GetMapping(value = "/searchProductSort")
	public String selectProductSort(HttpServletRequest request, @RequestParam("id") int id) {
		
		
		List<ProductDTO> productDTOs = productService.getListOrderByPrice(id);
		request.setAttribute("listProduct", productDTOs);
		
		return "user/selectProductSort";
	}

}
