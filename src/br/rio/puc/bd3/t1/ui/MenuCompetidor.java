package br.rio.puc.bd3.t1.ui;

import java.sql.SQLException;

import br.rio.puc.bd3.t1.dao.Conexao_DB;
import br.rio.puc.bd3.t1.model.Participacao;

public class MenuCompetidor extends AMenu {

    private static final int INSERE_DADOS = 1;

    private static final int REGISTRAR = 2;

    private final Conexao_DB con;

    public MenuCompetidor() {
	super("Modo de registração", new Opcao[] {
		new Opcao(INSERE_DADOS, "Insere informações pessoais"),
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
	    criaParticipacao();
	    break;
	}
    }

    private void criaCompetidor() {
	DialogoNovoCompetidor cat = new DialogoNovoCompetidor();
	cat.show();

	try {
	    con.insereCompetidor(cat.getCompetidor());
	    System.out.println();
	    System.out.println(" => Competidor inserido com successo!");
	    System.out.println();
	} catch (SQLException sqle) {
	    sqle.printStackTrace(System.err);
	}
    }

    private void criaParticipacao() {
	try {
	    (new MenuSeleccaoCompetidor(con.getCompetidores()) {
		@Override
		protected void opcaoSelectionado(final int competidorId) {
		    try {
			(new MenuSeleccaoCategoria(con.getCategorias()) {
			    @Override
			    protected void opcaoSelectionado(
				    final int categoriaId) {
				try {
				    con.insereParticipacao(new Participacao(
					    categoriaId, competidorId));
				    System.out.println();
				    System.out
					    .println(" => Participação inserido com successo!");
				    System.out
					    .println(" Quer participar em mais um categoria?");
				    System.out.println();
				} catch (SQLException sqle) {
				    sqle.printStackTrace(System.err);
				}

			    }
			}).show();
			System.out.println();
			System.out.println(" Quer registrar outro competidor?");
			System.out.println();
		    } catch (SQLException sqle) {
			sqle.printStackTrace(System.err);
		    }
		}
	    }).show();
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
