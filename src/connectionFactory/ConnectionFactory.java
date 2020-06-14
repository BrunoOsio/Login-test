package connectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Guilherme
 */
public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/dbLogin";
    private static final String USER = "root";
    private static final String PASS = "";

    public static java.sql.Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro na conexão: " + ex);
        }
    }

    public static void closeConnection(Connection con) {

        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println("Erro: Nao foi possivel fechar 'con'");
            }
        }

    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("Erro: Não foi possivel fechar 'con||stmt'");
            }
        }

        closeConnection(con);

    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Erro:  Não foi possivel fechar 'con||stmt||rs'");
            }
        }

        closeConnection(con, stmt);

    }

}
