package br.rio.puc.bd3.t1.ui;

import java.sql.SQLException;
import java.util.List;

import br.rio.puc.bd3.t1.dao.Conexao_DB;
import br.rio.puc.bd3.t1.model.Competidor;
import br.rio.puc.bd3.t1.model.Resultado;

public class MenuPublico extends AMenu {

    public static final int CONSULTA_PARTICIPANTES = 1;
    public static final int CONSULTA_BATERIAS = 2;

    private final Conexao_DB con;

    public MenuPublico() {
	super(
		"Modo de exibição dos resultados",
		new Opcao[]
		{
			new Opcao(
				CONSULTA_PARTICIPANTES,
				"Consultar participantes atuais de uma categoria"),
			new Opcao(
				CONSULTA_BATERIAS,
				"Consultar baterias que um competidor participou")

		});

	con = new Conexao_DB();
    }

    @Override
    protected void opcaoSelectionado(int id) {
	switch (id) {
	case CONSULTA_PARTICIPANTES:
	    consultaParticipantes();
	    break;
	case CONSULTA_BATERIAS:
	    consultaBaterias();
	    break;
	}

    }

    public void consultaParticipantes()
    {
	try {
	    (new MenuSeleccaoCategoria(con.getCategorias()) {
		@Override
		protected void opcaoSelectionado(
			final int categoriaId) {
		    try {
			List<Competidor> competidores = con
				.getCompetidoresAtuaisDeCategoria(categoriaId);

			System.out.println();
			System.out.println("-------------------------------");
			System.out.println("- Participantes");
			System.out.println("-------------------------------");
			for (int i = 0; i < competidores.size(); i++) {
			    System.out.println(" * "
				    + competidores.get(i).toString());
			    System.out
				    .println("-------------------------------");
			}
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

    public void consultaBaterias()
    {
	try {
	    (new MenuSeleccaoCompetidor(con.getCompetidores()) {
		@Override
		protected void opcaoSelectionado(final int competidorId) {
		    try {
			List<Resultado> resultados = con
				.getResultadosDoCompetidor(competidorId);

			System.out.println();
			System.out.println("-------------------------------");
			System.out.println("- Resultados");
			System.out.println("-------------------------------");
			for (Resultado res : resultados) {
			    System.out.println(res.toString());
			    System.out
				    .println("-------------------------------");
			}
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
