package br.com.alura.forum.controller.dto;

public class TokenDTO {

	private String token;
	private String tipo;
	
	public TokenDTO(String tokenDTO, String tipo) {
		this.token = tokenDTO;
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
