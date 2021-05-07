package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosServices {

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private FuncionarioRepository funcionarioRepository;
	
	public RelatoriosServices(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual relatorio deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca funcionario nome");
			System.out.println("2 - Busca funcionario nome, data contratacao, Salario Maior");
			System.out.println("3 - Busca funcionario por data contratacao");
			System.out.println("4 - Pesquisa funcionario salario ");
			
			int action = scanner.nextInt();
			if(action == 1) {
				buscaFuncionarioPorNome(scanner);
				
			}else if(action == 2) {
				buscarFuncionarioNomeSalarioMaiorData(scanner);
				
			}else if(action == 3) {
				buscaFuncionarioDataContratacao(scanner);
				
			}else if(action == 4) {
				pesquisaFuncionarioSalario();
				
			}else {
				system = false;
			}
		}
	}
	
	private void buscaFuncionarioPorNome(Scanner scanner) {
		System.out.println("Digite nome para pesquisar");
		String name = scanner.next();
		List<Funcionario> list = funcionarioRepository.findByNome(name);
		list.forEach(System.out::println);
		System.out.println();
	}
	
	private void buscarFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Digite nome para pesquisar");
		String nome = scanner.next();
		
		System.out.println("Digite data contratacao para pesquisar");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		System.out.println("Digite salario para pesquisar");
		Double salario = scanner.nextDouble();
		
		List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("Digite data contratacao para pesquisar");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
		list.forEach(System.out::println);
	}

	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario id:"+f.getId()+ " | nome: "+f.getNome() + " | salario: "+f.getSalario()));
	}
	
}
