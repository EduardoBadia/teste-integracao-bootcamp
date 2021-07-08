package br.com.zup.bootcamp.dao;

import java.util.Collection;

import javax.persistence.EntityManager;

import br.com.zup.bootcamp.modelo.Resposta;

public class RespostaRepository {

	private EntityManager manager;

	public RespostaRepository(EntityManager manager) {
		this.manager = manager;
	}

	public Collection<Resposta> buscaRespostas(Long idAluno) {
		return manager.createQuery("select r from Resposta r where r.aluno.id = :idAluno", Resposta.class)
				.setParameter("idAluno", idAluno).getResultList();
	}
}
