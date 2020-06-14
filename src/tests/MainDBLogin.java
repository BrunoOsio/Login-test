package tests;

import connectionFactory.ConnectionFactory;
import java.util.List;
import model.bean.Usuario;
import model.dao.UsuarioDAO;

/**
 *
 * @author Guilherme
 */
public class MainDBLogin {

    public static void main(String[] args) {
        ConnectionFactory.getConnection();

        selectAll();
        
//        save();

    }
    
    public static void selectAll(){
        List<Usuario> usuarios = executeQuery();
        
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    public static List<Usuario> executeQuery() {
        return UsuarioDAO.executeQuery();

    }

    public static void save() {
        Usuario user1 = new Usuario("gabrielgsb022", "gabriel@gmail.com", "87654321");
        Usuario user2 = new Usuario("Nick Toretto", "nick@gmail.com", "99999");
        Usuario user3 = new Usuario("HODOR", "tonho@gmail.com", "5365");
        
        UsuarioDAO.save(user1);
        UsuarioDAO.save(user2);
        UsuarioDAO.save(user3);

    }
}
