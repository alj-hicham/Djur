package services;

import java.sql.PreparedStatement;


//this interface will be usefull if in some case we want to change the database or to have many database
public interface Db_connection {

    void openconnection();

    void closconnection();


}
