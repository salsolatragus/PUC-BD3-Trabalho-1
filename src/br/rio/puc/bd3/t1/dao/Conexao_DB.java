package br.rio.puc.bd3.t1.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.rio.puc.bd3.t1.model.Categoria;
import br.rio.puc.bd3.t1.model.Competidor;
import br.rio.puc.bd3.t1.model.Participacao;

public class Conexao_DB
{
    public static final String URL = "jdbc:oracle:thin:@139.82.36.250:1521/orcl";

    public static final String NOME = "bd312";

    public static final String SENHA = "bd312";

    static {
	try {
	    Class.forName("oracle.jdbc.OracleDriver");
	} catch (ClassNotFoundException cnfe) {
	    System.err.println("N�o consigui carregar Driver de Oracle!!!");
	    System.exit(1);
	}
    }

    Connection con = null;
    PreparedStatement stm = null;

    public Conexao_DB() {
	try {
	    con = DriverManager.getConnection(URL, NOME, SENHA);
	} catch (SQLException e) {
	    System.err.print("ERRO AO ABRIR A CONEX�O!!!");
	    e.printStackTrace();
	}

    }

    /**************************************************
     * M�todos de Cria��o
     ************************************************/

    public void insereCategoria(Categoria cat) throws SQLException {
	CallableStatement insere = con
		.prepareCall("{ CALL inserir_Categoria(?,?,?,?,?) }");
	try {
	    insere.setString(1, cat.getNome());
	    insere.setDate(2, new Date(cat.getDataSelectivas().getTime()));
	    insere.setDate(3, new Date(cat.getDataQuartasDeFinal().getTime()));
	    insere.setDate(4, new Date(cat.getDataSemifinais().getTime()));
	    insere.setDate(5, new Date(cat.getDataFinal().getTime()));
	    insere.execute();
	} finally {
	    insere.close();
	}
    }

    public void insereCompetidor(Competidor comp) throws SQLException {
	CallableStatement insere = con
		.prepareCall("{ CALL inserir_Competidor(?,?,?) }");
	try {
	    insere.setString(1, comp.getNome());
	    insere.setString(2, comp.getNacionalidade());
	    insere.setDate(3, new Date(comp.getDataDeNacimento().getTime()));
	    insere.execute();
	} finally {
	    insere.close();
	}
    }

    public void insereParticipacao(Participacao p) throws SQLException {
	CallableStatement registra = con
		.prepareCall("{ CALL registrar_por_Categoria(?,?) }");
	try {
	    registra.setInt(1, p.getCompetidor());
	    registra.setInt(2, p.getCategoria());
	    registra.execute();
	} finally {
	    registra.close();
	}
    }

    void insereResultado()
    {

	String sql = null;

	try
	{
	    con.prepareStatement(sql);

	    con.close();
	    stm.close();

	} catch (SQLException e)
	{

	}
    }

    /**************************************************
     * M�todos de Cria��o
     ************************************************/

    void alteraCategoria()
    {
	String sql = null;

	try
	{
	    con.prepareStatement(sql);

	    con.close();
	    stm.close();

	} catch (SQLException e)
	{

	}
    }

    /**************************************************
     * M�todos de Leitura
     ************************************************/

    public List<Categoria> getCategorias() throws SQLException {
	List<Categoria> categorias = new ArrayList<Categoria>();
	Statement leitura = con.createStatement();
	try {
	    ResultSet res = leitura
		    .executeQuery("SELECT Id, Nome, Data_selectivas, Data_quartas_de_final, Data_semifinais, Data_final FROM Categoria");
	    try {
		while (res.next()) {
		    int id = res.getInt(1);
		    String nome = res.getString(2);
		    java.util.Date selectivas = res.getDate(3);
		    java.util.Date quartas = res.getDate(4);
		    java.util.Date semifinais = res.getDate(5);
		    java.util.Date ofinal = res.getDate(6);
		    categorias.add(new Categoria(id, nome, selectivas, quartas,
			    semifinais, ofinal));
		}
	    } finally {
		res.close();
	    }
	} finally {
	    leitura.close();
	}
	return categorias;
    }

    public List<Competidor> getCompetidores() throws SQLException {
	List<Competidor> competidores = new ArrayList<Competidor>();
	Statement leitura = con.createStatement();
	try {
	    ResultSet res = leitura
		    .executeQuery("SELECT Id, Nome, Nacionalidade, Data_de_nacimento FROM Competidor");
	    try {
		while (res.next()) {
		    int id = res.getInt(1);
		    String nome = res.getString(2);
		    String nacionalidade = res.getString(3);
		    java.util.Date nacimento = res.getDate(4);
		    competidores.add(new Competidor(id, nome, nacionalidade,
			    nacimento));
		}
	    } finally {
		res.close();
	    }
	} finally {
	    leitura.close();
	}
	return competidores;
    }

    /**************************************************
     * M�todos de Exclus�o
     ************************************************/

    void deletaCategoria()
    {
	String sql = null;

	try
	{
	    con.prepareStatement(sql);

	    con.close();
	    stm.close();

	} catch (SQLException e)
	{

	}
    }

    public void close() throws SQLException {
	con.close();
    }

    @Override
    protected void finalize() throws Throwable {
	close();
    }
}
