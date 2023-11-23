package uv.mx.fei.logic;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import uv.mx.fei.dataaccess.DataBaseManager;
import uv.mx.fei.logic.domain.User;

public class UsersDAO {
    private final DataBaseManager dataBaseManager;
    
    public UsersDAO(){
        dataBaseManager = new DataBaseManager();
    }
    
    public int modifyUser(User user) throws SQLException{
        int result = 0;
        String query = "UPDATE TABLE Profesores SET nombre = ?, fechaIngreso = ?, fechaExpiración = ?, gradoAcadémico= ?, fechaNacimiento = ? WHERE NumPersonal IN(?)";
        
        try{
            PreparedStatement statement = dataBaseManager.getConnection().prepareStatement(query);
            
            statement.setString(1, user.getName());
            statement.setDate(2, user.getJoinDate());
            statement.setDate(3, user.getExpirationDate());
            statement.setString(4, user.getAcademicDegree());
            statement.setDate(5, user.getBirthDate());
            statement.setInt(6, user.getStaffNumber());
            
            result = statement.executeUpdate();
        }catch(SQLException exception){
            throw new SQLException("Couldn't connect to DB");
        }finally{
            dataBaseManager.closeConnection();
        }
        
        return result;
    }
}
