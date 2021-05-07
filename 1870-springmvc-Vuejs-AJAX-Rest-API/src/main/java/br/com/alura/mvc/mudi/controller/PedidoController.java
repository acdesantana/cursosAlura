package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.mvc.mudi.controller.request.PedidoRequest;
import br.com.alura.mvc.mudi.enums.StatusPedido;
import br.com.alura.mvc.mudi.service.PedidoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/formulario")
	public ModelAndView formulario() {
		ModelAndView mv = new ModelAndView("/formulario");
		mv.addObject(new PedidoRequest());
		return mv;
	}
	
	@PostMapping("/cadastrar")
	public String cadastrar(@Valid PedidoRequest pedidoRequest, BindingResult result) {
		if(result.hasErrors()) {
			return "/formulario";
		}
		
		pedidoRequest.setStatus(StatusPedido.AGUARDANDO);
		pedidoService.cadastrar(pedidoRequest);
		return "redirect:/home";
	}
}
