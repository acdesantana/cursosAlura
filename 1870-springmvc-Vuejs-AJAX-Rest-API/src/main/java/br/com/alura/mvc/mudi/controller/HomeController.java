package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.mvc.mudi.enums.StatusPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.service.PedidoService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public ModelAndView home() {
		List<Pedido> pedidos = pedidoService.listarPedidosEntregues();
		
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("pedidos", pedidos);
		
		return modelAndView; 
	}

}
