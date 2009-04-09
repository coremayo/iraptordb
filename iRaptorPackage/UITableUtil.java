/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iRaptorPackage;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
/**
 *
 * @author Gun Jack
 */
public class UITableUtil {
    JTable theTableModel = new JTable();
    Object type;


    public UITableUtil(JTable table, String type){
    this.type = type;
    theTableModel = table;

    }
    public void updateTable(){
        if(type == "movie"){
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
            javax.swing.table.DefaultTableModel testTable = (DefaultTableModel)theTableModel.getModel();
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
                        theTableModel.setValueAt(idList[i], i, 0);
                        theTableModel.setValueAt(titleList[i], i, 1);
                        theTableModel.setValueAt(genreList[i], i, 2);
                        theTableModel.setValueAt(ratingList[i], i, 3);
                        theTableModel.setValueAt(yearList[i], i, 4);
                        theTableModel.setValueAt(dateList[i], i, 5);

                    }

                conn2.close();
            }catch(Exception e){System.out.println(e.getMessage());
                        }
        }
        if(type == "game"){
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
                    javax.swing.table.DefaultTableModel testTable = (DefaultTableModel)theTableModel.getModel();
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
                                theTableModel.setValueAt(idList[i], i, 0);
                                theTableModel.setValueAt(titleList[i], i, 1);
                                theTableModel.setValueAt(genreList[i], i, 2);
                                theTableModel.setValueAt(ratingList[i], i, 3);
                                theTableModel.setValueAt(yearList[i], i, 4);
                                theTableModel.setValueAt(dateList[i], i, 5);

                            }

                        conn2.close();
                    }catch(Exception e){System.out.println(e.getMessage());
                                }
        }
        if(type == "cd"){
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
                javax.swing.table.DefaultTableModel testTable = (DefaultTableModel)theTableModel.getModel();
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
                            theTableModel.setValueAt(idList[i], i, 0);
                            theTableModel.setValueAt(titleList[i], i, 1);
                            theTableModel.setValueAt(genreList[i], i, 2);
                            theTableModel.setValueAt(ratingList[i], i, 3);
                            theTableModel.setValueAt(yearList[i], i, 4);
                            theTableModel.setValueAt(dateList[i], i, 5);

                        }

                    conn2.close();
                }catch(Exception e){System.out.println(e.getMessage());
                            }
        }
        if(type == "book"){
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
                javax.swing.table.DefaultTableModel testTable = (DefaultTableModel)theTableModel.getModel();
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
                            theTableModel.setValueAt(idList[i], i, 0);
                            theTableModel.setValueAt(titleList[i], i, 1);
                            theTableModel.setValueAt(genreList[i], i, 2);
                            theTableModel.setValueAt(ratingList[i], i, 3);
                            theTableModel.setValueAt(yearList[i], i, 4);
                            theTableModel.setValueAt(dateList[i], i, 5);

                        }

                    conn2.close();
                }catch(Exception e){System.out.println(e.getMessage());
                            }
        }
    }
}
