import DAO.användareimple;
import Model.Användare;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Mainview {


    public static void main(String[] args) {
        //field
        String username = null;
        String password = null;
        Scanner scanner = new Scanner(System.in);
        användareimple användareop = new användareimple();
        Användare användare = new Användare();
/*      Db_Connect connect = new Db_Connect();
        connect.openconnection();*/

        System.out.println("------------------------");

        System.out.println("välkomen ");
        System.out.println("Logga in på ditt konto :  klick :1");
        System.out.println("skapa konta : klick :2 ");
        System.out.print("avsluta :  klick 3");

        //this if condition check if the user press number 1 , 2 ,3
        //1 means login
        //2 mean create account
        //3 mean exit the application
        // if no one of this choice then the user will be asked to press a correct number
        try {
            int choice = scanner.nextInt();

            if (choice == 1) {

                try {
                    användareop.VerifyUser(username, password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (choice == 2) {
                try {
                    användareop.Adduser(användare);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (choice == 3) {
                System.exit(0);
            }
        } catch (InputMismatchException e) {
            System.out.println("Vänligen ange ett korrekt numm");

        }
    }


}
