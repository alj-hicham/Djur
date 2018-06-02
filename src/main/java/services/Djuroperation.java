package services;

import Model.Djur;

import java.sql.SQLException;
import java.util.List;

public interface Djuroperation {

    void Startquestion() throws SQLException;

    List<Djur> listdjurbynotfluffig() throws SQLException;

    List<Djur> listdjurbyflu() throws SQLException;
}
