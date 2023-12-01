package uv.mx.fei.logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import uv.mx.fei.dataaccess.DataBaseManager;

public class SignatureDAO {
    private DataBaseManager dbm;
    
    public SignatureDAO(){
        dbm = new DataBaseManager();
    }
    
    public int updateSignature(String signature) throws SQLException{
        int result = 0;
        String query = "UPDATE Firmas SET firma = SHA2(?, 256)";
        PreparedStatement statement = dbm.getConnection().prepareStatement(query);
        statement.setString(1, query);

        result = statement.executeUpdate();
        dbm.closeConnection();

        return result;
    }
    
    public String getSignature() throws SQLException{
        String signature = "";
        String query = "SELECT firma FROM Firmas";
        PreparedStatement statement = dbm.getConnection().prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            signature = resultSet.getString("firma");
        }
        
        dbm.closeConnection();
        
        return signature;
    }
}
