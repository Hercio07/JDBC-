package DB;

import java.sql.Connection;
import java.sql.DriverManager;
public class Conexao {
    
    
     // mettodo reaponsavel para estabelecer a conexao com o banco de dados 
    public static Connection conector() {
        Connection conexao = null;
        // A linha abaixo chama o driver
        String driver = "com.mysql.jdbc.Driver";
        // Armazenando informacoes referentes ao banco de dados
        final String url = "jdbc:mysql://localhost:3306/gestao_discos?useSSL=false";
        final String user = "root";
        final String password = "0310";
        // Estabelecendo a conexao com o banco de dados
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            
            return null;
        }
    }
}
/*
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/gestao_discos?useSSL=false";
        String user = "root";
        String password = "0310";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado com sucesso!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

*/