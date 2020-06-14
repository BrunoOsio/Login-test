package model.dao;

import connectionFactory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.Usuario;

/**
 *
 * @author Guilherme
 */
public class UsuarioDAO {

    public static boolean save(Usuario usuario) {
        String sql = "insert into dbLogin.usuarios(usuario, email, senha) values(? , ?, ?);";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.executeUpdate();

            ConnectionFactory.closeConnection(con, ps);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;

        }

    }

    public static List<Usuario> executeQuery() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "select id, usuario, email, senha from dbLogin.usuarios;";

        Connection con = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                System.out.println("");

            }

            ConnectionFactory.closeConnection(con, ps, rs);

            return usuarios;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public static boolean login(Usuario usuario) {
        String sql = "select usuario, senha from dbLogin.usuarios";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (usuario.getUsuario().equals(rs.getString(1)) && usuario.getSenha().equals(rs.getString(2))) {
                    System.out.println("Login com sucesso");
                    
                    ConnectionFactory.closeConnection(con, ps, rs);
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }
}
