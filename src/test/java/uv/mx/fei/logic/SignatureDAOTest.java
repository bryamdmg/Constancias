package uv.mx.fei.logic;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uv.mx.fei.dataaccess.DataBaseManager;

public class SignatureDAOTest {
    private static DataBaseManager dbm;
    private static String signature;
    
    public SignatureDAOTest() {
        dbm = new DataBaseManager();
        signature = "consejoFEI2023";
    }
    
    @BeforeAll
    public static void setUpClass() {
        String query = "INSERT INTO Firmas(firma) VALUES(SHA2(?, 256))";
        
        try{
            PreparedStatement statement = dbm.getConnection().prepareCall(query);
            statement.setString(1, signature);
            
            statement.executeUpdate();
        }catch(SQLException exception){
            fail("Couldn't set up test class. Failed to connect to DB");
        }finally{
            dbm.closeConnection();
        }
    }
    
    @AfterAll
    public static void tearDownClass() {
        String query = "DELETE FROM Firmas WHERE firma LIKE SHA2(?, 256)";
        
        try{
            PreparedStatement statement = dbm.getConnection().prepareStatement(query);
            statement.setString(1, signature);
            
            statement.executeUpdate();
        }catch(SQLException exception){
            fail("Couldn't tear down test class. Failed to connect to DB");
        }finally{
            dbm.closeConnection();
        }
    }

    @Test
    public void testUpdateSignature() throws SQLException {
        SignatureDAO signatureDAO = new SignatureDAO();
        signature = "nuevaFirmaConstancias2023";
        
        assertTrue(signatureDAO.updateSignature(signature) > 0);
    }

    @Test
    public void testGetSignature() throws SQLException {
        SignatureDAO signatureDAO = new SignatureDAO();
        
        assertTrue(!signatureDAO.getSignature().isBlank());
    }
}
