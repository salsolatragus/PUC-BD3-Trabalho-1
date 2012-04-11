package br.rio.puc.bd3.t1.ui;

import java.sql.SQLException;

import br.rio.puc.bd3.t1.dao.Conexao_DB;

public class Menu_Organizador extends AMenu {

    public static final int NOVA_CATEGORIA = 1;

    private final Conexao_DB con;

    public Menu_Organizador() {
	super("Modo de organização", new Opcao[] {
		new Opcao(NOVA_CATEGORIA, "Nova categoria")
	});
	con = new Conexao_DB();
    }

    @Override
    protected void opcaoSelectionado(int opcao) {
	switch (opcao) {
	case NOVA_CATEGORIA:
	    criaCategoria();
	    break;
	}
    }

    private void criaCategoria() {
	Dialogo_Nova_Categoria cat = new Dialogo_Nova_Categoria();
	cat.show();

	try {
	    con.insereCategoria(cat.getCategoria());
	} catch (SQLException sqle) {
	    sqle.printStackTrace(System.err);
	}
    }

    @Override
    protected void finalize() throws SQLException {
	if (con != null) {
	    con.close();
	}
    }
}
