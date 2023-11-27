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
        
        try{
            PreparedStatement statement = dbm.getConnection().prepareStatement(query);
            
            statement.setString(1, query);
            
            result = statement.executeUpdate();
        }catch(SQLException exception){
            throw new SQLException("Couldn't update data. There's no connection to DB");
        }finally{
            dbm.closeConnection();
        }
        
        return result;
    }
    
    public String getSignature() throws SQLException{
        String signature = "";
        String query = "SELECT firma FROM Firmas";
        
        try{
            PreparedStatement statement = dbm.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                signature = resultSet.getString("firma");
            }
        }catch(SQLException exception){
            throw new SQLException();
        }finally{
            dbm.closeConnection();
        }
        
        return signature;
    }
}
