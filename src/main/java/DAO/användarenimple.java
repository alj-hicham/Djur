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
    public int operation = sc.nextInt();
    PreparedStatement ps = null;
    String Loginid = användaren.getLoginiId();
    String password = användaren.getPassword();
    String Namn = användaren.getNamn();


    public boolean VerifyUser(String username, String Password) {
        String sql = "SELECT ID, Namn ,LoginId ,password,Role FROM anvandaren WHERE LoginID = '" + username + "' and Password = '" + Password + "' ";
        boolean Isvalid = false;

        System.out.println("username");
        this.Loginid = sc.nextLine();
        System.out.println("password");
        this.password = sc.nextLine();


        connectdb.openconnection();
        ps = connectdb.prepareRequest(sql);


        try {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Isvalid = true;
                String Role = resultSet.getString("Role");
                if (Role.equals("admin")) {
                    try {
                        System.out.println("1");
                        System.out.println("2");
                        System.out.println("3");
                        switch (operation) {
                            case 1:
                                djuroperationimpl.Startquestion();
                                break;
                            case 2:
                                Adduser(användaren);
                                break;

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


        System.out.println("user id");
        this.Loginid = sc.nextLine();
        System.out.println("password");
        this.password = sc.nextLine();
        System.out.println("your name");
        this.Namn = sc.nextLine();


        connectdb.openconnection();
        ps = connectdb.prepareRequest(req);
        try {
            ps.setString(1, Namn);
            ps.setString(2, Loginid);
            ps.setString(3, password);


            a = ps.executeUpdate();
            connectdb.closconnection();

        } catch (SQLException seql) {
            System.out.println(seql);
        }
        return a != 0;

    }


    public ArrayList<Användaren> Listing() {
        return null;
    }

    public List<Användaren> listing(String key) {
        return null;
    }

    public boolean delete(String key) {
        return false;
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
