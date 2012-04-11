package br.rio.puc.bd3.t1.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.rio.puc.bd3.t1.model.Categoria;
import br.rio.puc.bd3.t1.model.Competidor;

public class Conexao_DB
{
    public static final String URL = "jdbc:oracle:thin:@139.82.36.250:1521/orcl";

    public static final String NOME = "bd312";

    public static final String SENHA = "bd312";

    static {
	try {
	    Class.forName("oracle.jdbc.OracleDriver");
	} catch (ClassNotFoundException cnfe) {
	    System.err.println("Não consigui carregar Driver de Oracle!!!");
	    System.exit(1);
	}
    }

    Connection con = null;
    PreparedStatement stm = null;

    public Conexao_DB() {
	try {
	    con = DriverManager.getConnection(URL, NOME, SENHA);
	} catch (SQLException e) {
	    System.err.print("ERRO AO ABRIR A CONEXÃO!!!");
	    e.printStackTrace();
	}

    }

    /**************************************************
     * Métodos de Criação
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

    void insereBateria()
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

    void insereBateriaCompetidor()
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
     * Métodos de Criação
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

    void alteraCompetidor()
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

    void alteraBateria()
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

    void alteraBateriaCompetidor()
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

    void alteraResultado()
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
     * Métodos de Exclusão
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

    void deletaCompetidor()
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

    void deletaBateria()
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

    void deletaBateriaCompetidor()
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

    void deletaResultado()
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
