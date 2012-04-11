package br.rio.puc.bd3.t1.model;

public class Participacao {

    private final int categoria;

    private final int competidor;

    public Participacao(int categoria, int competidor) {
	this.categoria = categoria;
	this.competidor = competidor;
    }

    public int getCategoria() {
	return categoria;
    }

    public int getCompetidor() {
	return competidor;
    }
}
