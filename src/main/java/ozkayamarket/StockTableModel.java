/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ozkayamarket;

/*
 * #%L
 * Ozgun Ozkaya
 * %%
 * Copyright (C) 2017 Debreceni Egyetem
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * This method perform to provide column and row names in table 
 * 
 * @author ozgun
 */
public class StockTableModel extends AbstractTableModel implements TableModel{
    
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(StockTableModel.class);
    private List<Object[]> rowList = new ArrayList<>();
    private String[] columns = {"Stock ID", "Product ID", "Product Name", "Stock Comment", "Quantity", 
        "Buy", "Sell"};
    
    /**
     * Display the defined stocks  elements from the Oracle database in the Main Frame
     * 
     */
    public StockTableModel()
    {
        Connection conn = DbConnection.getDbConnection();
        log.info("Connected to Database for Main Stock Table");
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT s.STOCKID, s.PRODUCTID, pr.NAME, s.STOCKCOMMENT, s.QUANTITY, "
                    + "s.BUY, s.SELL  FROM OM_STOCK s INNER JOIN OM_PRODUCT pr ON s.PRODUCTID = pr.PRODUCTID");
            log.trace("Get Stock values from database");
            while (rs.next())
            {
                Object[] row = new Object[7];
                
                row[0] = rs.getString("STOCKID");
                row[1] = rs.getString("PRODUCTID");
                row[2] = rs.getString("NAME");
                row[3] = rs.getString("STOCKCOMMENT");
                row[4] = rs.getInt("QUANTITY");
                row[5] = rs.getDouble("BUY");
                row[6] = rs.getDouble("SELL");
                
                rowList.add(row);
               
            }
             log.info("Stock table updated from database");
        } catch (SQLException ex) {
            log.debug("Error Getting the all  Stock information from the  database");
          //  Logger.getLogger(StockTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
/**
   *
    */
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    
    @Override
    public int getRowCount() {
        return rowList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] row = rowList.get(rowIndex);
        return row[columnIndex];
    }
    
}
