package br.rio.puc.bd3.t1.model;

import java.util.Date;

public class Competidor {

    private final String nome;

    private final String nacionalidade;

    private final Date dataDeNacimento;

    public Competidor(String nome, String nacionalidade, Date dataDeNacimento) {
	super();
	this.nome = nome;
	this.nacionalidade = nacionalidade;
	this.dataDeNacimento = dataDeNacimento;
    }

    public String getNome() {
	return nome;
    }

    public String getNacionalidade() {
	return nacionalidade;
    }

    public Date getDataDeNacimento() {
	return dataDeNacimento;
    }
}
