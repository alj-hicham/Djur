package DAO;

import Model.Användare;
import Model.Djur;
import services.Djuroperation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


//this class is responsible for operation with animal in the database
public class Djuroperationimpl implements Djuroperation {
    //fields
    private Djur djura = new Djur();
    private Scanner scanner = new Scanner(System.in);
    private Db_Connect connect = new Db_Connect();


    //this method is responsible for question to the user
    //gillar du fluffiga djur

    public void Startquestion() throws SQLException {
        System.out.println("gillar du fluffiga djur");
        System.out.println("1 : ja");
        System.out.println("2: nej");
        int choice;


        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                try {
                    listdjurbyflu();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                listdjurbynotfluffig();
                break;

        }

        System.out.println("vill du fortsätta till nästa frågor eller välja en djur ");
        System.out.println(" frågor : 1 ");
        System.out.println(" välja: 2");
        int choice2;
        choice2 = scanner.nextInt();

        switch (choice2) {
            case 1:
                smartaellermysiga();
                break;
            case 2:
                System.out.println("Vänligen sätt in djurets id");

                Long id_u = scanner.nextLong();

                System.out.println("Vänligen sätt in användare id");

                Long id_anv = scanner.nextLong();

                valjadjuren(id_u, id_anv);


                break;
        }

    }


    //this method is responsible for the choice of the animal for the user
    //doesn't test it yet

    private boolean valjadjuren(Long id, Long id_anv) throws SQLException {

        int a = 0;

        String sql = "UPDATE djur SET Id_user=? WHERE Id_djur=" + id + "";


        connect.openconnection();
        try {

            PreparedStatement ps;
            ps = connect.prepareRequest(sql);

            try {

                ps.setLong(1, id_anv);


                a = ps.executeUpdate();


            } catch (SQLException ex) {
                ex.printStackTrace();

            } finally {
                ps.close();
            }
        } finally {
            connect.closconnection();
        }


        return a != 0;
    }


    // this method is responsible for the question number 2
    // föredrar du smarta eller mysiga djur
    private void smartaellermysiga() {
        System.out.println("föredrar du smarta eller mysiga djur?");
        System.out.println("smarta : 1 ");
        System.out.println("mysiga : 2 ");
        int choice3 = scanner.nextInt();

        switch (choice3) {
            case 1:
                try {
                    listbysmarta();
                    System.out.println("vill du fortsätta till nästa frågor eller välja en djur ");
                    System.out.println(" frågor : 1 ");
                    System.out.println("välja : 2");
                    int choice2 = scanner.nextInt();
                    switch (choice2) {
                        case 1:
                            System.out.println("Är det en nackdel om det finns en överhängande chans att djuret tar livet av dig?");
                            System.out.println("ja : 1");
                            System.out.println("nej : 2");

                            int choice4 = scanner.nextInt();
                            if (choice4 == 1) {

                                listbynoMordiskhet();
                            } else if (choice4 == 2) {
                                listbyMordiskhet();
                            }

                            break;
                        case 2:
                            System.out.println("Vänligen sätt in djurets id");
                            scanner.nextLong();
                            Long id_djur = scanner.nextLong();

                            System.out.println("Vänligen sätt in lösenord ");
                            scanner.nextLine();
                            Long id_anv = scanner.nextLong();


                            valjadjuren(id_djur, id_anv);
                            break;
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    listbyMysighet();
                    System.out.println("vill du fortsätta till nästa frågor eller välja en djur ");
                    System.out.println(" frågor : 1 ");
                    System.out.println("välja : 2");
                    break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    // this method is responsible for listing the animal by not mordiskhet
    // any animal greater than 5 for the mordiskhet will be not displayed
    public List<Djur> listbynoMordiskhet() throws SQLException {
        String sql = "SELECT * FROM djur WHERE Mordiskhet< 5";
        List<Djur> djurmordisk = new ArrayList<Djur>();
        connect.openconnection();
        try {
            PreparedStatement pstm = connect.prepareRequest(sql);
            try {
                ResultSet resultSet = pstm.executeQuery();

                while (resultSet.next()) {
                    Long djur_id_nomordisk = resultSet.getLong(1);
                    String Namn_nomornodisk = resultSet.getString(2);
                    int Fluffighet_nomordisk = resultSet.getInt(3);
                    int Mysighet_nomordisk = resultSet.getInt(4);
                    int Mordiskhet_nomordisk = resultSet.getInt(5);
                    int illalusket_nomordisk = resultSet.getInt(6);
                    int smarthet_nomordisk = resultSet.getInt(7);
                    int storlek_nomordisk = resultSet.getInt(8);
                    Djur djurs_nomordisk = new Djur(djur_id_nomordisk, Namn_nomornodisk, Fluffighet_nomordisk, Mysighet_nomordisk, Mordiskhet_nomordisk, illalusket_nomordisk, smarthet_nomordisk, storlek_nomordisk);
                    djurmordisk.add(djurs_nomordisk);
                    System.out.println("----------------------------");
                    System.out.println("djur id" + djur_id_nomordisk);
                    System.out.println("djur namn" + Namn_nomornodisk);
                    System.out.println("fluffighet" + Fluffighet_nomordisk);
                    System.out.println("mysighet : " + Mysighet_nomordisk);
                    System.out.println("Mordiskhet " + Mordiskhet_nomordisk);
                    System.out.println("illaluktande" + illalusket_nomordisk);
                    System.out.println("storlek : " + storlek_nomordisk);
                    System.out.println("smarthet : " + smarthet_nomordisk);
                    System.out.println("----------------------------");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                pstm.close();
            }
        } finally {
            connect.closconnection();
        }
        return djurmordisk;

    }


    //this method is responsible for listing the animal by mordiskhet
    //any animal with mordiskhet is greater than 5 will be displayed

    public List<Djur> listbyMordiskhet() throws SQLException {
        String sql = "SELECT * FROM djur WHERE Mordiskhet>5";
        List<Djur> djurmordisk = new ArrayList<Djur>();
        connect.openconnection();
        try {
            PreparedStatement ps = connect.prepareRequest(sql);
            try {
                ResultSet resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    Long djur_id_mordiskhet = resultSet.getLong(1);
                    String Namn_mordiskhet = resultSet.getString(2);
                    int Fluffighet_mordiskhet = resultSet.getInt(3);
                    int Mysighet_mordiskhet = resultSet.getInt(4);
                    int Mordiskhet_mordiskhet = resultSet.getInt(5);
                    int illalusket_mordiskhet = resultSet.getInt(6);
                    int smarthet_mordiskhet = resultSet.getInt(7);
                    int storlek_mordiskhet = resultSet.getInt(8);
                    Djur djurs_mordiskhet = new Djur(djur_id_mordiskhet, Namn_mordiskhet, Fluffighet_mordiskhet, Mysighet_mordiskhet, Mordiskhet_mordiskhet, illalusket_mordiskhet, smarthet_mordiskhet, storlek_mordiskhet);
                    djurmordisk.add(djurs_mordiskhet);


                    System.out.println("----------------------------");
                    System.out.println("djur id :" + djur_id_mordiskhet);
                    System.out.println("djur namn :" + Namn_mordiskhet);
                    System.out.println("fluffighet :" + Fluffighet_mordiskhet);
                    System.out.println("mysighet :" + Mysighet_mordiskhet);
                    System.out.println("Mordiskhet: " + Mordiskhet_mordiskhet);
                    System.out.println("illaluktande : " + illalusket_mordiskhet);
                    System.out.println("storlek : " + storlek_mordiskhet);
                    System.out.println("smarthet :" + smarthet_mordiskhet);
                    System.out.println("----------------------------");


                }

            } finally {

                ps.close();

            }
        } finally {
            connect.closconnection();
        }
        return djurmordisk;
    }


    // this method is responsible for listing the animal by inteligence
    // any animal with smarthet is greater than mysighet will be displayed
    public List<Djur> listbysmarta() throws SQLException {

        String sql = "SELECT * FROM djur WHERE smarthet > Mysighet";
        List<Djur> djursmarta = new ArrayList<Djur>();
        connect.openconnection();
        try {
            PreparedStatement ps = connect.prepareRequest(sql);
            try {
                ResultSet resultSet = ps.executeQuery();

                while (resultSet.next()) {

                    Long djur_id_smarta = resultSet.getLong(1);
                    String Namn_smarta = resultSet.getString(2);
                    int Fluffighet_smarta = resultSet.getInt(3);
                    int Mysighet_smarta = resultSet.getInt(4);
                    int Mordiskhet_smarta = resultSet.getInt(5);
                    int illalusket_smarta = resultSet.getInt(6);
                    int smarthet_smarta = resultSet.getInt(7);
                    int storlek_smarta = resultSet.getInt(8);
                    Djur djurs_neg = new Djur(djur_id_smarta, Namn_smarta, Fluffighet_smarta, Mysighet_smarta, Mordiskhet_smarta, illalusket_smarta, smarthet_smarta, storlek_smarta);
                    djursmarta.add(djurs_neg);
                    System.out.println("----------------------------");
                    System.out.println("djur id : " + djur_id_smarta);
                    System.out.println("djur namn : " + Namn_smarta);
                    System.out.println("Fluffighet :" + Fluffighet_smarta);
                    System.out.println("mysighet :" + Mysighet_smarta);
                    System.out.println("mordoshet" + Mordiskhet_smarta);
                    System.out.println("illaluktande :" + illalusket_smarta);
                    System.out.println("storlek :" + storlek_smarta);
                    System.out.println("smarthet :" + smarthet_smarta);
                    System.out.println("----------------------------");

                }
            } finally {
                ps.close();
            }
        } finally {
            connect.closconnection();
        }
        return djursmarta;
    }


    //this method is responsible for listing the animal by mysighet
    //any animal that have mysighet greater then smarthet will be displayed
    public List<Djur> listbyMysighet() throws SQLException {
        String sql = "SELECT * FROM djur WHERE Mysighet > smarthet";

        List<Djur> djurmysighet = new ArrayList<Djur>();

        connect.openconnection();
        try {
            PreparedStatement ps = connect.prepareRequest(sql);
            try {
                ResultSet resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    Long djur_id_mysig = resultSet.getLong(1);
                    String Namn_mysig = resultSet.getString(2);
                    int Fluffighet_mysig = resultSet.getInt(3);
                    int Mysighet_mysig = resultSet.getInt(4);
                    int Mordiskhet_mysig = resultSet.getInt(5);
                    int illalusket_mysig = resultSet.getInt(6);
                    int smarthet_mysig = resultSet.getInt(7);
                    int storlek_mysig = resultSet.getInt(8);
                    Djur djurs_mysig = new Djur(djur_id_mysig, Namn_mysig, Fluffighet_mysig, Mysighet_mysig, Mordiskhet_mysig, illalusket_mysig, smarthet_mysig, storlek_mysig);
                    djurmysighet.add(djurs_mysig);
                    System.out.println("----------------------------");
                    System.out.println("djur id : " + djur_id_mysig);
                    System.out.println("djur namn :" + Namn_mysig);
                    System.out.println("fluffighet : " + Fluffighet_mysig);
                    System.out.println("mysighet " + Mysighet_mysig);
                    System.out.println("mordiskhet " + Mordiskhet_mysig);
                    System.out.println("illalusktande" + illalusket_mysig);
                    System.out.println("smarthet" + smarthet_mysig);
                    System.out.println("storlek : " + storlek_mysig);
                    System.out.println("----------------------------");

                }
            } finally {
                ps.close();
            }
        } finally {
            connect.closconnection();
        }


        return djurmysighet;
    }


    //this method is responsible by listing the animal by not fluffy
    //any animal that have fluffighet lower than 5 will be displayed

    public List<Djur> listdjurbynotfluffig() throws SQLException {
        String sql = "SELECT * FROM djur WHERE Fluffighet <5 ";
        List<Djur> djuren = new ArrayList<Djur>();
        connect.openconnection();
        try {
            PreparedStatement ps = connect.prepareRequest(sql);
            try {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    Long djur_id_neg = resultSet.getLong(1);
                    String Namn_neg = resultSet.getString(2);
                    int Fluffighet_neg = resultSet.getInt(3);
                    int Mysighet_neg = resultSet.getInt(4);
                    int Mordiskhet_neg = resultSet.getInt(5);
                    int illalusket_neg = resultSet.getInt(6);
                    int smarthet_neg = resultSet.getInt(7);
                    int storlek_neg = resultSet.getInt(8);
                    Djur djurs_neg = new Djur(djur_id_neg, Namn_neg, Fluffighet_neg, Mysighet_neg, Mordiskhet_neg, illalusket_neg, smarthet_neg, storlek_neg);
                    djuren.add(djurs_neg);
                    System.out.println("----------------------------");
                    System.out.println("djur id :" + djur_id_neg);
                    System.out.println("djur namn :" + Namn_neg);
                    System.out.println("fluffighet : " + Fluffighet_neg);
                    System.out.println("mysighet : " + Mysighet_neg);
                    System.out.println("mordiskhet : " + Mordiskhet_neg);
                    System.out.println("illlaluktande : " + illalusket_neg);
                    System.out.println("smarthet : " + smarthet_neg);
                    System.out.println("storlek : " + storlek_neg);
                    System.out.println("----------------------------");

                }

            } finally {
                ps.close();
            }
        } finally {
            connect.closconnection();
        }
        return djuren;

    }


    // this method is responsible by listing the animal by fluffi
    // any animal that have fluffi greater than 5 will be displayed
    public List<Djur> listdjurbyflu() throws SQLException {
        String sql = "SELECT * FROM djur WHERE Fluffighet >5 ";
        List<Djur> djuren = new ArrayList<Djur>();
        connect.openconnection();

        PreparedStatement ps;
        ps = connect.prepareRequest(sql);

        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            Long djur_id = resultSet.getLong(1);
            String Namn = resultSet.getString(2);
            int Fluffighet = resultSet.getInt(3);
            int Mysighet = resultSet.getInt(4);
            int Mordiskhet = resultSet.getInt(5);
            int illaluktande = resultSet.getInt(6);
            int smarthet = resultSet.getInt(7);
            int storlek = resultSet.getInt(8);
            Djur djurs = new Djur(djur_id, Namn, Fluffighet, Mysighet, Mordiskhet, illaluktande, smarthet, storlek);
            djuren.add(djurs);

            System.out.println("----------------------------");
            System.out.println("djur id : " + djur_id);
            System.out.println("djur_namn :" + Namn);
            System.out.println("fluffighet : " + Fluffighet);
            System.out.println("mysighet :" + Mysighet);
            System.out.println("mordiskhet :" + Mordiskhet);
            System.out.println("illaluktande :" + illaluktande);
            System.out.println("smarthet : " + smarthet);
            System.out.println("storlek : " + storlek);
            System.out.println("----------------------------");
        }
/*
*         while (resultSet.next()) {
            Long djur_id = resultSet.getLong(1);
            String Namn = resultSet.getString(2);
            int Fluffighet = resultSet.getInt(3);
            int Mysighet = resultSet.getInt(4);
            int Mordiskhet = resultSet.getInt(5);
            int illalusket = resultSet.getInt(6);
            int smarthet = resultSet.getInt(7);
            int storlek = resultSet.getInt(8);
            Djur djurs = new Djur(djur_id, Namn, Fluffighet, Mysighet, Mordiskhet, illalusket, smarthet, storlek);
            djuren.add(djurs);

            System.out.println(djur_id);
            System.out.println(Namn);
            System.out.println(Fluffighet);
            System.out.println(Mysighet);
            System.out.println(Mordiskhet);
            System.out.println(illalusket);
            System.out.println(storlek);

        }
* */

        return djuren;
    }


    //this method is responsible for adding animal by the administrator
    public boolean addanimal(Djur djur) {
        int a = 0;
        String sql = "INSERT INTO djur(Namn, Fluffighet, Mysighet, Mordiskhet, illaluktande, smarthet, storlek) VALUES (?,?,?,?,?,?,?) ";
        connect.openconnection();
        PreparedStatement ps = connect.prepareRequest(sql);
        try {
            System.out.println("djur namn");
            String djurnamn = scanner.nextLine();
            System.out.println("Fluffighet ");
            int fluffighet = scanner.nextInt();
            System.out.println("Mysighet ");
            int Mysighet = scanner.nextInt();
            System.out.println("mordiskhet ");
            int mordiskhet = scanner.nextInt();
            System.out.println("illaluktande ");
            int illaluktande = scanner.nextInt();
            System.out.println("smarthet ");
            int smarthet = scanner.nextInt();
            System.out.println("storlek");
            int storlek = scanner.nextInt();
            ps.setString(1, djurnamn);
            ps.setInt(2, fluffighet);
            ps.setInt(3, Mysighet);
            ps.setInt(4, mordiskhet);
            ps.setInt(5, illaluktande);
            ps.setInt(6, smarthet);
            ps.setInt(7, storlek);

            a = ps.executeUpdate();
            listadjuren();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return a != 0;
    }


    //this method is responsible by listing all animal by administrator

    public List<Djur> listadjuren() throws SQLException {
        String sql = "SELECT * FROM djur";
        ArrayList<Djur> djuren = new ArrayList<Djur>();
        connect.openconnection();
        PreparedStatement ps = connect.prepareRequest(sql);
        try {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Long id_djur = resultSet.getLong(1);
                String namn = resultSet.getString(2);
                int fluffighet = resultSet.getInt(3);
                int Misighet = resultSet.getInt(4);
                int mordiskhet = resultSet.getInt(5);
                int illaluktande = resultSet.getInt(6);
                int smarthet = resultSet.getInt(7);
                int storlek = resultSet.getInt(8);
                Djur djurs = new Djur(id_djur, namn, fluffighet, Misighet, mordiskhet, illaluktande, smarthet, storlek);
                djuren.add(djurs);
                System.out.println("---------------------------");
                System.out.println("id djur :" + id_djur);
                System.out.println("djur " + namn);
                System.out.println("fluffighet" + fluffighet);
                System.out.println("misyghet" + Misighet);
                System.out.println("mordisket" + mordiskhet);
                System.out.println("illaluktande" + illaluktande);
                System.out.println("smarthet" + smarthet);
                System.out.println("storlek" + storlek);
                System.out.println("------------------------------");
            }
            System.out.println("vill du komma tillbaka till meny eller vill du avsluta");
            System.out.println(" klick 1 för att kommer tilbacka");
            System.out.println(" klick 2 för att avsluta");
            int operation = scanner.nextInt();
            try {
                if (operation == 1) {
                    System.out.println("starta frågor: klick 1 ");
                    System.out.println(" skapa konto :klick 2  ");
                    System.out.println("lista användare : klick 3");
                    System.out.println("ta bort konto : klick 4");
                    System.out.println("lägg till ett djur : klick 5");
                    System.out.println("lista djuren : klick 6");
                    System.out.println("lista val : klick  7");
                    System.out.println("ta bot val : klick 8");
                    System.out.println("avsluta : klick 9");
                    try {
                        int goback = scanner.nextInt();
                        switch (goback) {
                            case 1:
                                Startquestion();
                                break;
                            case 2:
                                användareimple användareimpleobj = new användareimple();
                                Användare users = new Användare();
                                användareimpleobj.Adduser(users);
                                break;
                            case 3:
                                användareimple användareimpleobj2 = new användareimple();
                                användareimpleobj2.Listing();
                                break;
                            case 4:
                                System.out.print("Vänligen ange ett korrekt nummer");
                                Long id = scanner.nextLong();
                                användareimple användareimpleobj3 = new användareimple();
                                användareimpleobj3.delete(id);
                                break;
                            case 5:
                                addanimal(djura);
                                break;
                            case 6:
                                listadjuren();
                                break;
                            case 7:
                                valopimpl valopimplobject = new valopimpl();
                                valopimplobject.lista_alla_val();
                                break;
                            case 8:
                                System.out.println("Vänligen ange ett korrekt nummer");
                                Long id_val = scanner.nextLong();
                                valopimpl valopimplobject8 = new valopimpl();
                                valopimplobject8.tabortval(id_val);
                                break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Vänligen ange ett korrekt nummer");

                    }
                } else if (operation == 2) {
                    System.exit(0);

                }
            } catch (InputMismatchException e) {
                System.out.println("Vänligen ange ett korrekt nummer");
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return djuren;
    }

/*public ArrayList<Djur> listdjur() throws SQLException{
        String sql = "SELECT * from djur";
        ArrayList<Djur> djurs = new ArrayList<Djur>();
        PreparedStatement pstm = connect.prepareRequest(sql);

        try{
            ResultSet rs =pstm.executeQuery();

            Long djurs_id = rs.getLong(1);
            String namn = rs.getString(2);
            int fluffighet = rs.getInt(3);
            int Mysighet = rs.getInt(4);
            int mordiskhet = rs.getInt(5);
            int illaluktande = rs.getInt(6);
            int smarthet = rs.getInt(7);
            int storlek = rs.getInt(8);
            Djur mindjur = new Djur(djurs_id,namn,fluffighet,Mysighet,mordiskhet,illaluktande,smarthet,storlek);
        djurs.add(mindjur);

        System.out.println("id djur " + djurs_id);
        System.out.println("namn" + namn);
        System.out.println("fluffighet"+ fluffighet );
        System.out.println("Mysighet"+ Mysighet);
        System.out.println("mordiskhet" + mordiskhet);
        System.out.println("illaluktande"+ illaluktande);
        System.out.println("smarthet" + smarthet);
        System.out.println("storlek" + storlek);

        System.out.println("-----------------------");
        }catch(NullPointerException exe){
            System.err.print(exe);

        }


return djurs;}*/
}
