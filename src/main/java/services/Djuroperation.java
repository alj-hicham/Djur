package services;

import Model.Djur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Djuroperation {

    void Startquestion() throws SQLException;

    List<Djur> listadjuren() throws SQLException;

    List<Djur> listdjurbynotfluffig() throws SQLException;

    List<Djur> listdjurbyflu() throws SQLException;

    boolean addanimal(Djur djur);

    List<Djur> listbysmarta() throws SQLException;

    List<Djur> listbyMysighet() throws SQLException;

    List<Djur> listbynoMordiskhet() throws SQLException;

    List<Djur> listbyMordiskhet() throws SQLException;
}
