package iRaptorPackage;

import java.awt.Desktop;
import java.net.*;


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
    
    
    
    
}