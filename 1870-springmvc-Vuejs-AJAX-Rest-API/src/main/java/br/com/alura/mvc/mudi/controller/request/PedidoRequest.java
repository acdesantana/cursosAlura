package br.com.alura.mvc.mudi.controller.request;

import javax.validation.constraints.NotBlank;

import br.com.alura.mvc.mudi.enums.StatusPedido;

public class PedidoRequest {

	//link de todas anotacoes: https://docs.jboss.org/hibernate/beanvalidation/spec/2.0/api/javax/validation/constraints/package-summary.html
	//NotBlank.pedidoRequest.nomeProduto=não pode estar em branco. Msg específicas estao configuradas no messages.properties
	@NotBlank private String nomeProduto;
	@NotBlank private String urlProduto;
	@NotBlank private String urlImagem;
	@NotBlank private String descricao;
	private StatusPedido status;
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getUrlProduto() {
		return urlProduto;
	}
	public void setUrlProduto(String urlProduto) {
		this.urlProduto = urlProduto;
	}
	public String getUrlImagem() {
		return urlImagem;
	}
	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public StatusPedido getStatus() {
		return status;
	}
	public void setStatus(StatusPedido status) {
		this.status = status;
	}
	
}
