import java.sql.*;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * raptorAddGUI.java
 *
 * Created on Mar 24, 2009, 1:03:04 PM
 */

/**
 *
 * @author Gun Jack
 */
public class raptorAddGUI extends javax.swing.JFrame {

    /** Creates new form raptorAddGUI */
    public raptorAddGUI() {
        initComponents();

    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ratingTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ohGod2 = new javax.swing.JButton();
        ohGod = new javax.swing.JButton();
        addItemButton = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        titleTextField = new javax.swing.JTextField();
        yearReleasedTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        notesArea = new javax.swing.JTextArea();
        closeButton = new javax.swing.JButton();
        genreTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Type");

        jLabel2.setText("Genre");

        jLabel3.setText("Rating");

        jLabel4.setText("Notes");

        ohGod2.setVisible(false);
        ohGod2.setText("Raptor Recommends");
        ohGod2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ohGod2ActionPerformed(evt);
            }
        });

        ohGod.setVisible(false);
        ohGod.setText("Raptor Recommends");
        ohGod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ohGodActionPerformed(evt);
            }
        });

        addItemButton.setText("Add Item");
        addItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemButtonActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Movie", "Book", "Game", "CD" }));

        jLabel7.setText("Year Released");

        jLabel6.setText("Name");

        notesArea.setColumns(20);
        notesArea.setRows(5);
        notesArea.setMinimumSize(new java.awt.Dimension(6, 20));
        notesArea.setPreferredSize(new java.awt.Dimension(6, 20));
        jScrollPane2.setViewportView(notesArea);

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ratingTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                            .addComponent(titleTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                            .addComponent(genreTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ohGod2)
                                            .addComponent(ohGod)))
                                    .addComponent(yearReleasedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(addItemButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(closeButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ohGod2)
                    .addComponent(genreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ratingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ohGod))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(yearReleasedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addItemButton)
                    .addComponent(closeButton))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemButtonActionPerformed
          Object selectedMediaType = jComboBox1.getSelectedItem();
          String genreType = genreTextField.getText();
          String rating = ratingTextField.getText();
          String title = titleTextField.getText();
          String yearReleased = yearReleasedTextField.getText();
          String notes = notesArea.getText();
          /*do stuff with database here */
          Connection conn = null;
          try{
          Class.forName("org.sqlite.JDBC");
          conn = DriverManager.getConnection("jdbc:sqlite:test.db");
          Statement query = conn.createStatement();
          String s = "INSERT INTO Item(title, genre, rating, year, dateAdded, notes) VALUES ('" + title + "','" + genreType + "','" + rating + "','" + yearReleased + "', CURRENT_TIMESTAMP" + "','" + notes + "');";
          query.executeUpdate(s);
          }catch(Exception e){}
          finally{
              try{
                  if (conn != null) conn.close();} catch(SQLException e){}}

}//GEN-LAST:event_addItemButtonActionPerformed

    private void ohGod2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ohGod2ActionPerformed

    }//GEN-LAST:event_ohGod2ActionPerformed

    private void ohGodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ohGodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ohGodActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        
}//GEN-LAST:event_closeButtonActionPerformed

    /**
    * @param args the command line arguments
    */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addItemButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JTextField genreTextField;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea notesArea;
    private javax.swing.JButton ohGod;
    private javax.swing.JButton ohGod2;
    private javax.swing.JTextField ratingTextField;
    private javax.swing.JTextField titleTextField;
    private javax.swing.JTextField yearReleasedTextField;
    // End of variables declaration//GEN-END:variables

}
