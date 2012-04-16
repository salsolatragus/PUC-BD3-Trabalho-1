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

import br.rio.puc.bd3.t1.model.Bateria;
import br.rio.puc.bd3.t1.model.Categoria;
import br.rio.puc.bd3.t1.model.Competidor;
import br.rio.puc.bd3.t1.model.Participacao;
import br.rio.puc.bd3.t1.model.Resultado;

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

    public void insereResultado(Resultado p) throws SQLException {
	CallableStatement insere = null;
	try {
	    if (p.getDesclassificacao() == Resultado.DESCLASSIFICADO) {
		insere = con
			.prepareCall("{ CALL inserir_Desclassificacao(?,?) }");
		insere.setInt(1, p.getCompetidor());
		insere.setInt(2, p.getBateria());
		insere.execute();
	    } else if (p.getTempo() >= 0) {
		insere = con.prepareCall("{ CALL inserir_Resultado(?,?,?) }");
		insere.setInt(1, p.getCompetidor());
		insere.setInt(2, p.getBateria());
		insere.setInt(3, p.getTempo());
		insere.execute();
	    } else {
		throw new IllegalArgumentException("Resultado inválido!");
	    }
	} finally {
	    if (insere != null) {
		insere.close();
	    }
	}
    }

    public void fechaBateria(int bateriaId) throws SQLException {
	CallableStatement registra = con
		.prepareCall("{ CALL fechar_Bateria(?) }");
	try {
	    registra.setInt(1, bateriaId);
	    registra.execute();
	} finally {
	    registra.close();
	}
    }

    /**************************************************
     * Métodos de Leitura
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

    public List<Competidor> getCompetidoresDeCategoria(int categoria)
	    throws SQLException {
	List<Competidor> competidores = new ArrayList<Competidor>();

	PreparedStatement leitura = con
		.prepareStatement("SELECT Id, Nome, Nacionalidade, Data_de_nacimento FROM Categoria_Competidor LEFT JOIN Competidor ON Competidor = Id WHERE Categoria = ?");
	try {
	    leitura.setInt(1, categoria);
	    ResultSet res = leitura.executeQuery();
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

    public List<Resultado> getResultadosDoCompetidor(int competidor)
	    throws SQLException {
	List<Resultado> participacoes = new ArrayList<Resultado>();
	PreparedStatement leitura = con
		.prepareStatement("SELECT Bateria, Desclassificado, Tempo FROM Participacoes WHERE Competidor = ?");
	try {
	    leitura.setInt(1, competidor);
	    ResultSet res = leitura.executeQuery();
	    try {
		while (res.next()) {
		    int bateria = res.getInt(1);
		    int desclassificado = res.getInt(2);
		    int tempo = res.getInt(3);
		    participacoes.add(new Resultado(competidor, bateria,
			    desclassificado, tempo));
		}
	    } finally {
		res.close();
	    }
	} finally {
	    leitura.close();
	}

	return participacoes;
    }

    public List<Bateria> getBateriasQueFaltamResultados() throws SQLException {
	List<Bateria> baterias = new ArrayList<Bateria>();
	Statement leitura = con.createStatement();
	try {
	    ResultSet res = leitura
		    .executeQuery("SELECT Id, Categoria, Tipo, Numero FROM Bateria_Info WHERE Participantes > Resultados");
	    try {
		while (res.next()) {
		    int id = res.getInt(1);
		    int categoria = res.getInt(2);
		    int tipo = res.getInt(3);
		    int numero = res.getInt(4);
		    baterias.add(new Bateria(id, categoria, tipo, numero));
		}
	    } finally {
		res.close();
	    }
	} finally {
	    leitura.close();
	}
	return baterias;
    }

    public List<Competidor> getCompetidoresDeBateriaSemResultados(int bateriaId)
	    throws SQLException {
	List<Competidor> competidores = new ArrayList<Competidor>();
	PreparedStatement leitura = con
		.prepareStatement("SELECT C.Id, Nome, Nacionalidade, Data_de_nacimento FROM Participacoes LEFT JOIN Competidor C ON Competidor = C.Id WHERE Bateria = ? AND Desclassificado IS NULL");
	try {
	    leitura.setInt(1, bateriaId);
	    ResultSet res = leitura.executeQuery();
	    try {
		while (res.next()) {
		    int id = res.getInt(1);
		    String nome = res.getString(2);
		    String nacionalidade = res.getString(3);
		    Date nacimento = res.getDate(4);
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

    public List<Bateria> getBateriasCompletas() throws SQLException {
	List<Bateria> baterias = new ArrayList<Bateria>();
	Statement leiture = con.createStatement();
	try {
	    ResultSet res = leiture
		    .executeQuery("SELECT Id, Categoria, Tipo, Numero FROM Bateria_Info WHERE Participantes = Resultados AND Fechado IS NULL");
	    try {
		while (res.next()) {
		    int id = res.getInt(1);
		    int categoria = res.getInt(2);
		    int tipo = res.getInt(3);
		    int numero = res.getInt(4);
		    baterias.add(new Bateria(id, categoria, tipo, numero));
		}
	    } finally {
		res.close();
	    }
	} finally {
	    leiture.close();
	}
	return baterias;
    }

    public void close() throws SQLException {
	con.close();
    }

    @Override
    protected void finalize() throws Throwable {
	close();
    }
}
