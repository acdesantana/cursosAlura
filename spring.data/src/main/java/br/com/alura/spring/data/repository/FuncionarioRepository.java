package br.com.alura.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends JpaSpecificationExecutor<Funcionario>, PagingAndSortingRepository<Funcionario, Integer> 
																								/* CrudRepository<Funcionario, Integer>  esta nao tem paginacao USO COM MARIADB */
																								/* MongoRepository USO COM MONGODB */{
	
	//Derived Queries: o JPQL é gerado dinamicamente (ou derivado) baseado no nome do método
	List<Funcionario> findByNome(String nome);
	List<Funcionario> findByNomeLike(String nome); // O valor do parâmetro nome deve usar o pattern, p ex: String nome = "%maria%";
	List<Funcionario> findByNomeEndingWith(String nome);
	List<Funcionario> findByNomeStartingWith(String nome);
	List<Funcionario> findByNomeIsNull();
	List<Funcionario> findByNomeIsNotNull();
	List<Funcionario> findByNomeOrderByNomeAsc(String nome);
	//List<Funcionario> findByUnidadeTrabalhos_Descricao(String descricao); // deve ter underline!!
	
	//JPQL: Java Persistence Query Language
	@Query("select f from Funcionario f where f.nome = :nome and f.salario >= :salario and f.dataContratacao = :dataContratacao")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate dataContratacao);
	@Query("SELECT f FROM Funcionario f JOIN f.cargo c WHERE c.descricao = :descricao")
	List<Funcionario> findByCargoPelaDescricao(String descricao);
	@Query("SELECT f FROM Funcionario f JOIN f.unidades u WHERE u.descricao = :descricao")
	List<Funcionario> findByUnidadeTrabalhos_Descricao(String descricao); //nomenclatura fora do padrão Java. Isso é uma desvantagem dos Derived Query Methods.
	
	//NativeQuery: sql puro
	@Query(value = "select * from funcionarios f where f.data_contratacao >= :data", nativeQuery = true)
	List<Funcionario> findDataContratacaoMaior(LocalDate data);
	
	@Query(value = "select f.id, f.nome, f.salario from funcionarios f", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();
}
