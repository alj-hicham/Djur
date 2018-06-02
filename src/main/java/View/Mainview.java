package View;

import DAO.Db_Connect;
import DAO.användarenimple;
import Model.Användaren;
import services.Användarenoperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mainview {


    public static void main(String[] args) {
/*        Db_Connect connect = new Db_Connect();
        connect.openconnection();*/


        String username = null;

        String password = null;
        användarenimple användar = new användarenimple();
        Användaren användaren = new Användaren();
        användar.VerifyUser(username, password);

/* användar.VerifyUser(username, password);
användar.Adduser(användaren);*/

    }


}
