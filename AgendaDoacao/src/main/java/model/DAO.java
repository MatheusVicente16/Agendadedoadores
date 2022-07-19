package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	/** Módulo de conexão **/
	// Parametros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbdoacao?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "";

	// Método de conexão

	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/* CRUD CREATE DO DB */

	public void inserirContato(JavaBeans contato) {
		// Criando um objeto para acessar a classe JavaBeans

		String create = "insert into contatos (nome,tipo,fone,email) values (?,?,?,?)";
		try {
			// Abrindo a conexão com DB
			Connection con = conectar();
			// Preparando a Query para a execução no DB
			PreparedStatement pst = con.prepareStatement(create);
			// Subistituindo os parametros (?,?,?,?) pelo conteudo das vareaveis do JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getTipo());
			pst.setString(3, contato.getFone());
			pst.setString(4, contato.getEmail());

			// Executando a Query

			pst.executeUpdate();

			// Encerrando a conexão com o DB

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/* CRUD READ */

	public ArrayList<JavaBeans> listarContatos() {
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "select * from contatos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// Este laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				// Variáveis de apoi que recebem os dados do banco
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String tipo = rs.getString(3);
				String fone = rs.getString(4);
				String email = rs.getString(5);
				// Populando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, tipo, fone, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	/** CRUD UPDATE **/
	// selecionar o contato
	public void selecionarContato(JavaBeans contato) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contato.getIdcon());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				//setar as variaveis JavaBeans
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setTipo(rs.getString(3));
				contato.setFone(rs.getString(4));
				contato.setEmail(rs.getString(5));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	//Editar o contato
	public void alteraContato(JavaBeans contato) {
		String create = "update contatos set nome=?,tipo=?,fone=?,email=? where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getTipo());
			pst.setString(3, contato.getFone());
			pst.setString(4, contato.getEmail());
			pst.setString(5, contato.getIdcon());
			pst.executeUpdate();	
			con.close();
		} catch (Exception e) {		
		System.out.println(e);
		}
	}
	// CRUD DELETE
	
	public void deletarContato(JavaBeans contato) {
		String delete = "delete from contatos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}

}
