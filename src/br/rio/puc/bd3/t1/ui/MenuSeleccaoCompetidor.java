package br.rio.puc.bd3.t1.ui;

import java.util.List;

import br.rio.puc.bd3.t1.model.Competidor;

public abstract class MenuSeleccaoCompetidor extends AMenu {

    private static Opcao[] getOpcoes(List<Competidor> competidores) {
	Opcao[] opcoes = new Opcao[competidores.size()];
	for (int i = 0; i < competidores.size(); i++) {
	    Competidor cur = competidores.get(i);
	    opcoes[i] = new Opcao(cur.getId(), cur.getNome());
	}
	return opcoes;
    }

    public MenuSeleccaoCompetidor(List<Competidor> competidores) {
	super("Escolhe competidor:", getOpcoes(competidores));
    }

}
