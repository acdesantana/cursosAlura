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
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/pedido")
	public ModelAndView home(Principal principal) {
		String usuarioLogado = principal.getName();
		List<Pedido> pedidos = pedidoService.listarTodosPedidosPorUsuario(new User(usuarioLogado));
		
		ModelAndView modelAndView = new ModelAndView("home"); // home.html
		modelAndView.addObject("pedidos", pedidos);
		
		return modelAndView; 
	}

	@GetMapping("/pedido/{status}")
	public /*ModelAndView*/ String filtroStatus(@PathVariable(value = "status") String status, Model model, Principal principal) {
		String usuarioLogado = principal.getName();
		List<Pedido> pedidos = pedidoService.listarPorStatusAndUsuario(StatusPedido.valueOf(status.toUpperCase()),new User(usuarioLogado));
		
//		ModelAndView modelAndView = new ModelAndView("home");
//		modelAndView.addObject("pedidos", pedidos);
//		modelAndView.addObject("status", status);
//		return modelAndView; 
		model.addAttribute("pedidos",pedidos);
		model.addAttribute("status", status);
		return "forward:/usuario/pedido"; // Redirecionamento server-side: o fluxo volta apenas para o Front-Controller do Spring MVC e ele chama a nova action. Tudo isso dentro da mesma requisição HTTP.
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/usuario/pedido"; //	Seguindo a regra: "always redirect after post"! Redirecionamento client-side. Isto é, ele devolve uma resposta HTTP para pedir uma nova requisição para a URL /home.
	}
}
