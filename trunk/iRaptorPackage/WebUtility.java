package iRaptorPackage;

import java.awt.Desktop;
import java.net.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.regex.*;

/**
 * Contains methods for accessing appropriate websites for information.
 * @author Logan Moon
 */
public class WebUtility {
    
    /**
     * Open a new browser tab/window to an Amazon search page for the 
     * given title.
     * @param title Title of item to search for.
     * @param medium Medium of item.  One of: "book", "DVD", "CD", or "game"
     * @throws IllegalArgumentException if the medium is invalid
     * @throws Exception if unable to launch browser
     */
    
    public static void amazonSearch(String title, String medium) throws 
        IllegalArgumentException, Exception {
        String type=""; // dvd, videogames, stripbooks, popular
        if(medium.equalsIgnoreCase("DVD"))
            type = "dvd";
        else if(medium.equalsIgnoreCase("book"))
            type = "stripbooks";
        else if(medium.equalsIgnoreCase("CD"))
            type = "popular";
        else if(medium.equalsIgnoreCase("game"))
            type = "videogames";
        else
            throw new IllegalArgumentException("Invalid medium");
        
        Desktop D = Desktop.getDesktop();
        
        try {
        title = URLEncoder.encode(title, "UTF-16");
        
        D.browse(new URI("http://www.amazon.com/s/ref=nb_ss_gw?url=search-alias%3D" + type + 
        		  "&field-keywords=" + title + "&x=8&y=2"));
        } catch( Exception e ) {
        	throw new Exception("Unable to launch browser");
        }
        
        
    }
    
    /**
     * Open a new browser tab/window to the GameFAQs search page for the
     * specified title.
     * @param title Title of the game.
     * @throws Exception
     */
    
    public static void gamesFAQsearch(String title) throws Exception{
    	Desktop D = Desktop.getDesktop();
        
        try {
        title = URLEncoder.encode(title, "UTF-16");
        
        D.browse(new URI("http://www.gamefaqs.com/search/index.html?game=" +
        		title + "&s=s&platform=0"));
        } catch( Exception e ) {
        	throw new Exception("Unable to launch browser.");
        }
    }
    
    
    public static Vector<String> getSuggestions(String title, String medium) 
    		throws IOException,IllegalArgumentException {
    	
    	Vector<String> recommendations = new Vector<String>();
    	
    	String type="";
        // dvd, videogames, stripbooks, popular
        if(medium.equalsIgnoreCase("DVD"))
            type = "dvd";
        else if(medium.equalsIgnoreCase("book"))
            type = "stripbooks";
        else if(medium.equalsIgnoreCase("CD"))
            type = "popular";
        else if(medium.equalsIgnoreCase("game"))
            type = "videogames";
        else
            throw new IllegalArgumentException("Invalid medium");

        URL searchURL = null;
        
        try {
        title = URLEncoder.encode(title, "UTF-16");
        
        searchURL = new URL("http://www.amazon.com/s/ref=nb_ss_gw?url=search-alias%3D" + type + 
        		  "&field-keywords=" + title + "&x=8&y=2");
        } catch( Exception e ) {
        	throw new IllegalArgumentException("Invalid title");
        }
    	
        //Grab the Amazon search results page for the given item
        String searchResults = readPage(searchURL);
        
        int i = searchResults.indexOf("class=\"productTitle\"><a href=\"");
        if(i >= 0) {
        	int j = searchResults.indexOf('\"', i+30);
        	URL itemURL = new URL(searchResults.substring(i, j));
        	
        	String itemPage = readPage(itemURL);
        	
        	i = itemPage.indexOf("Explore similar items");
        	if(i > 0) {
        		j = itemPage.lastIndexOf('\"', i);
        		i = itemPage.lastIndexOf('\"', i-1);
        		
        		if(i > 0 && j > 0) {
        			String similar = itemPage.substring(i, j);
        			URL similarURL = new URL(similar);
        			
        			String similarPage = readPage(similarURL);
        			
        			Pattern p = Pattern.compile("\\G.*<div class=\"simProductInfo\"><a href=\"[^\"]+\">([^>]+)</a>");
        			Matcher m = p.matcher(similarPage);
        			
        			for(int c = 0; c < 5; c++) {
        				if(m.matches()) {
        					recommendations.set(c, m.group(0));
        				}
        			}
        			
        		}
        		
        	}
        	
        }
    	
    	return recommendations;
    }
    
    
    
    /**
     * Download an HTML page and return its code.
     * @param u URL of the page to download
     * @return The code of the page as a String
     * @throws IOException If the page cannot be read
     */
    public static String readPage(URL u) throws IOException {
    	String code = "";
    	String line;
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(u.openStream()));
    	while((line = br.readLine())!= null) {
    		code += (line + "\n");
    	}
    	
    	
    	return code;
    }
    
}