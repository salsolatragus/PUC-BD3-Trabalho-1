package br.rio.puc.bd3.t1.ui;

import java.util.Date;

import br.rio.puc.bd3.t1.model.Categoria;

public class Dialogo_Nova_Categoria extends ADialogo {

    public static final int NOME = 1;

    public static final int DATA_SELECTIVAS = 2;

    public static final int DATA_QUARTAS_DE_FINAL = 3;

    public static final int DATA_SEMIFINAIS = 4;

    public static final int DATA_FINAL = 5;

    public Dialogo_Nova_Categoria() {
	super(
		"Entre com os dados da Categoria",
		new Caracteristica[] {
			new Caracteristica(NOME, "Nome", TIPO.STRING),
			new Caracteristica(
				DATA_SELECTIVAS,
				"Data de selectivas",
				TIPO.DATA),
			new Caracteristica(
				DATA_QUARTAS_DE_FINAL,
				"Data de quartas de final (deve ser depois selectivas)",
				TIPO.DATA),
			new Caracteristica(
				DATA_SEMIFINAIS,
				"Data de semifinais (deve ser depois quartas de final)",
				TIPO.DATA),
			new Caracteristica(
				DATA_FINAL,
				"Data de final (deve ser depois semifinais)",
				TIPO.DATA)
		});
    }

    public Categoria getCategoria() {
	String nome = getValorString(NOME);
	Date selectivas = getValorDate(DATA_SELECTIVAS);
	Date quartas = getValorDate(DATA_QUARTAS_DE_FINAL);
	Date semifinais = getValorDate(DATA_SEMIFINAIS);
	Date ofinal = getValorDate(DATA_FINAL);
	return new Categoria(nome, selectivas, quartas, semifinais, ofinal);
    }
}
