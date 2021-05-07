package br.com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.enums.StatusPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.User;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	List<Pedido> findAll();

	@Cacheable("pedidos")
	List<Pedido> findByStatus(StatusPedido status, Pageable sort);
	
	List<Pedido> findByStatusAndUser(StatusPedido status, User usuarioLogado);

	List<Pedido> findByUser(User usuarioLogado);
	
}
