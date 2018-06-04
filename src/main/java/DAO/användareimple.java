package DAO;

import Model.Användare;

import Model.Djur;
import Model.Val;

import services.Användarenoperation;
import services.Djuroperation;

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
    private String Namnsc = användare.getNamn();
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
    public boolean VerifyUser(String username, String Password) {

        String req = "SELECT `ID`, `Namn`, `username`, `password`, `Role` FROM användare WHERE username = ? and password = ?";

        boolean Isvalid = false;
        connectdb.openconnection();
        PreparedStatement ps = null;
        ps = connectdb.prepareRequest(req);
        Val val = new Val();


        try {
            System.out.println("ditt användarnamn");
            this.usernamesc = sc.nextLine();

            System.out.println("ditt lösenord");
            this.Passwordsc = sc.nextLine();

            ps.setString(1, usernamesc);
            ps.setString(2, Passwordsc);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Isvalid = true;
                String Role = resultSet.getString("Role");


                if (Role.equals("admin")) {

                    try {
                        System.out.println("starta frågor: klick 1 ");
                        System.out.println(" skapa konto :klick 2  ");
                        System.out.println("lista användare : klick 3");
                        System.out.println("ta bort konto : klick 4");
                        System.out.println("lägg till ett djur : klick 5");
                        System.out.println("lista djuren : klick 6");
                        System.out.println("lista val : klick 7");
                        System.out.println("ta bort val :8");
                        System.out.println("avsluta : klick 9");

                        try {
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
                        } catch (InputMismatchException ex) {
                            System.out.println("Vänligen ange ett korrekt nummer");

                        }
                    } catch (Exception e) {
                        System.err.println(e);

                    }
                } else if (Role.equals("user")) {

                    try {
                        System.out.println("starta  : klick 1");
//                        System.out.println("ta bort konto : klick 2");
                        System.out.println("avsluta: klick 2");
                        try {
                            int operationuser = sc.nextInt();
                            switch (operationuser) {
                                case 1:
                                    djurop.Startquestion();
                                    break;
                              /*  case 2:
                                    System.out.println("your Id");
                                    sc.nextLong();
                                    Long id = sc.nextLong();
                                    System.out.println("Användarnamn");
                                    sc.nextLine();
                                    String Användarnamn = sc.nextLine();

                                    System.out.println("ditt lösenord");
                                    sc.nextLine();
                                    String lösenord = sc.nextLine();
                                   deletebyconfirm(id , Användarnamn,lösenord);
                                    break;*/
                                case 2:
                                    System.exit(0);

                            }

                        } catch (InputMismatchException inp) {
                            System.out.println("Vänligen ange ett korrekt nummer");

                        }

                    } catch (Exception e) {
                        System.err.println(e);
                    }
                } else {
                    System.out.println("vänligen ange ett korrekt nummer");

                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return Isvalid;
    }


    //this method is responsible for signup

    public boolean Adduser(Användare använd) {
        int a = 0;
        String req = "INSERT INTO användare(Namn,username,password) values (?,?,?)";


        connectdb.openconnection();
        PreparedStatement ps = null;
        ps = connectdb.prepareRequest(req);
        try {
            System.out.println("your name");
            this.Namnsc = sc.nextLine();
            System.out.println("username");
            this.usernamesc = sc.nextLine();

            System.out.println("password");
            this.Passwordsc = sc.nextLine();
            ps.setString(1, Namnsc);
            ps.setString(2, usernamesc);
            ps.setString(3, Passwordsc);


            a = ps.executeUpdate();
            String username = null;
            String password = null;

            VerifyUser(username, password);

        } catch (SQLException seql) {
            System.out.println(seql);
        }
        return a != 0;

    }


    //this method is responsible for listing the users

    public ArrayList<Användare> Listing() {
        String sql = "SELECT * FROM användare";
        connectdb.openconnection();
        ArrayList<Användare> användarens = new ArrayList<Användare>();
        PreparedStatement ps = null;
        ps = connectdb.prepareRequest(sql);
        try {
            ResultSet resultSet = ps.executeQuery();

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

        }
        return användarens;
    }


    //this method is responsible for delete account
    // this method doesn't tested yet
    public boolean deletebyconfirm(Long ID, String username, String password) {
        int a = 0;

        String sql = "DELETE FROM användare WHERE ID =? AND username = ? AND password = ?";
        connectdb.openconnection();
        PreparedStatement ps = connectdb.prepareRequest(sql);

        try {
            ps.setLong(1, ID);
            ps.setString(2, username);
            ps.setString(3, password);
            a = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);

        }

        return a != 0;
    }


    //this method is responsible for deleting by ID
    public boolean delete(Long key) {
        int a = 0;

        String sql = "DELETE FROM användare WHERE ID=?";


        connectdb.openconnection();
        PreparedStatement ps;
        ps = connectdb.prepareRequest(sql);


        try {
            ps.setLong(1, key);
            a = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        connectdb.closconnection();
        return a != 0
                ;
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
