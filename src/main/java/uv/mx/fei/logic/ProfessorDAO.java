package uv.mx.fei.logic;

import uv.mx.fei.dataaccess.DataBaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessorDAO {

    public int getProfessorIdByUsername(String username) throws SQLException {
        String query = "SELECT numPersonal FROM usuarios WHERE nombreUsuario=(?)";
        DataBaseManager databaseManager = new DataBaseManager();
        Connection connection = databaseManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        int id = 0;
        while (resultSet.next()) {
            id = resultSet.getInt("numPersonal");
        }
        databaseManager.closeConnection();

        return id;
    }

}
