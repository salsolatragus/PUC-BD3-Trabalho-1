package br.rio.puc.bd3.t1.ui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ADialogo {

    protected static enum TIPO {
	STRING,
	DATA
    }

    protected static class Caracteristica {
	private final int id;

	private final String nome;

	private final TIPO tipo;

	public Caracteristica(int id, String nome, TIPO tipo) {
	    this.id = id;
	    this.nome = nome;
	    this.tipo = tipo;
	}
    }

    private final String titulo;

    private final Caracteristica[] caracteristicas;

    private final Map<Integer, String> valoresString;

    private final Map<Integer, Date> valoresData;

    public ADialogo(String titulo, Caracteristica[] caracteristicas) {
	this.titulo = titulo;
	this.caracteristicas = caracteristicas;
	valoresString = new HashMap<Integer, String>();
	valoresData = new HashMap<Integer, Date>();
    }

    public Caracteristica[] getCaracteristicas() {
	return caracteristicas;
    }

    public void show() {
	Scanner res = new Scanner(System.in);

	System.out.println("====================");
	System.out.println("= " + titulo);
	System.out.println("====================");

	for (Caracteristica c : caracteristicas) {
	    switch (c.tipo) {
	    case STRING:
		valoresString.put(c.id, leString(c, res));
		break;
	    case DATA:
		valoresData.put(c.id, leData(c, res));
		break;
	    }
	}
    }

    private String leString(Caracteristica c, Scanner res) {
	System.out.print(c.nome + ": ");
	return res.nextLine();
    }

    private Date leData(Caracteristica c, Scanner res) {
	DateFormat dateParser = new SimpleDateFormat("dd.MM.yyyy");
	Date data = null;
	do {
	    System.out.print(c.nome + " (formato dd.mm.aaaa): ");
	    try {
		data = dateParser.parse(res.nextLine());
	    } catch (ParseException pe) {
		System.err.println("Data inválida! Tente novamente...");
	    }
	} while (data == null);
	return data;
    }

    public String getValorString(int cId) {
	return valoresString.get(cId);
    }

    public Date getValorDate(int cId) {
	return valoresData.get(cId);
    }
}
