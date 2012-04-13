package br.rio.puc.bd3.t1.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class AMenu {

    protected static class Opcao {
	private final int id;

	private final String nome;

	public Opcao(int id, String nome) {
	    this.id = id;
	    this.nome = nome;
	}
    }

    private final String titulo;

    private final Opcao[] opcoes;

    private final boolean manualSair;

    public AMenu(String titulo, Opcao[] opcoes, boolean manualSair) {
	this.titulo = titulo;
	this.opcoes = opcoes;
	this.manualSair = manualSair;
    }

    public AMenu(String titulo, Opcao[] opcoes) {
	this(titulo, opcoes, true);
    }

    public void show() {
	int seleccao = -1;
	Scanner res = new Scanner(System.in);
	do {
	    System.out.println("====================");
	    System.out.println("= " + titulo);
	    System.out.println("====================");
	    for (int i = 0; i < opcoes.length; i++) {
		System.out.println("[" + (i + 1) + "] " + opcoes[i].nome);
	    }
	    if (manualSair) {
		System.out.println("[0] Sair");
	    }
	    System.out.println("====================");
	    do {
		System.out.print("Digite selecção: ");
		try {
		    seleccao = res.nextInt();
		    if (seleccao < 0 || seleccao > opcoes.length) {
			seleccao = -1;
		    }
		} catch (InputMismatchException ime) {
		    seleccao = -1;
		} finally {
		    // cai fim da linhaa
		    res.nextLine();
		}
		if (seleccao == -1) {
		    System.out.println("Opcão inválida tente novamente!!!");
		}
	    } while (seleccao == -1);

	    if (seleccao > 0) {
		opcaoSelectionado(opcoes[seleccao - 1].id);
	    }
	} while (seleccao != 0 && manualSair);
    }

    protected abstract void opcaoSelectionado(int id);
}
