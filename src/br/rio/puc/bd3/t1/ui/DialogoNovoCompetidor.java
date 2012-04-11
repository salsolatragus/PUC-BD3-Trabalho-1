package br.rio.puc.bd3.t1.ui;

import java.util.Date;

import br.rio.puc.bd3.t1.model.Competidor;

public class DialogoNovoCompetidor extends ADialogo {

    public static final int NOME = 1;

    public static final int NACIONALIDADE = 2;

    public static final int NACIMENTO = 3;

    public DialogoNovoCompetidor() {
	super("Entre com os dados de competidor",
		new Caracteristica[] {
			new Caracteristica(NOME, "Nome", TIPO.STRING),
			new Caracteristica(
				NACIONALIDADE,
				"Nacionalidade",
				TIPO.STRING),
			new Caracteristica(
				NACIMENTO,
				"Data de nacimento",
				TIPO.DATA)
		});
    }

    public Competidor getCompetidor() {
	String nome = getValorString(NOME);
	String nacionalidade = getValorString(NACIONALIDADE);
	Date nacimento = getValorDate(NACIMENTO);
	return new Competidor(nome, nacionalidade, nacimento);
    }
}
