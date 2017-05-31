/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ozkayamarkettesting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import junit.framework.Assert;
import static org.junit.Assert.*;
import ozkayamarket.DbConnection;

/**
 *
 * @author BOSS
 */
public class stocktesting {
    
    @Test
    public void DeletingProductTest() throws SQLException
    {
        Connection conn = DbConnection.getDbConnection();
        Statement st = conn.createStatement();
        ResultSet currentProductCountResultSet = st.executeQuery("SELECT COUNT(*) FROM OM_PRODUCT");
        
        currentProductCountResultSet.next();
        int currentProductCount = currentProductCountResultSet.getInt(1);
        
        PreparedStatement ps = conn.prepareStatement("DELETE FROM OM_PRODUCT WHERE PRODUCTID LIKE ?");
            
        ps.setInt(1, 500);
        ps.executeUpdate();
            
        currentProductCountResultSet = st.executeQuery("SELECT COUNT(*) FROM OM_PRODUCT");
        
        currentProductCountResultSet.next();
        int addedProductCount = currentProductCountResultSet.getInt(1);    
        
        assertNotEquals(currentProductCount, addedProductCount);
    }
    // tablo nerede database tablosu oracle'da
   @Test
    public void AddingProductTest() throws SQLException{
        Connection conn = DbConnection.getDbConnection();
        Statement st = conn.createStatement();
        ResultSet currentProductCountResultSet = st.executeQuery("SELECT COUNT(*) FROM OM_PRODUCT");
        
        currentProductCountResultSet.next();
        int currentProductCount = currentProductCountResultSet.getInt(1);
        
        PreparedStatement ps = conn.prepareStatement("INSERT INTO OM_PRODUCT VALUES (?,?,?)");
            
        ps.setInt(1, 600);
            ps.setString(2,"Test");
            ps.setString(3, "Test 2");
        
            ps.executeUpdate();
            
        currentProductCountResultSet = st.executeQuery("SELECT COUNT(*) FROM OM_PRODUCT");
        
        currentProductCountResultSet.next();
        int addedProductCount = currentProductCountResultSet.getInt(1);    
        
        assertNotEquals(currentProductCount, addedProductCount);
        
      // proje neerede hangisi
      // bu test class sanirim
      //aynen test clasıı ama yanlıs benımkı bakiyorum ismdi bana bi musaade
      
    }
}