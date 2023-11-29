package uv.mx.fei.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public int getUserValidation(String username, String password) throws SQLException {
        int result = 0;
        String query = "SELECT 1 FROM Usuarios WHERE nombreUsuario=(?) AND contrasena=(SHA2(?, 256))";

        PreparedStatement preparedStatement = dataBaseManager.getConnection().prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        result = preparedStatement.executeUpdate();
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
}
