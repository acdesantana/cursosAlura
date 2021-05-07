package br.com.alura.spring.data.service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {
	
	private Boolean system = true;
	private final CargoRepository cargoRepository;
	
	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	
	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual acao voce quer executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Novo Cargo");
			System.out.println("2 - Atualizar Cargo");
			System.out.println("3 - Listar todos");
			System.out.println("4 - Excluir");
			
			int action = scanner.nextInt();
			if(action == 1) {
				salvar(scanner);
				
			}else if(action == 2) {
				atualizar(scanner);
				
			}else if(action == 3) {
				listar();
			
			}else if(action == 4) {
				excluir(scanner);
				
			}else {
				system = false;
			}
		}
	}
	
	private void salvar(Scanner scanner) {
		System.out.println("Descricao do cargo");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao.toUpperCase());
		cargoRepository.save(cargo);
		System.out.println("Registro salvo");
		System.out.println("");
	}
	
	private void atualizar(Scanner scanner) {
		try {
			System.out.println("Buscar registro pelo Id");
			Integer id = Integer.parseInt(scanner.next());
			Optional<Cargo> opt = cargoRepository.findById(id);
			
			Cargo cargo = opt.get();
			
			System.out.println("Nova descricao do cargo");
			String descricao = scanner.next();
			cargo.setDescricao(descricao.toUpperCase());
			cargoRepository.save(cargo);
			System.out.println("Registro atualizado");
			System.out.println("");
			
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		
			System.out.println("Id nao encontrado!");
			System.out.println("");
		}
	}

	private void listar(){
		System.out.println("Listando todos os cargos");
		Iterable<Cargo> cargos = cargoRepository.findAll();
		
		cargos.forEach(cargo -> System.out.println(cargo.toString()));
		System.out.println("");
	}
	
	private void excluir(Scanner scanner) {
		System.out.println("Excluir registro pelo Id");
		cargoRepository.deleteById(scanner.nextInt());
		System.out.println("Registro excluído");
		System.out.println("");
	}
	
}
