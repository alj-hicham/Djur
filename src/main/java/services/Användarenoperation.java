package services;

import Model.Användare;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Användarenoperation {


    boolean VerifyUser(String Username, String password) throws SQLException;

    boolean Adduser(Användare användaren) throws SQLException;

    ArrayList<Användare> Listing() throws SQLException;

    List<Användare> listing(String key);

    boolean delete(Long key) throws SQLException;


    boolean deletebyconfirm(Long id, String username, String password);


}
