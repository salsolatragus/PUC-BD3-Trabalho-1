package br.rio.puc.bd3.t1.ui;

import java.util.List;

import br.rio.puc.bd3.t1.model.Categoria;

public abstract class MenuSeleccaoCategoria extends AMenu {

    private static Opcao[] getOpcoes(List<Categoria> categorias) {
	Opcao[] opcoes = new Opcao[categorias.size()];
	for (int i = 0; i < categorias.size(); i++) {
	    Categoria cur = categorias.get(i);
	    opcoes[i] = new Opcao(cur.getId(), cur.getNome());
	}
	return opcoes;
    }

    public MenuSeleccaoCategoria(List<Categoria> categorias) {
	super("Escolhe categoria:", getOpcoes(categorias));
    }
}
