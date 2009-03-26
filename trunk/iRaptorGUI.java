import java.sql.*;

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
        movieTab = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        bookTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        gameTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        CDTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        movieTextArea = new javax.swing.JTextArea();
        addItemButton = new javax.swing.JButton();
        removeItemButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        recommendButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Authors", "Publisher", "Genre", "Rating", "isbn", "Year Released", "Date Added", "Tags", "Other"
            }
        ));
        jScrollPane2.setViewportView(bookTable);

        movieTab.addTab("Books", jScrollPane2);

        gameTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Genre", "Rating", "Date Added", "Year Released", "Tags", "Other"
            }
        ));
        jScrollPane3.setViewportView(gameTable);

        movieTab.addTab("Games", jScrollPane3);

        CDTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Artists", "Genre", "Rating", "Year Released", "Date Added", "Tags", "Other"
            }
        ));
        jScrollPane4.setViewportView(CDTable);

        movieTab.addTab("CDs", jScrollPane4);

        movieTextArea.setColumns(20);
        movieTextArea.setRows(5);
        jScrollPane5.setViewportView(movieTextArea);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 792, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 360, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        movieTab.addTab("Movies", jPanel1);

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
                        .add(updateButton)
                        .addContainerGap(444, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(movieTab, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
                        .add(144, 144, 144))))
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
                .add(movieTab, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 408, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemButtonActionPerformed
        new raptorAddGUI().setVisible(true);
}//GEN-LAST:event_addItemButtonActionPerformed

    private void removeItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeItemButtonActionPerformed
        /* Remove selected item from database */
}//GEN-LAST:event_removeItemButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        Connection conn = null;
          try{
          Class.forName("org.sqlite.JDBC");
          String mainTitle = null;
          String mainGenre = null;
          String mainTimeStamp = null;
          String mainRating = null;
          String mainAdded = null;

          conn = DriverManager.getConnection("jdbc:sqlite:test.db");
          Statement query = conn.createStatement();
          ResultSet rs = query.executeQuery("select * from Item;");
          while(rs.next()){
              mainTitle = rs.getString("title");
              mainGenre = rs.getString("genre");
              mainRating = rs.getString("rating");
              mainAdded = rs.getString("year");
              mainTimeStamp = rs.getString("dateAdded");
              String s  =  mainTitle + "," + mainGenre + "," + mainRating + "," + mainAdded + "," + mainTimeStamp +"\n";
              movieTextArea.append(s);
          }
          
          
          }catch(Exception e){System.out.println(e.getMessage());}
    }//GEN-LAST:event_updateButtonActionPerformed

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
    private javax.swing.JTabbedPane movieTab;
    private javax.swing.JTextArea movieTextArea;
    private javax.swing.JButton recommendButton;
    private javax.swing.JButton removeItemButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables

}
