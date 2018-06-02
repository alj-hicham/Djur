package DAO;

import Model.Användaren;
import Model.Djur;
import services.Djuroperation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Djuroperationimpl implements Djuroperation {
    Scanner scanner = new Scanner(System.in);
    Db_Connect connect = new Db_Connect();

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

        }

    }

    private void smartaellermysiga() {
        System.out.println("föredrar du smarta eller mysiga djur?");
        System.out.println("smarta : 1 ");
        System.out.println("mysiga : 2 ");
        int choice3 = scanner.nextInt();

        switch (choice3) {
            case 1:
                listbysmarta();
            case 2:

        }
    }

    private List<Djur> listbysmarta() {
    }

    public List<Djur> listdjurbynotfluffig() throws SQLException {
        String sql = "SELECT * FROM djur WHERE Fluffighet <5 ";
        List<Djur> djuren = new ArrayList<Djur>();
        connect.openconnection();

        PreparedStatement ps;
        ps = connect.prepareRequest(sql);

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

            System.out.println(djur_id_neg);
            System.out.println(Namn_neg);
            System.out.println(Fluffighet_neg);
            System.out.println(Mysighet_neg);
            System.out.println(Mordiskhet_neg);
            System.out.println(illalusket_neg);
            System.out.println(storlek_neg);

        }


        return djuren;

    }

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


        return djuren;
    }
}
