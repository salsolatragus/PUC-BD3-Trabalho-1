package br.rio.puc.bd3.t1.model;

public class Participacao {

    private final int bateria;
    private final int categoria;
    private final int competidor;

    public Participacao(int categoria, int competidor) {
	this.categoria = categoria;
	this.bateria = -1;
	this.competidor = competidor;
    }

    public int getCategoria() {
	return categoria;
    }

    public int getCompetidor() {
	return competidor;
    }

    public int getBateria() {
	return bateria;
    }
}
