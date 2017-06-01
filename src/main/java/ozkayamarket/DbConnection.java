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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Database Connection for Market Stocks
 * @author ozgun
 * @version 1.0
 * Created Date: 30.05.2017
 */
public class DbConnection {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DbConnection.class);
    
    private static Connection conn = null;
    
    public static Connection getDbConnection()
    {
        if (conn == null)
        {
            try {
                  DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            conn = DriverManager.getConnection("jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g", "ENG_K79IEQ", "ozgunozkaya");
            log.info("Database connection succesful");
        } catch (SQLException ex) {
            log.debug("Database Connection Failed");
           // Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
            return conn;
        }
        else return conn;
        
    }
    
}
