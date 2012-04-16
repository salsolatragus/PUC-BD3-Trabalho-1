package br.rio.puc.bd3.t1.ui;

import java.util.List;

import br.rio.puc.bd3.t1.model.Bateria;

public abstract class MenuSeleccaoBateria extends AMenu {

    private static Opcao[] getOpcoes(List<Bateria> baterias) {
	Opcao[] opcoes = new Opcao[baterias.size()];
	for (int i = 0; i < baterias.size(); i++) {
	    Bateria cur = baterias.get(i);
	    opcoes[i] = new Opcao(cur.getId(), cur.toString());
	}
	return opcoes;
    }

    public MenuSeleccaoBateria(List<Bateria> baterias) {
	super("Escolhe bateria:", getOpcoes(baterias));
    }
}
