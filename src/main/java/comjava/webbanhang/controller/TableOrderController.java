package comjava.webbanhang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import comjava.webbanhang.model.TableOrderDTO;
import comjava.webbanhang.service.TableOrderService;

@Controller
@RequestMapping(value = "/admin/QuanLyDonHang")
public class TableOrderController {
	
	
	@Autowired
	private TableOrderService tableOrderService;
	
	@GetMapping(value = "/DanhSach")
	public String list(HttpServletRequest request) {
		List<TableOrderDTO> tableOrderDTOs = tableOrderService.getListByActive(true);
		request.setAttribute("tableOrderDTOs", tableOrderDTOs);
		return "admin/tableorder/list";
	}
	
	@GetMapping(value = "/Xoa/{id}")
	public String delete(@PathVariable("id") int id) {		
		tableOrderService.delete(id);
		return "redirect:/admin/QuanLyDonHang/DanhSach";
	}
	
	@GetMapping(value = "/GiaoHang/{id}")
	public String checked(@PathVariable("id") int id) {		
		TableOrderDTO tableOrderDTO = tableOrderService.getById(id);
		tableOrderDTO.setChecked(true);
		tableOrderService.addOrUpdate(tableOrderDTO);
		return "redirect:/admin/QuanLyDonHang/DanhSach";
	}
}
