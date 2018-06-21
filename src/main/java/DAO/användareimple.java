package DAO;

import Model.Användare;
import Model.Djur;
import services.Användarenoperation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


//this class is responsible for user operation

public class användareimple implements Användarenoperation {
    //field
    private Db_Connect connectdb = new Db_Connect();
    private Användare användare = new Användare();
    private String usernamesc = användare.getLoginiId();
    private String Passwordsc = användare.getPassword();
    private Djuroperationimpl djurop = new Djuroperationimpl();
    private Scanner sc = new Scanner(System.in);
    private Djur djur = new Djur();



    /*this method check the user registration
if the user have an account then the application will start by question
it's also check if the user is adminstrator or user
if the user is administrator then he have a menu with 9 choice
starta frågor
skapa konto
lista användare
ta bort konto
lägg till ett djur
lista djuren
lista vall
ta bort val
avsluta
if the user is normal user not administrator
then he will redirect to a menu with 2 choice
start fågor
avsluta*/
    public boolean VerifyUser(String username, String Password) throws SQLException {

        String req = "SELECT `ID`, `Namn`, `username`, `password`, `Role` FROM användare WHERE username = ? and password = ?";

        boolean Isvalid = false;
        connectdb.openconnection();

        try {
            PreparedStatement ps = connectdb.prepareRequest(req);
            System.out.println("ditt användarnamn");
            this.usernamesc = sc.nextLine();

            System.out.println("ditt lösenord");
            this.Passwordsc = sc.nextLine();

            ps.setString(1, usernamesc);
            ps.setString(2, Passwordsc);


            try {
                ResultSet resultSet = ps.executeQuery();


                try {

                    while (resultSet.next()) {
                        Isvalid = true;
                        String Role = resultSet.getString("Role");


                        if (Role.equals("admin")) {


                            System.out.println("starta frågor: klick 1 ");
                            System.out.println(" skapa konto :klick 2  ");
                            System.out.println("lista användare : klick 3");
                            System.out.println("ta bort konto : klick 4");
                            System.out.println("lägg till ett djur : klick 5");
                            System.out.println("lista djuren : klick 6");
                            System.out.println("lista val : klick 7");
                            System.out.println("ta bort val :8");
                            System.out.println("avsluta : klick 9");


                            int operation;
                            operation = sc.nextInt();


                            switch (operation) {
                                case 1:

                                    djurop.Startquestion();
                                    break;
                                case 2:
                                    Användare anv = new Användare();
                                    Adduser(anv);
                                    break;
                                case 3:
                                    Listing();
                                    break;
                                case 4:
                                    System.out.println("Vänligen ange ett korrekt nummer");
                                    Long id = sc.nextLong();
                                    delete(id);
                                    break;
                                case 5:
                                    djurop.addanimal(djur);
                                    break;


                                case 6:
                                    djurop.listadjuren();
                                    break;
                                case 7:

                                    valopimpl valopimplobj = new valopimpl();

                                    valopimplobj.lista_alla_val();
                                    break;

                                case 8:
                                    valopimpl valopimplobj8 = new valopimpl();
                                    System.out.println("Vänligen ange ett korrekt nummer");
                                    Long id_vall = sc.nextLong();
                                    valopimplobj8.tabortval(id_vall);
                                    break;

                                case 9:
                                    System.exit(0);

                                default:
                                    djurop.Startquestion();
                            }
                        } else if (Role.equals("user")) {

                            // try {
                            System.out.println("starta  : klick 1");
                            System.out.println("ta bort konto : klick 2");
                            System.out.println("avsluta: klick 2");

                            int operationuser = sc.nextInt();
                            switch (operationuser) {
                                case 1:
                                    djurop.Startquestion();
                                    break;

                                case 2:
                                    System.exit(0);


                            }
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Vänligen ange ett korrekt nummer");
                } finally {
                    resultSet.close();
                }
            } finally {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectdb.closconnection();
        }
        return Isvalid;
    }


    //this method is responsible for signup

    public boolean Adduser(Användare använd) throws SQLException {

        int a = 0;
        String req = "INSERT INTO användare(Namn,username,password) values (?,?,?)";


        connectdb.openconnection();
        PreparedStatement ps;
        try {
            ps = connectdb.prepareRequest(req);
            try {
                System.out.println("your name");
                sc.nextLine();
                String namnsc = sc.nextLine();
                System.out.println("username");
                sc.nextLine();
                String myusername = sc.nextLine();
                System.out.println("password");
                sc.nextLine();
                String mypassword = sc.nextLine();

                ps.setString(1, namnsc);
                ps.setString(2, usernamesc);
                ps.setString(3, Passwordsc);


                a = ps.executeUpdate();


                VerifyUser(myusername, mypassword);

            } catch (SQLException seql) {
                seql.printStackTrace();
            } finally {
                ps.close();
            }

        } finally {
            connectdb.closconnection();
        }
        return a != 0;

    }


    //this method is responsible for listing the users

    public ArrayList<Användare> Listing() throws SQLException {
        String sql = "SELECT * FROM användare";
        connectdb.openconnection();
        ArrayList<Användare> användarens = new ArrayList<Användare>();
        PreparedStatement ps = connectdb.prepareRequest(sql);
        try {
            ResultSet resultSet = ps.executeQuery();
            try {
                while (resultSet.next()) {

                    Long ID = resultSet.getLong(1);
                    String Namn = resultSet.getString(2);
                    String LoginId = resultSet.getString(3);
                    String password = resultSet.getString(4);
                    String Role = resultSet.getString(5);
                    Long id_djur = resultSet.getLong(6);
                    Användare användaren = new Användare(ID, Namn, LoginId, password, Role, id_djur);
                    användarens.add(användaren);
                    System.out.println("-----------------------");
                    System.out.println(ID);
                    System.out.println(Namn);
                    System.out.println(LoginId);
                    System.out.println(password);
                    System.out.println(Role);
                    System.out.println("-----------------------");

                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());

            } finally {
                ps.close();
            }
        } finally {
            connectdb.closconnection();
        }
        return användarens;
    }


    //this method is responsible for delete account
    // this method doesn't tested yet
    public boolean deletebyconfirm(Long ID, String username, String password) {
        int a = 0;

        String sql = "DELETE FROM användare WHERE ID =? AND username = ? AND password = ?";
        connectdb.openconnection();
        try {
            PreparedStatement ps = connectdb.prepareRequest(sql);
            try {

                ps.setLong(1, ID);
                ps.setString(2, username);
                ps.setString(3, password);
                a = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        } finally {
            connectdb.closconnection();
        }

        return a != 0;
    }


    //this method is responsible for deleting by ID
    public boolean delete(Long key) throws SQLException {
        int a = 0;

        String sql = "DELETE FROM användare WHERE ID=?";


        connectdb.openconnection();
        try {
            PreparedStatement ps = connectdb.prepareRequest(sql);


            try {
                ps.setLong(1, key);
                a = ps.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                ps.close();
            }
        } finally {
            connectdb.closconnection();
        }

        return a != 0;
    }

    //this method is responsible for listing the user by id
    //doesn't work yet
    public List<Användare> listing(String key) {
        return null;
    }
}


  /*  public Användare getAnvändaren(Long id) {
        Användare använd = null;

        try {

            PreparedStatement pstm = connectdb.prepareRequest("select * from användare where ID = ?");

            pstm.setLong(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                använd = new Användare();
                använd.setId(id);


            }


        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return använd;
    }

}*/

/*    Scanner scanner = new Scanner(System.in);
    String username = scanner.nextLine();
    String Password = scanner.nextLine();*/



/*public boolean VerifyUser(String username, String password) {
    boolean check= false;
    System.out.println("Enter your login ID");

  this.Loginid = username;

    System.out.println("enter your password");

    this.password=password;








try{




    connectdb.openconnection();

    connectdb.prepareRequest("SELECT * FROM användaren where LoginID =? and Password=? ");

    connectdb.preparedStatement.setString(1,username);
    connectdb.preparedStatement.setString(2,password);

ResultSet resultSet = connectdb.preparedStatement.executeQuery();
check= resultSet.next();


}catch(Exception exp){
exp.printStackTrace();
}

return check;


    }*/



/*


 boolean IsValid= false;
 connectdb.openconnection();
        PreparedStatement ps = null;
        ps =connectdb.prepareRequest(sql);
        try{
            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()){
                IsValid = true;
                String Role = resultSet.getString("Role");

                if(Role.equals("Admin")){
                    try{
                        r
                    }

                }

            }

        }catch(Exception exp){

        }
*/
