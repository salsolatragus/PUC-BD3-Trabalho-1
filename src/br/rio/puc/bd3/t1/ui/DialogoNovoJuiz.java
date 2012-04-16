package br.rio.puc.bd3.t1.ui;

import br.rio.puc.bd3.t1.model.Resultado;

public class DialogoNovoJuiz extends ADialogo
{
    public static final int DESCLASSIFICADO = 1;

    public static final int TEMPO = 2;

    private final int competidor;
    private final int bateria;

    public DialogoNovoJuiz(int competidor, int bateria)
    {
	super("Entre com o resultado da competição",
		new Caracteristica[]
		{
			new Caracteristica(DESCLASSIFICADO,
				"Desclassificado (0|1)",
				TIPO.INT),
			new Caracteristica(TEMPO,
				"Tempo (-1 se desclassificado)", TIPO.INT)
		});
	this.competidor = competidor;
	this.bateria = bateria;
    }

    public Resultado getParticipacao()
    {
	int desclassificado = getValorInt(DESCLASSIFICADO);
	int tempo = getValorInt(TEMPO);
	return new Resultado(competidor, bateria, desclassificado, tempo);

    }

}
