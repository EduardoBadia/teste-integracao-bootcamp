package br.com.zup.bootcamp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.zup.bootcamp.modelo.Aluno;
import br.com.zup.bootcamp.modelo.Resposta;

public class RespostaTest {
	
	private EntityManager em;
	private AlunoDao alunoDao;
	
	@Before
	public void antes()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("treinamento");
		em = emf.createEntityManager();
		
		alunoDao = new AlunoDao(em);
		em.getTransaction().begin();
	}
	
	@After
	public void depois()
	{
		em.getTransaction().rollback();
		em.close();
	}
	
	
	@Test
	public void deveRetornarNenhumaRespostaAoBuscar()
	{
		Aluno aluno1 = new AlunoBuilder()
				 .comNome("eduardo")
				 .comEmail("eduardo@email.com")
				 .comRespostas("resposta do curso de java")
				 .comRespostas("resposta do curso de HTTP")
				 .comRespostas("resposta do curso de SQL")
				 .constroi();
		
		alunoDao.salvar(aluno1);
		
		RespostaRepository rr = new RespostaRepository(em);
		
		List<Resposta> respostas = (List<Resposta>) rr.buscaRespostas(-1L);
		
		assertTrue(respostas.isEmpty());
	}
	
	@Test
	public void deveRetornarTodasAsRespostasAoBuscar()
	{	
		Aluno aluno1 = new AlunoBuilder()
				 .comNome("eduardo")
				 .comEmail("eduardo@email.com")
				 .comRespostas("resposta do curso de java")
				 .comRespostas("resposta do curso de HTTP")
				 .comRespostas("resposta do curso de SQL")
				 .constroi();
		
		alunoDao.salvar(aluno1);
		
		RespostaRepository rr = new RespostaRepository(em);
		
		List<Resposta> respostas = (List<Resposta>) rr.buscaRespostas(aluno1.getId());
		
		assertEquals(3, respostas.size());
		assertEquals("resposta do curso de java", respostas.get(0).getMensagem());
		assertEquals("resposta do curso de HTTP", respostas.get(1).getMensagem());
		assertEquals("resposta do curso de SQL", respostas.get(2).getMensagem());
	}
	
	@Test
	public void deveRetornarNenhumaRespostaAposApagarIdAluno()
	{
		Aluno aluno1 = new AlunoBuilder()
				 .comNome("eduardo")
				 .comEmail("eduardo@email.com")
				 .comRespostas("resposta do curso de java")
				 .comRespostas("resposta do curso de HTTP")
				 .comRespostas("resposta do curso de SQL")
				 .constroi();
		
		alunoDao.salvar(aluno1);
		alunoDao.apagar(aluno1);
		
		RespostaRepository rr = new RespostaRepository(em);
		
		List<Resposta> respostas = (List<Resposta>) rr.buscaRespostas(aluno1.getId());
		
		assertTrue(respostas.isEmpty());
	}
	
	@Test 
	public void deveRetornarRespostasAposAtualizarDadosDoAluno()
	{
		Aluno aluno1 = new AlunoBuilder()
				 .comNome("eduardo")
				 .comEmail("eduardo@email.com")
				 .comRespostas("resposta do curso de java")
				 .comRespostas("resposta do curso de HTTP")
				 .comRespostas("resposta do curso de SQL")
				 .constroi();
		
		alunoDao.salvar(aluno1);
		
		aluno1.setNome("felipe");
		aluno1.setEmail("felipe@gmail.com");
		
		alunoDao.atualizar(aluno1);
		
		RespostaRepository rr = new RespostaRepository(em);
		
		List<Resposta> respostas = (List<Resposta>) rr.buscaRespostas(aluno1.getId());
		
		assertEquals(3, respostas.size());
		assertEquals("resposta do curso de java", respostas.get(0).getMensagem());
		assertEquals("resposta do curso de HTTP", respostas.get(1).getMensagem());
		assertEquals("resposta do curso de SQL", respostas.get(2).getMensagem());
	}
	
}
