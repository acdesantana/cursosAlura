package br.com.alura.mvc.mudi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.alura.mvc.mudi.controller.request.PedidoRequest;
import br.com.alura.mvc.mudi.enums.StatusPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Pedido> listarTodosPedidos() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		return pedidos;
	}

	public void cadastrar(PedidoRequest pedidoRequest) {
		Pedido pedido = new Pedido(pedidoRequest.getNomeProduto(), pedidoRequest.getUrlProduto(), pedidoRequest.getUrlImagem(), pedidoRequest.getDescricao(), pedidoRequest.getStatus());
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User usuario = userRepository.findByUsername(username);
		
		pedido.setUser(usuario);
		pedidoRepository.save(pedido);
	}

	public List<Pedido> listarPorStatusAndUsuario(StatusPedido status, User usuarioLogado) {
		List<Pedido> pedidos = pedidoRepository.findByStatusAndUser(status, usuarioLogado);
		return pedidos;
	}

	public List<Pedido> listarPedidosEntregues() {
		Sort sort = Sort.by("dataDaEntrega").descending();
		PageRequest paginacao = PageRequest.of(0, 10, sort); // 0 = primeira pagina
		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.ENTREGUE, paginacao);
		return pedidos;
	}
	
	public List<Pedido> listarTodosPedidosPorUsuario(User usuarioLogado) {
		List<Pedido> pedidos = pedidoRepository.findByUser(usuarioLogado);
		return pedidos;
	}

}
