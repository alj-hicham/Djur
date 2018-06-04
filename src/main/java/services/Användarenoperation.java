package services;

import java.util.ArrayList;
import java.util.List;

import Model.*;

public interface Användarenoperation {


    boolean VerifyUser(String Username, String password);

    boolean Adduser(Användare användaren);

    ArrayList<Användare> Listing();

    List<Användare> listing(String key);

    boolean delete(Long key);


    boolean deletebyconfirm(Long id, String username, String password);


}
