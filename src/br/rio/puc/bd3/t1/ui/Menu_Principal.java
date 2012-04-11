package br.rio.puc.bd3.t1.ui;

public class Menu_Principal extends AMenu {

    public static final int ORGANIZADOR = 1;

    public static final int COMPETIDOR = 2;

    public static final int JUIZ = 3;

    public Menu_Principal() {
	super("Menu principal", new Opcao[] {
		new Opcao(ORGANIZADOR, "Fazer login como organizador"),
		new Opcao(COMPETIDOR, "Fazer login como competidor"),
		new Opcao(JUIZ, "Fazer login como juiz")
	});
    }

    @Override
    protected void opcaoSelectionado(int opcao) {
	System.out.println();
	switch (opcao) {
	case ORGANIZADOR:
	    System.out.println("... Bem vindo, organizador!");
	    System.out.println();
	    (new Menu_Organizador()).show();
	    break;
	case COMPETIDOR:
	    System.out.println("... Bem vindo, competidor!");
	    System.out.println();
	    (new Menu_Competidor()).show();
	    break;
	case JUIZ:
	    System.out.println("... Bem vindo, juiz!");
	    System.out.println();
	    (new Menu_Juiz()).show();
	    break;
	}
    }

    public static void main(String[] args) {
	(new Menu_Principal()).show();
    }
}
