package DAO;

import Model.Användaren;
import services.Användarenoperation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class användarenimple implements Användarenoperation {
    Db_Connect connectdb = new Db_Connect();
    Användaren användaren = new Användaren();
    Djuroperationimpl djuroperationimpl = new Djuroperationimpl();
    Scanner sc = new Scanner(System.in);


    String usernamesc = användaren.getLoginiId();
    String Passwordsc = användaren.getPassword();
    String Namnsc = användaren.getNamn();


    public boolean VerifyUser(String username, String Password) {

        String req = "SELECT `ID`, `Namn`, `LoginId`, `password`, `Role` FROM anvandaren WHERE LoginId = ? and password = ?";
        boolean Isvalid = false;
        connectdb.openconnection();
        PreparedStatement ps = null;
        ps = connectdb.prepareRequest(req);





/*        System.out.println("username");
        this.Loginid = sc.nextLine();
        System.out.println("password");
        this.password = sc.nextLine();*/


        try {
            System.out.println("your username");
            this.usernamesc = sc.nextLine();

            System.out.println("your password");
            this.Passwordsc = sc.nextLine();
            ps.setString(1, usernamesc);
            ps.setString(2, Passwordsc);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Isvalid = true;
                String Role = resultSet.getString("Role");
                if (Role.equals("admin")) {

                    try {
                        System.out.println("1");
                        System.out.println("2");
                        System.out.println("3");
                        System.out.println("4");
                        int operation;
                        operation = sc.nextInt();
                        switch (operation) {
                            case 1:

                                djuroperationimpl.Startquestion();
                                break;
                            case 2:
                                Användaren anv = new Användaren();
                                Adduser(anv);
                                break;
                            case 3:
                                Listing();
                                break;
                            case 4:
                                System.out.println("please insert an id");
                                Long id = sc.nextLong();
                                delete(id);
                            default:
                                djuroperationimpl.Startquestion();
                        }

                    } catch (Exception e) {
                        System.err.println(e);

                    }
                } else {
                    try {
                        djuroperationimpl.Startquestion();
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }

            }

        } catch (SQLException e) {
            System.out.println(e);

        } finally {
            connectdb.closconnection();
        }


        return Isvalid;
    }


    public boolean Adduser(Användaren använd) {
        int a = 0;
        String req = "INSERT INTO anvandaren(Namn,LoginId,password) values (?,?,?)";


        connectdb.openconnection();
        PreparedStatement ps = null;
        ps = connectdb.prepareRequest(req);
        try {
            System.out.println("your name");
            this.Namnsc = sc.nextLine();

            System.out.println("user id");
            this.usernamesc = sc.nextLine();

            System.out.println("password");
            this.Passwordsc = sc.nextLine();
            ps.setString(1, Namnsc);
            ps.setString(2, usernamesc);
            ps.setString(3, Passwordsc);


            a = ps.executeUpdate();
            connectdb.closconnection();

        } catch (SQLException seql) {
            System.out.println(seql);
        }
        return a != 0;

    }

    public ArrayList<Användaren> Listing() {
        String sql = "SELECT * FROM anvandaren";
        connectdb.openconnection();
        ArrayList<Användaren> användarens = new ArrayList<Användaren>();
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
                Användaren användaren = new Användaren(ID, Namn, LoginId, password, Role);
                användarens.add(användaren);

                System.out.println(ID);
                System.out.println(Namn);
                System.out.println(LoginId);
                System.out.println(password);
                System.out.println(Role);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return användarens;
    }


    public List<Användaren> listing(String key) {
        return null;
    }

    public boolean delete(Long key) {
        int a = 0;
        String sql = "DELETE FROM anvandaren WHERE ID=?";


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

    public Användaren getAnvändaren(Long id) {
        Användaren använd = null;

        try {

            PreparedStatement pstm = connectdb.prepareRequest("select * from användaren where ID = ?");

            pstm.setLong(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                använd = new Användaren();
                använd.setId(id);


            }


        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return använd;
    }

}

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
