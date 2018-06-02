package services;

import java.util.ArrayList;
import java.util.List;

import Model.*;

public interface Användarenoperation {


    boolean VerifyUser(String Username, String password);

    boolean Adduser(Användaren användaren);

    ArrayList<Användaren> Listing();

    List<Användaren> listing(String key);

    boolean delete(Long key);

    Användaren getAnvändaren(Long id);


}
