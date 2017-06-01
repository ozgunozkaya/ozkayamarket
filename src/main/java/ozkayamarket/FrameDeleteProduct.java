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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author ozgun
 */
public class FrameDeleteProduct extends javax.swing.JFrame {

    /**
     * Creates new form FrameDeleteProduct
     */
    public FrameDeleteProduct() {
        initComponents();
    }
private static final org.slf4j.Logger log = LoggerFactory.getLogger(FrameDeleteProduct.class);
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbProduct = new javax.swing.JComboBox<>();
        btnDeleteProduct = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Delete Product");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Choose Product:");

        btnDeleteProduct.setText("Delete Product");
        btnDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDeleteProduct)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDeleteProduct)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
 * This button perform to take the chosen product from the combobox  by user and
 * Delete the chosen product from the Oracle Database
 * @param evt 
 */
    private void btnDeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProductActionPerformed
        Connection conn = DbConnection.getDbConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM OM_PRODUCT WHERE productId = ?");
            
            String selectedItem = (String) cmbProduct.getSelectedItem(); // Secılen urunu al comboboxdan
            String[] selectedItemSplit = selectedItem.split(" - "); // " - " a gore ayır ikiye split geriye bir array dondurur
            String productId = selectedItemSplit[0]; // birinci eleman her zaman product ıd idir.
            
            ps.setString(1, productId);
            log.trace("Product  Selected");
            ps.executeUpdate();
            cmbProduct.removeAllItems();
            formWindowOpened(null);
            JOptionPane.showMessageDialog(null, "The Product has been deleted.", "The product deleted", 
                    JOptionPane.INFORMATION_MESSAGE);
            log.info("The product deleted from database");
        } catch (SQLException ex) {
           log.debug("Error Deleting the Product from the  database");
           //Logger.getLogger(FrameDeleteProduct.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnDeleteProductActionPerformed
/**
 * This method display the Delete Product Frame and
 * Get the defined product Id's in the combobox
 * @param evt 
 */
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Connection conn = DbConnection.getDbConnection();
         log.info("Connected to Database For Product Deleting Frame");
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT PRODUCTID, NAME FROM OM_PRODUCT");

            while (rs.next()) {
                cmbProduct.addItem(rs.getString("PRODUCTID") + " - " + rs.getString("NAME"));
            }

        } catch (SQLException ex) {
          
           log.debug("Error Getting the ProductID from database");
        //  Logger.getLogger(FrameDeleteProduct.class.getName()).log(Level.SEVERE, null, ex);
          
        }


    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameDeleteProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameDeleteProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameDeleteProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameDeleteProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameDeleteProduct().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteProduct;
    private javax.swing.JComboBox<String> cmbProduct;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
