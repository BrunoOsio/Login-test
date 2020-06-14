package mainProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static mainProject.Main.logar;
import model.bean.Usuario;
import model.dao.UsuarioDAO;

/**
 *
 * @author Guilherme
 */
public class Main {

    static Scanner input;
    static int in1 = 0;
    static List<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        inicio();
    }
    
    

    public static void inicio() {
        input = new Scanner(System.in);

        while (in1 != 1 && in1 != 2 && in1 != 3 && in1 != 4) {
            System.out.println("--MAIN--");
            System.out.println("Escolha o metodo:\n1- Registrar\n2- Logar\n3- Mostrar Users\n4 - Fechar");
            in1 = input.nextInt();
        }
        switch (in1) {
            case 1:
                registrar();
                break;
            case 2:
                logar();
                break;
            case 3:
                mostrarUsers();
                break;
            case 4:
                System.exit(0);
                break;
        }
        in1 = 0;
        Main.inicio();
    }

    public static boolean registrar() {
        input = new Scanner(System.in);

        System.out.println("--REGISTRO--");
        System.out.println("Insira seu username: ");
        String inUser = input.nextLine();

        System.out.println("Insira seu email: ");
        String inEmail = input.nextLine();

        System.out.println("Insira seu password: ");
        String inPass = input.nextLine();

        Usuario user = new Usuario(inUser, inEmail, inPass);
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return UsuarioDAO.save(user);

    }

    public static void mostrarUsers() {
        usuarios.clear();
        usuarios = UsuarioDAO.executeQuery();

        usuarios.forEach((usuario) -> {
            System.out.println(usuario);
        });
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void logar() {
        input = new Scanner(System.in);

        System.out.println("--LOGIN--");
        System.out.println("username: ");
        String inUser = input.nextLine();

        System.out.println("password: ");
        String inPass = input.nextLine();

        Usuario tempUser = new Usuario(inUser, inPass);
        
        System.out.println(UsuarioDAO.login(tempUser));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
