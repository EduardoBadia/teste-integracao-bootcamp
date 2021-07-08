package br.com.zup.bootcamp.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.zup.bootcamp.modelo.Aluno;
import br.com.zup.bootcamp.modelo.Resposta;

public class AlunoBuilder {

	private String nome; 
	private String email;
	private List<Resposta> respostas;
	
	public AlunoBuilder()
	{
		respostas = new ArrayList<Resposta>();
	}
	
	public AlunoBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public AlunoBuilder comEmail(String email) {
		this.email = email;
		return this;
	}
	
	public AlunoBuilder comRespostas(String mensagem) {
		
		this.respostas.add(new Resposta(mensagem));
		return this;
	}
	
	public Aluno constroi() {
		
		Aluno aluno = new Aluno(nome, email);
		
		for (Resposta resposta : this.respostas) {
            
		     aluno.adicionaResposta(resposta);
        }
		
		return aluno;
	}
}
