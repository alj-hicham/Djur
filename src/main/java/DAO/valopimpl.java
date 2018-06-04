package DAO;

import Model.Användare;
import Model.Djur;
import Model.Val;
import services.Valop;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


//this method is responsible for choice of the animal of the user
public class valopimpl implements Valop {
    //fields
    private Db_Connect connect = new Db_Connect();
    private Scanner scanner = new Scanner(System.in);
    private användareimple användareimple = new användareimple();
    private Djur djur = new Djur();


    //this method is responsible by listing all the choice
    public ArrayList<Val> lista_alla_val() {
        String sql = "SELECT * FROM val";
        ArrayList<Val> val = new ArrayList<Val>();
        connect.openconnection();
        PreparedStatement ps = connect.prepareRequest(sql);

        try {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Long val_id = resultSet.getLong(1);
                Long id_anvandare = resultSet.getLong(2);
                Long id_djur = resultSet.getLong(3);
                Val valobject = new Val(val_id, id_anvandare, id_djur);
                val.add(valobject);

                System.out.println("ID val " + val_id);
                System.out.println("id användare " + id_anvandare);
                System.out.println("id djur" + id_djur);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return val;
    }

    // this method is responsible by deleting the choice by id
    //after listing the rest of the choice will be displayed
    //then its will redirect the user to the menu
    public boolean tabortval(Long key) {
        int a = 0;
        String sql = "DELETE FROM val WHERE ID= ? ";
        connect.openconnection();
        PreparedStatement ps = connect.prepareRequest(sql);
        try {
            ps.setLong(1, key);
            a = ps.executeUpdate();
            lista_alla_val();

            System.out.println("vill du komma tillbaka till meny eller vill du avsluta");
            System.out.println(" klick 1 för att kommer tilbacka");
            System.out.println(" klick 2 för att avsluta");
            try {
                int choice = scanner.nextInt();

                if (choice == 1) {


                    System.out.println("starta frågor: klick 1 ");
                    System.out.println(" skapa konto :klick 2  ");
                    System.out.println("lista användare : klick 3");
                    System.out.println("ta bort konto : klick 4");
                    System.out.println("lägg till ett djur : klick 5");
                    System.out.println("lista djuren : klick 6");
                    System.out.println("lista val : klick 7");
                    System.out.println("ta bot val :8");
                    System.out.println("avsluta : klick 9");

                    try {
                        int operation;
                        operation = scanner.nextInt();
                        switch (operation) {
                            case 1:
                                Djuroperationimpl djuroperationimpl = new Djuroperationimpl();
                                djuroperationimpl.Startquestion();
                                break;
                            case 2:
                                Användare anv = new Användare();
                                användareimple.Adduser(anv);
                                break;
                            case 3:
                                användareimple.Listing();
                                break;
                            case 4:
                                System.out.println("Vänligen ange ett korrekt nummer");
                                Long id = scanner.nextLong();
                                användareimple.delete(id);
                                break;
                            case 5:
                                Djuroperationimpl djuroperationimpl5 = new Djuroperationimpl();
                                djuroperationimpl5.addanimal(djur);
                                break;


                            case 6:
                                Djuroperationimpl djuroperationimpl6 = new Djuroperationimpl();
                                djuroperationimpl6.listadjuren();
                                break;
                            case 7:

                                lista_alla_val();
                                break;

                            case 8:
                                System.out.println("Vänligen ange ett korrekt nummer");
                                Long id_vall = scanner.nextLong();
                                tabortval(id_vall);
                                break;

                            case 9:
                                System.exit(0);

                            default:
                                Djuroperationimpl djuroperationimpldefault = new Djuroperationimpl();
                                djuroperationimpldefault.Startquestion();
                        }
                    } catch (InputMismatchException ex) {
                        System.out.println("Vänligen ange ett korrekt nummer");

                    }

                } else if (choice == 2) {
                    System.exit(0);
                }
            } catch (InputMismatchException e) {
                System.out.print(" Vänligen ange ett korrekt nummer");

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return a != 0;
    }
}