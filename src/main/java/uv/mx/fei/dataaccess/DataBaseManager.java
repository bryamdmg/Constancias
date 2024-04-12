package uv.mx.fei.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class DataBaseManager {
    private Connection connection;
    
    public Connection getConnection() throws SQLException {
        this.connect();
        return connection;
    }

    public void closeConnection(){
        if(connection != null){
            try{
                if(!connection.isClosed()){
                    connection.close();
                }
            }catch(SQLException exception){
                Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, exception);
            }
        }
    }

    private void connect() throws SQLException{
        connection = DriverManager.getConnection("jdbc:mariadb://localhost/Constancias", "usuarioConstancias", "uvConstancias2023");
    }
}