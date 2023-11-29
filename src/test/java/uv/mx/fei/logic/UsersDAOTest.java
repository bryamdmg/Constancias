package uv.mx.fei.logic;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import uv.mx.fei.dataaccess.DataBaseManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uv.mx.fei.logic.domain.User;

public class UsersDAOTest {
    private static DataBaseManager dbm;
    private static User user;
    
    public UsersDAOTest() {
        dbm = new DataBaseManager();
        
        user = new User();
        user.setStaffNumber(12345);
        user.setName("Erica meneses rico");
        user.setAcademicDegree("Licenciatura");
        user.setJoinDate(Date.valueOf(LocalDate.of(2010, Month.FEBRUARY, 28)));
        user.setExpirationDate(Date.valueOf(LocalDate.of(2040, Month.DECEMBER, 20)));
        user.setBirthDate(Date.valueOf(LocalDate.of(1990, Month.JULY, 30)));
    }
    
    @BeforeAll
    public static void setUpClass() {
        String query = "INSERT INTO Profesores(NumPersonal, nombre, fechaIngreso, fechaExpiración, gradoAcadémico, fechaNacimiento) VALUES(?, ?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement statement = dbm.getConnection().prepareStatement(query);
            
            statement.setInt(1, user.getStaffNumber());
            statement.setString(2, user.getName());
            statement.setDate(3, user.getJoinDate());
            statement.setDate(4, user.getExpirationDate());
            statement.setString(5, user.getAcademicDegree());
            statement.setDate(6, user.getBirthDate());
            
            statement.executeUpdate();
        }catch(SQLException exception){
            fail("Failed to set up tests. Couldn't connect to DB");
        }
    }
    
    @AfterAll
    public static void tearDownClass() {
        String query = "DELETE FROM Profesores WHERE NumPersonal IN(?)";
        
        try{
            PreparedStatement statement = dbm.getConnection().prepareStatement(query);
            
            statement.setInt(1, user.getStaffNumber());
            
            statement.executeUpdate();
        }catch(SQLException exception){
            fail("Failed to tear down class. Couldn't connect to DB");
        }
                
    }

    @Test
    public void testModifyUserSuccess() throws SQLException {
        UsersDAO userDAO = new UsersDAO();
        
        user.setAcademicDegree("Maestría");
        user.setName("Erika Meneses Rico");
        
        assertTrue(userDAO.modifyProfessor(user) > 0);
    }
    
    @Test
    public void testModifyUserFail() throws SQLException {
        
    }
}
