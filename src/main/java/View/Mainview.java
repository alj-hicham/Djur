package View;

import DAO.användarenimple;
import Model.Användaren;
import services.Användarenoperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mainview {


    public static void main(String[] args) {


        Användaren användaren = new Användaren();

        användarenimple användar = new användarenimple();
        String username = användaren.getLoginiId();
        String password = användaren.getPassword();
        användar.VerifyUser(username, password);

    }


}
