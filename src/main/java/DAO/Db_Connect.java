package DAO;


import services.Db_connection;

import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db_Connect implements Db_connection {

    Connection connect = null;
    PreparedStatement preparedStatement = null;
    private String user = "root", pw = "", url = "jdbc:mysql://localhost:3306/djur";
/*    Användaren användaren = new Användaren();
    private String username = användaren.getLoginiId();
    private String password = användaren.getPassword();*/

    public void openconnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, pw);
            if (connect != null) {


                System.out.println("successfuly connected");

            }


        } catch (ClassNotFoundException exp) {
            System.out.println(exp);
        } catch (SQLException sqlExp) {
            System.out.println(sqlExp);
        } finally {


        }
    }


    PreparedStatement prepareRequest(String req) {
//        användarenimple login = new användarenimple();
        try {
            preparedStatement = connect.prepareStatement(req);
//

        } catch (SQLException Ex) {
            System.out.println(Ex);
        }
        return preparedStatement;
    }


    public void closconnection() {

        try {
            connect.close();
        } catch (SQLException Sqlexeption) {
            System.out.println(Sqlexeption);
        } finally {

        }

    }
}
