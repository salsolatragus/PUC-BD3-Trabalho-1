package br.rio.puc.bd3.t1.model;

import java.util.Date;

public class Competidor {

    private final int id;

    private final String nome;

    private final String nacionalidade;

    private final Date dataDeNacimento;

    public Competidor(int id, String nome, String nacionalidade,
	    Date dataDeNacimento) {
	this.id = id;
	this.nome = nome;
	this.nacionalidade = nacionalidade;
	this.dataDeNacimento = dataDeNacimento;
    }

    public Competidor(String nome, String nacionalidade, Date dataDeNacimento) {
	this(-1, nome, nacionalidade, dataDeNacimento);
    }

    public int getId() {
	return id;
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
