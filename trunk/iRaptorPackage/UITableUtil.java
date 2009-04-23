/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iRaptorPackage;

import domain.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
/**
 *
 * @author Gun Jack
 */
public class UITableUtil {
    JTable theTableModel = new JTable();
    String type;
    javax.swing.table.DefaultTableModel testTable; 
    Collection<domain.VideoGame> listOVideoGames = domain.DomainUtil.getVideoGames();
    Collection<domain.DVD> listODVDs = domain.DomainUtil.getDVDs();
    Collection<domain.Book> listOBooks = domain.DomainUtil.getBooks();
    Collection<domain.CD> listOCDs = domain.DomainUtil.getCDs();


    public UITableUtil(JTable table, String type){
    this.type = type;
    theTableModel = table;
    testTable = (DefaultTableModel)theTableModel.getModel();

    }
    public void updateTable(){
        if(type == "movie"){
            int i = 0;
        	Collection<domain.DVD> DVDCollection = domain.DomainUtil.getDVDs();
            Iterator<domain.DVD> dvdIterator = DVDCollection.iterator();
            testTable.setNumRows(DVDCollection.size());
            while(dvdIterator.hasNext()){
            	domain.DVD currentDVD = dvdIterator.next();
            	for(int j =0; j<8;j++){
            		int ItemId = currentDVD.getItemId();
            		String DVDName = currentDVD.getTitle();
            		String DVDgenre = currentDVD.getGenre();
            		String DVDnotes = currentDVD.getNotes();
            		int DVDYearReleased = currentDVD.getYear();
            		int DVDRating = currentDVD.getRating();
            		List<Tag> DVDTags = currentDVD.getTags();
            		String TAGS = DVDTags.toString();
            		Date DVDAdded = currentDVD.getDateAdded();
            		if(j == 0){
            			theTableModel.setValueAt(ItemId, i, j);
            		}
            		if(j == 1){
            			theTableModel.setValueAt(DVDName, i, j);
            		}
            		if(j == 2){
            			theTableModel.setValueAt(DVDgenre, i, j);
            		}
            		if(j == 3){
            			theTableModel.setValueAt(DVDRating, i, j);
            		}
            		if(j == 4){
            			theTableModel.setValueAt(DVDYearReleased, i, j);
            		}
            		if(j == 5){
            			theTableModel.setValueAt(DVDAdded, i, j);
            		}
            		if(j == 6){
            			theTableModel.setValueAt(TAGS, i, j);
            		}
            		if(j == 7){
            			theTableModel.setValueAt(DVDnotes, i, j);
            		}
            	}
            	i++;
            	}
            }
            if(type == "game"){
            	int i = 0;
            	Collection<domain.VideoGame> VideoGameCollection = domain.DomainUtil.getVideoGames();
                Iterator<domain.VideoGame> VideoGameIterator = VideoGameCollection.iterator();
                testTable.setNumRows(VideoGameCollection.size());
                while(VideoGameIterator.hasNext()){
                	domain.VideoGame currentVideoGame = VideoGameIterator.next();
                	for(int j =0; j<8;j++){
                		int ItemId = currentVideoGame.getItemId();
                		String DVDName = currentVideoGame.getTitle();
                		String DVDgenre = currentVideoGame.getGenre();
                		String DVDnotes = currentVideoGame.getNotes();
                		int DVDYearReleased = currentVideoGame.getYear();
                		int DVDRating = currentVideoGame.getRating();
                		List<Tag> DVDTags = currentVideoGame.getTags();
                		String TAGS = DVDTags.toString();
                		Date DVDAdded = currentVideoGame.getDateAdded();
                		if(j == 0){
                			theTableModel.setValueAt(ItemId, i, j);
                		}
                		if(j == 1){
                			theTableModel.setValueAt(DVDName, i, j);
                		}
                		if(j == 2){
                			theTableModel.setValueAt(DVDgenre, i, j);
                		}
                		if(j == 3){
                			theTableModel.setValueAt(DVDRating, i, j);
                		}
                		if(j == 4){
                			theTableModel.setValueAt(DVDAdded, i, j);
                		}
                		if(j == 5){
                			theTableModel.setValueAt(DVDYearReleased, i, j);
                		}
                		if(j == 6){
                			theTableModel.setValueAt(TAGS, i, j);
                		}
                		if(j == 7){
                			theTableModel.setValueAt(DVDnotes, i, j);
                		}
                	}
                	i++;
                	}
            	
            	
            }
            if(type == "book"){
            	int i = 0;
            	Collection<domain.Book> VideoGameCollection = domain.DomainUtil.getBooks();
                Iterator<domain.Book> VideoGameIterator = VideoGameCollection.iterator();
                testTable.setNumRows(VideoGameCollection.size());
                while(VideoGameIterator.hasNext()){
                	domain.Book currentDVD = VideoGameIterator.next();
                	for(int j =0; j<8;j++){
                		int ItemId = currentDVD.getItemId();
                		String DVDName = currentDVD.getTitle();
                		String DVDgenre = currentDVD.getGenre();
                		String DVDnotes = currentDVD.getNotes();
                		int DVDYearReleased = currentDVD.getYear();
                		int DVDRating = currentDVD.getRating();
                		List<Tag> DVDTags = currentDVD.getTags();
                		Date DVDAdded = currentDVD.getDateAdded();
                		if(j == 0){
                			theTableModel.setValueAt(ItemId, i, j);
                		}
                		if(j == 1){
                			theTableModel.setValueAt(DVDName, i, j);
                		}
                		if(j == 2){
                			theTableModel.setValueAt(DVDgenre, i, j);
                		}
                		if(j == 3){
                			theTableModel.setValueAt(DVDRating, i, j);
                		}
                		if(j == 4){
                			theTableModel.setValueAt(DVDYearReleased, i, j);
                		}
                		if(j == 5){
                			theTableModel.setValueAt(DVDAdded, i, j);
                		}
                		//if(j == 6){
                		//	theTableModel.setValueAt(ItemId, i, j);
                		//}
                		if(j == 7){
                			theTableModel.setValueAt(DVDnotes, i, j);
                		}
                	}
                	i++;
                	}
            	
            	
            }
            
        
    if(type == "cd"){
    	int i = 0;
    	Collection<domain.CD> VideoGameCollection = domain.DomainUtil.getCDs();
        Iterator<domain.CD> VideoGameIterator = VideoGameCollection.iterator();
        testTable.setNumRows(VideoGameCollection.size());
        while(VideoGameIterator.hasNext()){
        	domain.CD currentDVD = VideoGameIterator.next();
        	for(int j =0; j<9;j++){
        		int ItemId = currentDVD.getItemId();
        		String DVDName = currentDVD.getTitle();
        		String DVDgenre = currentDVD.getGenre();
        		List<Creator> CDArtsist = currentDVD.getArtists();
        		String DVDnotes = currentDVD.getNotes();
        		int DVDYearReleased = currentDVD.getYear();
        		int DVDRating = currentDVD.getRating();
        		List<Tag> DVDTags = currentDVD.getTags();
        		Date DVDAdded = currentDVD.getDateAdded();
        		if(j == 0){
        			theTableModel.setValueAt(ItemId, i, j);
        		}
        		if(j == 1){
        			theTableModel.setValueAt(DVDName, i, j);
        		}
        		if(j == 2){
        			theTableModel.setValueAt(DVDgenre, i, j);
        		}
        		if(j == 3){
        			theTableModel.setValueAt(DVDgenre, i, j);
        		}
        		if(j == 4){
        			theTableModel.setValueAt(DVDRating, i, j);
        		}
        		if(j == 5){
        			theTableModel.setValueAt(DVDYearReleased, i, j);
        		}
        		if(j == 6){
        			theTableModel.setValueAt(DVDAdded, i, j);
        		}
        		if(j == 7){
        			//theTableModel.setValueAt(DVDTags, i, j);
        		}
        		if(j == 8){
        			theTableModel.setValueAt(DVDnotes, i, j);
        		}
        	}
        	i++;
        	}
    	
    	
    }}}
    
            	
            
            
            
        
        
        
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
            javax.swing.table.DefaultTableModel testTable = (DefaultTableModel)theTableModel.getModel();
            testTable.setNumRows(rowCount);
            String[] titleList = new String[rowCount];
            String[] genreList = new String[rowCount];
            String[] ratingList= new String[rowCount];
            String[] idList = new String[rowCount];
            String[] yearList = new String[rowCount];
            String[] dateList = new String[rowCount];
            String[] director = new String[rowCount];
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
        }*/
        /*if(type == "game"){
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
    }*/

