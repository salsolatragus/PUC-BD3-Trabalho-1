package br.rio.puc.bd3.t1.model;

import java.util.Date;

public class Categoria {

    private final int id;

    private final String nome;

    private final Date dataSelectivas;

    private final Date dataQuartasDeFinal;

    private final Date dataSemifinais;

    private final Date dataFinal;

    public Categoria(String nome, Date dataSelectivas, Date dataQuartasDeFinal,
	    Date dataSemifinais, Date dataFinal) {
	this(-1, nome, dataSelectivas, dataQuartasDeFinal, dataSemifinais,
		dataFinal);
    }

    public Categoria(int id, String nome, Date dataSelectivas,
	    Date dataQuartasDeFinal,
	    Date dataSemifinais, Date dataFinal) {
	this.id = id;
	this.nome = nome;
	this.dataSelectivas = dataSelectivas;
	this.dataQuartasDeFinal = dataQuartasDeFinal;
	this.dataSemifinais = dataSemifinais;
	this.dataFinal = dataFinal;
    }

    public int getId() {
	return id;
    }

    public String getNome() {
	return nome;
    }

    public Date getDataSelectivas() {
	return dataSelectivas;
    }

    public Date getDataQuartasDeFinal() {
	return dataQuartasDeFinal;
    }

    public Date getDataSemifinais() {
	return dataSemifinais;
    }

    public Date getDataFinal() {
	return dataFinal;
    }

    @Override
    public String toString() {
	return "Categoria " + getNome();
    }
}
