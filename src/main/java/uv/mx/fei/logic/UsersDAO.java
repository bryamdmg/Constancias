package uv.mx.fei.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uv.mx.fei.dataaccess.DataBaseManager;
import uv.mx.fei.logic.domain.User;

public class UsersDAO {
    private final DataBaseManager dataBaseManager;
    
    public UsersDAO(){
        dataBaseManager = new DataBaseManager();
    }
    
    public int modifyProfessor(User user) throws SQLException{
        int result = 0;
        String query = "UPDATE TABLE Usuarios SET nombre = ?, fechaIngreso = ?, fechaExpiración = ?, gradoAcadémico= ?, fechaNacimiento = ? WHERE NumPersonal IN(?)";
        
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

    public boolean isUserValid(String username, String password) throws SQLException {
        boolean result;
        String query = "SELECT 1 FROM Usuarios WHERE nombreUsuario=(?) AND contrasena=(SHA2(?, 256))";

        PreparedStatement preparedStatement = dataBaseManager.getConnection().prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        result = resultSet.next();
        dataBaseManager.closeConnection();

        return result;
    }


    public String getAccessAccountTypeByUsername(String username) throws SQLException {

        String query = "SELECT tipoUsuario FROM Usuarios WHERE nombreUsuario=(?)";

        PreparedStatement preparedStatement = dataBaseManager.getConnection().prepareStatement(query);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        dataBaseManager.closeConnection();

        String type = "";
        while (resultSet.next()) {
            type = resultSet.getString("tipoUsuario");
        }

        return type;
    }

    public List<User> getUsersList() throws SQLException {
        String query = "SELECT Id_usuario, nombreUsuario, tipoUsuario FROM Usuarios";

        PreparedStatement preparedStatement = dataBaseManager.getConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        dataBaseManager.closeConnection();

        List<User> accessAccountList = new ArrayList<>();
        while(resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("Id_usuario"));
            user.setUsername(resultSet.getString("nombreUsuario"));
            user.setType(resultSet.getString("tipoUsuario"));
            accessAccountList.add(user);
        }

        return accessAccountList;
    }
}
