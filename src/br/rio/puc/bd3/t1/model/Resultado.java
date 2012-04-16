package br.rio.puc.bd3.t1.model;

public class Resultado {
    public static final int DESCLASSIFICADO = 1;
    public static final int NAO_DESCLASSIFICADO = 0;
    public static final int NENHUM_TEMPO = -1;

    private final int competidor;

    private final int bateria;

    private final int desclassificacao;

    private final int tempo;

    public Resultado(int competidor, int bateria, int desclassificacao,
	    int tempo) {
	super();
	this.competidor = competidor;
	this.bateria = bateria;
	this.desclassificacao = desclassificacao;
	this.tempo = tempo;
    }

    public int getCompetidor() {
	return competidor;
    }

    public int getBateria() {
	return bateria;
    }

    public int getDesclassificacao() {
	return desclassificacao;
    }

    public int getTempo() {
	return tempo;
    }
}
