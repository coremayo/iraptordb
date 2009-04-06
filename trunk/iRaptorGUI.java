package iRaptorPackage;

import java.sql.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * iRaptorGUITest.java
 *
 * Created on Mar 23, 2009, 2:59:07 PM
 */

/**
 *
 * @author Tom Faine
 */
public class iRaptorGUI extends javax.swing.JFrame {

    /** Creates new form iRaptorGUITest */
    public iRaptorGUI() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tabPane = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        gameTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        CDTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        movieTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        bookTable = new javax.swing.JTable();
        addItemButton = new javax.swing.JButton();
        removeItemButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        recommendButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("iRaptor");

        gameTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "idNum", "Name", "Genre", "Rating", "Date Added", "Year Released", "Tags", "Other"
            }
        ));
        gameTable.setColumnSelectionAllowed(false);
        jScrollPane3.setViewportView(gameTable);

        tabPane.addTab("Games", jScrollPane3);

        CDTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "idNum", "Name", "Artists", "Genre", "Rating", "Year Released", "Date Added", "Tags", "Other"
            }
        ));
        CDTable.setColumnSelectionAllowed(false);
        jScrollPane4.setViewportView(CDTable);

        tabPane.addTab("CDs", jScrollPane4);

        movieTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "idNum", "Title", "Genre", "Rating", "Year Released", "Date Added", "Notes"
            }
        ));
        jScrollPane5.setViewportView(movieTable);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 380, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabPane.addTab("Movies", jPanel1);

        bookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "idNum", "Name", "Authors", "Publisher", "Genre", "Rating", "isbn", "Year Released", "Date Added", "Tags", "Other"
            }
        ));
        jScrollPane2.setViewportView(bookTable);

        tabPane.addTab("Books", jScrollPane2);

        addItemButton.setText("Add Item");
        addItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemButtonActionPerformed(evt);
            }
        });

        removeItemButton.setText("Remove Item");
        removeItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeItemButtonActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        recommendButton.setText("Recommend");

        updateButton.setText("Update Table");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(addItemButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(removeItemButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(searchButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(recommendButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(updateButton))
                    .add(tabPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(addItemButton)
                    .add(removeItemButton)
                    .add(searchButton)
                    .add(recommendButton)
                    .add(updateButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 24, Short.MAX_VALUE)
                .add(tabPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 408, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemButtonActionPerformed
        new raptorAddGUI().setVisible(true);
}//GEN-LAST:event_addItemButtonActionPerformed

    private void removeItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeItemButtonActionPerformed
        /* Remove selected item from database */
        int selectedRow = movieTable.getSelectedRow();
        Object selectedID = 0;
        selectedID = movieTable.getValueAt(selectedRow, 0);
        String selectedIDString = selectedID.toString();
        int selectedIDNum = Integer.parseInt(selectedIDString);
        System.out.println(selectedIDNum);
        DBUtil.removeBook(selectedIDNum);
}//GEN-LAST:event_removeItemButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        Connection conn = null;
        int rowCount = 0;
        try{
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:test.db");
        Statement query = conn.createStatement();
        ResultSet rs = query.executeQuery("SELECT COUNT(*) as theCount from Item;");
        rowCount = rs.getInt("theCount");
        
        conn.close();
        }catch(Exception e){System.out.println(e.getMessage());}
        javax.swing.table.DefaultTableModel testTable = (DefaultTableModel)movieTable.getModel();
        testTable.setNumRows(rowCount);
        String[] titleList = new String[rowCount];
        String[] genreList = new String[rowCount];
        String[] ratingList= new String[rowCount];
        String[] idList = new String[rowCount];
        String[] yearList = new String[rowCount];
        String[] dateList = new String[rowCount];
        String title;
        String genre;
        String rating;
        String ID;
        String year;
        String date;

        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn2 = null;
            conn2 = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement query2 = conn2.createStatement();
            ResultSet rs2 = query2.executeQuery("SELECT * from Item;");
            int J = 0;
            while(rs2.next()){
                ID = rs2.getString("itemId");
                title = rs2.getString("title");
                rating = rs2.getString("rating");
                genre = rs2.getString("genre");
                year = rs2.getString("year");
                date = rs2.getString("dateAdded");
                System.out.println(genre);
                idList[J] = ID;
                titleList[J] = title;
                genreList[J] = genre;
                ratingList[J] = rating;
                yearList[J] = year;
                dateList[J] = date;
                J++;
            }
                for(int i = 0; i < rowCount; i++){
                    movieTable.setValueAt(idList[i], i, 0);
                    movieTable.setValueAt(titleList[i], i, 1);
                    movieTable.setValueAt(genreList[i], i, 2);
                    movieTable.setValueAt(ratingList[i], i, 3);
                    movieTable.setValueAt(yearList[i], i, 4);
                    movieTable.setValueAt(dateList[i], i, 5);

                }
            
            conn2.close();
        }catch(Exception e){System.out.println(e.getMessage());

        }
        /* 
          }
              
          
          
          */
          
    }//GEN-LAST:event_updateButtonActionPerformed
/* Searches the web for a description of the related item*/
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        int selectedRow = movieTable.getSelectedRow();
        String selectedTitle = movieTable.getValueAt(selectedRow, 1).toString();
        String selectedGenre = movieTable.getValueAt(selectedRow, 2).toString();
        try{
        WebUtility.amazonSearch(selectedTitle, "DVD");
        }catch(Exception e){}
    }//GEN-LAST:event_searchButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new iRaptorGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CDTable;
    private javax.swing.JButton addItemButton;
    private javax.swing.JTable bookTable;
    private javax.swing.JTable gameTable;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable movieTable;
    private javax.swing.JButton recommendButton;
    private javax.swing.JButton removeItemButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTabbedPane tabPane;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables

}