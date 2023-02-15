package models;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	// variáveis para setar o banco de dados
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://10.26.49.130:3306/dboficina"; 
	private String user = "dba";
	private String password = "123@Senac"; 
	
	/**
	 * Metodo responsavel por abrir uma conexao com o banco  
	 * @return con 
	 */
	public Connection  conectar() {
		//criar um objeto 
		Connection con = null;
		//tratamento de exce��es 
		try {
			//l�gica principal para abrir a conex�o
			//uso do driver 
			Class.forName(driver);
			//obter os parametros da conexão (informações do servidor )
			con = DriverManager.getConnection(url, user, password); //conectar!
			//retornar conexão ("abrir a porta da geladeira")
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
					
		}
			
	}

	
	
}//fim do código