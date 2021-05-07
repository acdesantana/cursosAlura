package br.com.alura.forum.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.alura.forum.modelo.Curso;


//@SpringBootTest // nao precisa desta annotation aqui.
@RunWith(SpringRunner.class)
@DataJpaTest //especifica para teste de Repository
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Configuracao para usar outro banco para os testes que não seja o H2.
@ActiveProfiles("test")
public class CursoRepositoryTest {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	public void deveriaCarregarUmCursoAoBuscarPeloSeuNome() {
		String nomeCurso = "HTML 5";
		Curso html5 = new Curso();
		html5.setNome(nomeCurso);
		html5.setCategoria("Programacao");
		em.persist(html5);
		
		Curso curso = cursoRepository.findByNome(nomeCurso);
		Assert.assertNotNull(curso);
		Assert.assertEquals(nomeCurso, curso.getNome());
	}

	@Test
	public void NaoDeveriaCarregarCursoNaoCadastrado() {
		String nomeCurso = "JPA";
		Curso curso = cursoRepository.findByNome(nomeCurso);
		Assert.assertNull(curso);
	}
}
