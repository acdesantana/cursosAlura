package br.com.alura.spring.data.orm;

/**
 * 
 * Substitui a boa e velha classe DTO, onde é possivel ter metodo toString()
 *
 */
public interface FuncionarioProjecao {

	Integer getId();
	String getNome();
	Double getSalario();
	
}
