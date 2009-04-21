package iRaptorPackage;

import domain.*;
import java.sql.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

import domain.DBUtil;


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
        recommendButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        searchWebButton = new javax.swing.JButton();
        searchiRaptorButton = new javax.swing.JButton();
        strategyGuideButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("iRaptor");

        tabPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabPaneStateChanged(evt);
            }
        });

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
                "idNum", "Name", "Authors", "Genre", "Rating", "Date Added", "Year Released", "isbn", "Publisher", "Tags", "Other"
            }
        ));
        jScrollPane2.setViewportView(bookTable);
        bookTable.getColumnModel().getColumn(5).setResizable(false);
        bookTable.getColumnModel().getColumn(8).setResizable(false);

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

        recommendButton.setText("Recommend");
        recommendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recommendButtonActionPerformed(evt);
            }
        });

        updateButton.setText("Update Table");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        searchWebButton.setText("Info from the Web");
        searchWebButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchWebButtonActionPerformed(evt);
            }
        });

        searchiRaptorButton.setText("Search iRaptor");

        strategyGuideButton.setText("Get Strategy Guide");
        strategyGuideButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                strategyGuideButtonActionPerformed(evt);
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
                    .add(tabPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(8, 8, 8)
                        .add(addItemButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(removeItemButton)
                        .add(20, 20, 20)
                        .add(updateButton)
                        .add(76, 76, 76)
                        .add(searchWebButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(searchiRaptorButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(strategyGuideButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 64, Short.MAX_VALUE)
                        .add(recommendButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(recommendButton)
                    .add(updateButton)
                    .add(removeItemButton)
                    .add(addItemButton)
                    .add(searchWebButton)
                    .add(strategyGuideButton)
                    .add(searchiRaptorButton))
                .add(35, 35, 35)
                .add(tabPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .add(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents




    private void addItemButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        new raptorAddGUI().setVisible(true);
}                                             
    
    private void searchiRaptorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemButtonActionPerformed
        new searchRaptorGUI().setVisible(true);
}//GEN-LAST:event_addItemButtonActionPerformed

    private void removeItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeItemButtonActionPerformed
       UITableUtil gameTableUtil = new UITableUtil(gameTable, "game");
       UITableUtil movieTableUtil = new UITableUtil(movieTable, "movie");
       UITableUtil cdTableUtil = new UITableUtil(CDTable, "cd");
       UITableUtil bookTableUtil = new UITableUtil(bookTable, "book");
        /* Remove selected item from database */
        int selectedRow = movieTable.getSelectedRow();
        Object selectedID = 0;
        selectedID = movieTable.getValueAt(selectedRow, 0);
        String selectedIDString = selectedID.toString();
        int selectedIDNum = Integer.parseInt(selectedIDString);
        System.out.println(selectedIDNum);
        DomainUtil.removeItem(selectedIDNum);        gameTableUtil.updateTable();        movieTableUtil.updateTable();        cdTableUtil.updateTable();        bookTableUtil.updateTable();}//GEN-LAST:event_removeItemButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
       UITableUtil gameTableUtil = new UITableUtil(gameTable, "game");
       UITableUtil movieTableUtil = new UITableUtil(movieTable, "movie");
       UITableUtil cdTableUtil = new UITableUtil(CDTable, "cd");
       UITableUtil bookTableUtil = new UITableUtil(bookTable, "book");
       
        gameTable.setValueAt(10, 0, 0);
       gameTable.setValueAt("Fire Emblem", 0, 1);
       gameTable.setValueAt("Strategy", 0, 2);
       gameTable.setValueAt(5, 0, 3);
       gameTable.setValueAt("2009-04-04 21:06:55", 0, 4);
       gameTable.setValueAt(2009, 0, 5);
       movieTableUtil.updateTable();
        
        
        /*Connection conn = null;
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
            int selectedRow = movieTable.getSelectedRow();
        String selectedTitle = movieTable.getValueAt(selectedRow, 1).toString();
        String selectedGenre = movieTable.getValueAt(selectedRow, 2).toString();
        try{
        iRaptorPackage.WebUtility.amazonSearch(selectedTitle, "DVD");
        }catch(Exception e){}
          
          
          */
          
    }//GEN-LAST:event_updateButtonActionPerformed
/* Searches the web for a description of the related item*/
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed

    }//GEN-LAST:event_searchButtonActionPerformed

    private void searchWebButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchWebButtonActionPerformed
        int selectedIndex = tabPane.getSelectedIndex();
        int selectedRow = movieTable.getSelectedRow();
        String selectedTitle = movieTable.getValueAt(selectedRow, 1).toString();
        try{
        if(selectedIndex == 0){
            WebUtility.amazonSearch(selectedTitle, "game");
        }
        if(selectedIndex == 1){
            WebUtility.amazonSearch(selectedTitle, "CD");
        }
        if(selectedIndex == 2){
            iRaptorPackage.WebUtility.amazonSearch(selectedTitle, "DVD");
        }
        if(selectedIndex == 3){
            WebUtility.amazonSearch(selectedTitle, "book");
        }
        }catch(Exception e){}
    }//GEN-LAST:event_searchWebButtonActionPerformed

    private void tabPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabPaneStateChanged
        int selectedIndex = tabPane.getSelectedIndex();
        if(selectedIndex == 0){
            strategyGuideButton.setVisible(true);
        }// TODO add your handling code here:
        else{
            strategyGuideButton.setVisible(false);
        }
    }//GEN-LAST:event_tabPaneStateChanged

    private void strategyGuideButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_strategyGuideButtonActionPerformed
         int selectedRow = gameTable.getSelectedRow();
         String selectedTitle;
         selectedTitle = gameTable.getValueAt(selectedRow, 1).toString();
         try{
             WebUtility.gamesFAQsearch(selectedTitle);
         }catch(Exception e){}
         // TODO add your handling code here:
    }//GEN-LAST:event_strategyGuideButtonActionPerformed

    private void recommendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recommendButtonActionPerformed
        int selectedIndex = tabPane.getSelectedIndex();
        //int selectedRow = movieTable.getSelectedRow();
        //String selectedTitle = movieTable.getValueAt(selectedRow, 1).toString();
        System.out.println("HERE WE GO");
        try{
            //if(selectedIndex == 0){
            Vector<String> recomendations = WebUtility.getSuggestions("Fire Emblem"/*selectedTitle*/, "game");
        
        
        }catch(Exception e){System.out.println(e.toString());}
        System.out.println("THIS IS DONE");
        //JOptionPane test = new JOptionPane("HELP!");
        //test.setVisible(true);
    }//GEN-LAST:event_recommendButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
    	DBUtil.openTemporaryFile();
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
    private javax.swing.JButton searchWebButton;
    private javax.swing.JButton searchiRaptorButton;
    private javax.swing.JButton strategyGuideButton;
    private javax.swing.JTabbedPane tabPane;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
    
}
