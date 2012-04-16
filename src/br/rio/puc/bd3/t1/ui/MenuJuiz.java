package br.rio.puc.bd3.t1.ui;

import java.sql.SQLException;

import br.rio.puc.bd3.t1.dao.Conexao_DB;

public class MenuJuiz extends AMenu {

    public static final int RESULTADOS = 1;

    public static final int FECHA = 2;

    private final Conexao_DB con;

    public MenuJuiz()
    {
	super("Modo de exibição dos resultados", new Opcao[]
	{
		new Opcao(RESULTADOS, "Inserir resultados"),
		new Opcao(FECHA, "Fechar Bateria")
	});

	con = new Conexao_DB();
    }

    @Override
    protected void opcaoSelectionado(int id)
    {
	switch (id)
	{
	case RESULTADOS:
	    criaResultados();
	    break;
	case FECHA:
	    fechaBateria();
	    break;
	}

    }

    private void criaResultados()
    {
	try {
	    (new MenuSeleccaoBateria(con.getBateriasQueFaltamResultados()) {
		@Override
		protected void opcaoSelectionado(final int bateriaId) {
		    try {
			(new MenuSeleccaoCompetidor(
				con.getCompetidoresDeBateriaSemResultados(bateriaId)) {
			    @Override
			    protected void opcaoSelectionado(
				    final int competidorId) {
				DialogoNovoJuiz juiz = new DialogoNovoJuiz(
					competidorId, bateriaId);
				juiz.show();

				try {
				    con.insereResultado(juiz.getParticipacao());
				    System.out.println();
				    System.out
					    .println(" => Resultado inserido com successo!");
				    System.out
					    .println(" Insere mais um resultado dessa bateria?");
				    System.out.println();
				} catch (SQLException sqle) {
				    sqle.printStackTrace(System.err);
				}
			    }
			}).show();
			System.out.println();
			System.out
				.println(" Insere resultados para uma outra bateria?");
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

    private void fechaBateria()
    {
	try {
	    (new MenuSeleccaoBateria(con.getBateriasCompletas()) {

		@Override
		protected void opcaoSelectionado(int bateriaId) {
		    try {
			con.fechaBateria(bateriaId);
			System.out.println();
			System.out.println(" => Bateria fechado com successo!");
			System.out.println(" Fecha outra bateria?");
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
}
