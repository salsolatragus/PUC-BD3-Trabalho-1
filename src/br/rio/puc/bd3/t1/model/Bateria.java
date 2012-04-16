package br.rio.puc.bd3.t1.model;

public class Bateria {

    private final int id;

    private final Categoria category;

    private final int tipo;

    private final int numero;

    public Bateria(int id, Categoria category, int tipo, int numero) {
	super();
	this.id = id;
	this.category = category;
	this.tipo = tipo;
	this.numero = numero;
    }

    public int getId() {
	return id;
    }

    public Categoria getCategory() {
	return category;
    }

    public int getTipo() {
	return tipo;
    }

    public String getTipoPretty() {
	switch (getTipo()) {
	case 1:
	    return "final";
	case 2:
	    return "semifinal";
	case 3:
	    return "quarta de final";
	case 4:
	    return "selectiva";
	default:
	    return "<tipo desconhecido>";
	}
    }

    public int getNumero() {
	return numero;
    }

    @Override
    public String toString() {
	return getCategory() + ", " + getTipoPretty() + " " + getNumero();
    }
}
