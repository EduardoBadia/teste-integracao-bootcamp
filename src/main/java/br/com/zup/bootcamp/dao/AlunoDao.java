package br.com.zup.bootcamp.dao;

import javax.persistence.EntityManager;

import br.com.zup.bootcamp.modelo.Aluno;

public class AlunoDao {

	private EntityManager em;

	public AlunoDao(EntityManager em) {
		super();
		this.em = em;
	}
	
	public void salvar(Aluno aluno)
	{	
		em.persist(aluno);
	}
	
	public void apagar(Aluno aluno)
	{	
		em.remove(aluno);
	}
	
	public void atualizar(Aluno aluno)
	{
		em.merge(aluno);
	}
}
