package uv.mx.fei.logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import uv.mx.fei.dataaccess.DataBaseManager;
import uv.mx.fei.logic.domain.User;

public class UsersDAO {
    private final DataBaseManager dbm;
    
    public UsersDAO(){
        dbm = new DataBaseManager();
    }
    
    public int addUser(User user) throws SQLException{
        int result;
        String query = "INSERT INTO Usuarios(NumPersonal, nombre, tipoUsuario, fechaIngreso, fechaExpiración, gradoAcadémico, fechaNacimiento, nombreUsuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = dbm.getConnection().prepareStatement(query);

        statement.setInt(1, user.getStaffNumber());
        statement.setString(2, user.getName());
        statement.setString(3, user.getType());
        statement.setDate(4, user.getJoinDate());
        statement.setDate(5, user.getExpirationDate());
        statement.setString(6, user.getAcademicDegree());
        statement.setDate(7, user.getBirthDate());
        statement.setString(8, user.getUsername());

        result = statement.executeUpdate();
        dbm.closeConnection();

        return result;
    }
    
    public int modifyUser(User user) throws SQLException{
        int result;
        String query = "UPDATE TABLE Usuarios SET nombre = ?, tipoUsuario = ?, fechaIngreso = ?, fechaExpiración = ?, gradoAcadémico= ?, fechaNacimiento = ?, nombreUsuario = ? WHERE NumPersonal IN(?)";
        PreparedStatement statement = dbm.getConnection().prepareStatement(query);

        statement.setString(1, user.getName());
        statement.setString(2, user.getType());
        statement.setDate(3, user.getJoinDate());
        statement.setDate(4, user.getExpirationDate());
        statement.setString(5, user.getAcademicDegree());
        statement.setDate(6, user.getBirthDate());
        statement.setInt(7, user.getStaffNumber());
        statement.setString(8, user.getUsername());

        result = statement.executeUpdate();
        dbm.closeConnection();
        
        return result;
    }
    
    public ArrayList<User> getUserList() throws SQLException {
        ArrayList<User> userList = new ArrayList();
        String query = "SELECT NumPersonal, nombre, tipoUsuario, fechaIngreso, fechaExpiración, gradoAcadémico, fechaNacimiento, nombreUsuario FROM Usuarios";
        PreparedStatement statement = dbm.getConnection().prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            User user = new User();

            user.setStaffNumber(resultSet.getInt("NumPersonal"));
            user.setAcademicDegree(resultSet.getString("nombre"));
            user.setType(resultSet.getString("tipoUsuario"));
            user.setJoinDate(resultSet.getDate("fechaIngreso"));
            user.setExpirationDate(resultSet.getDate("fechaExpiración"));
            user.setAcademicDegree(resultSet.getString("gradoAcadémico"));
            user.setBirthDate(resultSet.getDate("fechaNacimiento"));
            user.setUsername(resultSet.getString("nombreUsuario"));

            userList.add(user);
        }
        dbm.closeConnection();
        
        return userList;
    }
    
    public String getAccessAccountTypeByUsername(String username) throws SQLException {
        String query = "SELECT tipoUsuario FROM Usuarios WHERE nombreUsuario=(?)";
        PreparedStatement preparedStatement = dbm.getConnection().prepareStatement(query);
        
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        dbm.closeConnection();
        String type = "";
        while (resultSet.next()) {
            type = resultSet.getString("tipoUsuario");
        }
        return type;
    }
    
    public boolean isUserValid(String username, String password) throws SQLException {
        boolean result;
        String query = "SELECT 1 FROM Usuarios WHERE nombreUsuario=(?) AND contrasena=(SHA2(?, 256))";
        
        PreparedStatement preparedStatement = dbm.getConnection().prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        result = resultSet.next();
        dbm.closeConnection();
        
        return result;
    }
    
    public boolean isUserAdmin(String username, String password) throws SQLException{
        boolean result;
        String query = "SELECT 1 FROM UsuarioS WHERE nombreUsuario = ?, AND contrasena =  AND tipoUsuario = 'Administrador'";
        
        PreparedStatement preparedStatement = dbm.getConnection().prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        result = resultSet.next();
        dbm.closeConnection();
        
        return result;
    }
}
