package services;

import Model.Val;

import java.util.ArrayList;

public interface Valop {
    ArrayList<Val> lista_alla_val();

    boolean tabortval(Long key);
}
