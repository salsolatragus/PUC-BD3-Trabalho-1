package br.rio.puc.bd3.t1.ui;

import java.sql.SQLException;

import br.rio.puc.bd3.t1.dao.Conexao_DB;

public class MenuCompetidor extends AMenu {

    private static final int INSERE_DADOS = 1;

    private static final int REGISTRAR = 2;

    private final Conexao_DB con;

    public MenuCompetidor() {
	super("Modo de registra��o", new Opcao[] {
		new Opcao(INSERE_DADOS, "Insere informa��es pessoais"),
		new Opcao(REGISTRAR, "Registra por uma categoria")
	});
	con = new Conexao_DB();
    }

    @Override
    protected void opcaoSelectionado(int id) {
	switch (id) {
	case INSERE_DADOS:
	    criaCompetidor();
	    break;
	case REGISTRAR:

	    break;
	}
    }

    private void criaCompetidor() {
	DialogoNovoCompetidor cat = new DialogoNovoCompetidor();
	cat.show();

	try {
	    con.insereCompetidor(cat.getCompetidor());
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