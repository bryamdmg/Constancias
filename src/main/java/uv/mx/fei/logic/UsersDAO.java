package uv.mx.fei.logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uv.mx.fei.dataaccess.DataBaseManager;
import uv.mx.fei.logic.domain.User;

public class UsersDAO {
    private final DataBaseManager dbm;
    
    public UsersDAO(){
        dbm = new DataBaseManager();
    }
    
    public int addUser(User user) throws SQLException{
        int result = 0;
        String query = "INSERT INTO Usuarios(NumPersonal, nombre, tipoUsuario, fechaIngreso, fechaExpiración, gradoAcadémico, fechaNacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement statement = dbm.getConnection().prepareStatement(query);
            
            statement.setInt(1, user.getStaffNumber());
            statement.setString(2, user.getName());
            statement.setString(3, user.getUserType());
            statement.setDate(4, user.getJoinDate());
            statement.setDate(5, user.getExpirationDate());
            statement.setString(6, user.getAcademicDegree());
            statement.setDate(7, user.getBirthDate());
            
            result = statement.executeUpdate();
        }catch(SQLException exception){
            throw new SQLException("Failed to add user. Couldn't connect to DB");
        }
        
        return result;
    }
    
    public int modifyUser(User user) throws SQLException{
        int result = 0;
        String query = "UPDATE TABLE Usuarios SET nombre = ?, tipoUsuario = ?, fechaIngreso = ?, fechaExpiración = ?, gradoAcadémico= ?, fechaNacimiento = ? WHERE NumPersonal IN(?)";
        
        try{
            PreparedStatement statement = dbm.getConnection().prepareStatement(query);
            
            statement.setString(1, user.getName());
            statement.setString(2, user.getUserType());
            statement.setDate(3, user.getJoinDate());
            statement.setDate(4, user.getExpirationDate());
            statement.setString(5, user.getAcademicDegree());
            statement.setDate(6, user.getBirthDate());
            statement.setInt(7, user.getStaffNumber());
            
            result = statement.executeUpdate();
        }catch(SQLException exception){
            throw new SQLException("Failed to modify User. Couldn't connect to DB");
        }finally{
            dbm.closeConnection();
        }
        
        return result;
    }
    
    public ArrayList<User> getUserList() throws SQLException {
        ArrayList<User> userList = new ArrayList();
        String query = "SELECT NumPersonal, nombre, tipoUsuario, fechaIngreso, fechaExpiración, gradoAcadémico, fechaNacimiento FROM Usuarios";
        
        try{
            PreparedStatement statement = dbm.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                User user = new User();
                
                user.setStaffNumber(resultSet.getInt("NumPersonal"));
                user.setAcademicDegree(resultSet.getString("nombre"));
                user.setUserType(resultSet.getString("tipoUsuario"));
                user.setJoinDate(resultSet.getDate("fechaIngreso"));
                user.setExpirationDate(resultSet.getDate("fechaExpiración"));
                user.setAcademicDegree(resultSet.getString("gradoAcadémico"));
                user.setBirthDate(resultSet.getDate("fechaNacimiento"));
                
                userList.add(user);
            }
        }catch(SQLException exception){
            throw new SQLException("Failed to modify User. Couldn't connect to DB");
        }finally{
            dbm.closeConnection();
        }
        
        return userList;
    }
}
