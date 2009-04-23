//package iRaptorTest;
//import iRaptorPackage.iRaptorGUI;
//
//import iRaptorPackage.raptorAddGUI;
//import iRaptorPackage.searchRaptorGUI;
//
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.event.MouseEvent.*;
//import java.awt.event.*;
//import java.awt.Robot;
//import java.awt.Point;
//
//import javax.swing.JButton;
//import javax.swing.JMenuItem;
//import javax.swing.JTable;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import javax.swing.SwingUtilities;
//import javax.swing.UIManager;
//import javax.swing.JComboBox;
//
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//
//import junit.framework.TestCase;
//
//
//public class GUITest extends TestCase {
//	
//	static iRaptorGUI gui = new iRaptorGUI();
//	static raptorAddGUI addgui = new raptorAddGUI();
//	static searchRaptorGUI searchgui = new searchRaptorGUI();
//	
//	@Test
//	public void testTypeInGameTable() throws Exception {
//		String testString = "message1";
//		assertNotNull(gui);  // Instantiated?
//		JTable gametable = (JTable)TestUtils.getChildNamed(gui, "game table");
//		//JTextField input = (JTextField)TestUtils.getChildNamed((Component)obj, "input");
//		assertNotNull(gametable); // Component found?
//		//assertNotNull(input); // Component found?
//		gametable.setValueAt(testString, 0, 0);
//		//input.setText(testString);
//		gametable.updateUI(); 
//		//input.postActionEvent();  // Type in a test message + ENTER 
//		assertEquals(testString, gametable.getValueAt(0, 0));
//		//assertEquals(testString + "?", input.getText());
//	}
//	
//	@Test
//	public void testTypeInCDTable() throws Exception {
//		String testString = "message1";
//		assertNotNull(gui);  // Instantiated?
//		JTable cdtable = (JTable)TestUtils.getChildNamed(gui, "cd table");
//		assertNotNull(cdtable); // Component found?
//		cdtable.setValueAt(testString, 0, 0);
//		cdtable.updateUI();
//		assertEquals(testString, cdtable.getValueAt(0, 0));
//	}
//	
//	
//	@Test
//	public void testTypeInMovieTable() throws Exception {
//		String testString = "message1";
//		assertNotNull(gui);  // Instantiated?
//		JTable movietable = (JTable)TestUtils.getChildNamed(gui, "movie table");
//		assertNotNull(movietable); // Component found?
//		movietable.setValueAt(testString, 0, 0);
//		movietable.updateUI(); 
//		assertEquals(testString, movietable.getValueAt(0, 0));
//	}
//	
//	@Test
//	public void testTypeInBookTable() throws Exception {
//		String testString = "message1";
//		assertNotNull(gui);  // Instantiated?
//		JTable booktable = (JTable)TestUtils.getChildNamed(gui, "book table");
//		assertNotNull(booktable); // Component found?
//		booktable.setValueAt(testString, 0, 0);
//		booktable.updateUI(); 
//		assertEquals(testString, booktable.getValueAt(0, 0));
//	}
//	
////	/*Test that the Search Item GUI pops up correctly on a Search button click from the main GUI*/
//	@Test
//	public void testPopUpSearchItem() throws Exception {
//		final JButton searchbutton = (JButton)TestUtils.getChildNamed(gui, "search button");  //getChildNamed(parent, name)
//		assertNotNull(searchbutton);
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				searchbutton.doClick();
//			}
//		});
//		JButton search = null;
//		// The dialog box will show up shortly
//		for (int i = 0; i < 5; ++i) {  //add == null
//			Thread.sleep(200);
//			//we'll need to add this search gui in the application code and it should contain a search button where we call setName("search item done button")
//			search = (JButton)TestUtils.getChildNamed(searchgui, "search item done button");
//			assertNotNull(search);
//		}
//		//assertEquals(UIManager.getString("OptionPane.addButtonText"), add.getText());
//		//add.doClick();
//	}
//	
//	/*Test that the Add Item GUI pops up correctly on an Add Item click from the main GUI*/
//	@Test
//	public void testPopUpAddItem() throws Exception {
//		final JButton additembutton = (JButton)TestUtils.getChildNamed(gui, "add item button");  //getChildNamed(parent, name)
//		assertNotNull(additembutton);
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				additembutton.doClick();
//			}
//		});
//		JButton add = null;
//		// The dialog box will show up shortly
//		for (int i = 0; i < 5; ++i) {  //add == null
//			Thread.sleep(200);
//			add = (JButton)TestUtils.getChildNamed(addgui, "add item done button");
//			assertNotNull(add);
//		}
//		//assertEquals(UIManager.getString("OptionPane.addButtonText"), add.getText());
//		//add.doClick();
//	}
//	
//	
//	/*Test adding a movie from the "Add Item GUI" */
//	@Test
//	public void testAddMovie() throws Exception {
//		
//		//First press the add item button from the main gui to pop up the add item gui on the screen
//		final JButton additembutton = (JButton)TestUtils.getChildNamed(gui, "add item button");  //getChildNamed(parent, name)
//		assertNotNull(additembutton);
//		assertNotNull(addgui);  // Instantiated?
//		
//		JTable movietable = (JTable)TestUtils.getChildNamed(gui, "movie table"); 
//		JComboBox combobox = (JComboBox)TestUtils.getMenuItemChildNamed(addgui, "add item combo box");
//		JTextField title = (JTextField)TestUtils.getChildNamed(addgui, "title text field");
//		JButton addbutton = (JButton)TestUtils.getChildNamed(addgui, "add item done button");
//		JButton updatebutton = (JButton)TestUtils.getChildNamed(gui, "update table button");
//		
//		assertNotNull(movietable); //Component found?
//		assertNotNull(combobox); // Component found?
//		assertNotNull(title); // Component found?
//		assertNotNull(addbutton); // Component found?		
//
////        for (int i=0; i<combobox.getItemCount(); i++) {
////        	if (combobox.getItemAt(i)=="Movie");
////        		//we don't have to worry about selecting the proper item in the combobox because movie is first by default
////        }
//		
//        int row = 0;
//        int column = 0;
//        for (int i=0; i<movietable.getColumnCount(); i++) {
//        	if (movietable.getColumnName(i) == "Title") {
//        		column = i;
//        	}
//        }
//        String testString = "Message1";
//        
//		title.setText(testString);
//		Thread.sleep(100);
//		addbutton.doClick();
//		Thread.sleep(200);
//		updatebutton.doClick();
//		Thread.sleep(200);
//		assertEquals(testString, movietable.getValueAt(row, column));
//	}
//	
//	/*Test adding a book from the "Add Item GUI" */
//	@Test
//	public void testAddBook() throws Exception {
//		
//		//First press the add item button from the main gui to pop up the add item gui on the screen
//		final JButton additembutton = (JButton)TestUtils.getChildNamed(gui, "add item button");  //getChildNamed(parent, name)
//		assertNotNull(additembutton);
//		assertNotNull(addgui);  // Instantiated?
//		
//		JTable booktable = (JTable)TestUtils.getChildNamed(gui, "book table"); 
//		JComboBox combobox = (JComboBox)TestUtils.getMenuItemChildNamed(addgui, "add item combo box");
//		JTextField title = (JTextField)TestUtils.getChildNamed(addgui, "title text field");
//		JButton addbutton = (JButton)TestUtils.getChildNamed(addgui, "add item done button");
//		JButton updatebutton = (JButton)TestUtils.getChildNamed(gui, "update table button");
//		
//		assertNotNull(booktable); //Component found?
//		assertNotNull(combobox); // Component found?
//		assertNotNull(title); // Component found?
//		assertNotNull(addbutton); // Component found?		
//
//        for (int i=0; i<combobox.getItemCount(); i++) {
//        	if (combobox.getItemAt(i)=="Book");
//        		combobox.setSelectedItem("Book");
//        		combobox.getSelectedItem(); 
//        }
//		
//        int row = 0;
//        int column = 0;
//        for (int i=0; i<booktable.getColumnCount(); i++) {
//        	if (booktable.getColumnName(i) == "Title") {
//        		column = i;
//        	}
//        }
//        String testString = "Message1";
//        
//		title.setText(testString);
//		Thread.sleep(100);
//		addbutton.doClick();
//		Thread.sleep(200);
//		updatebutton.doClick();
//		Thread.sleep(200);
//		assertEquals(testString, booktable.getValueAt(row, column));
//	}
//	
//	/*Test adding a Game from the "Add Item GUI" */
//	@Test
//	public void testAddGame() throws Exception {
//		
//		//First press the add item button from the main gui to pop up the add item gui on the screen
//		final JButton additembutton = (JButton)TestUtils.getChildNamed(gui, "add item button");  //getChildNamed(parent, name)
//		assertNotNull(additembutton);
//		assertNotNull(addgui);  // Instantiated?
//		
//		JTable gametable = (JTable)TestUtils.getChildNamed(gui, "game table"); 
//		JComboBox combobox = (JComboBox)TestUtils.getMenuItemChildNamed(addgui, "add item combo box");
//		JTextField title = (JTextField)TestUtils.getChildNamed(addgui, "title text field");
//		JButton addbutton = (JButton)TestUtils.getChildNamed(addgui, "add item done button");
//		JButton updatebutton = (JButton)TestUtils.getChildNamed(gui, "update table button");
//		
//		assertNotNull(gametable); //Component found?
//		assertNotNull(combobox); // Component found?
//		assertNotNull(title); // Component found?
//		assertNotNull(addbutton); // Component found?		
//
//        for (int i=0; i<combobox.getItemCount(); i++) {
//        	if (combobox.getItemAt(i)=="Game");
//        		combobox.setSelectedItem("Game");
//        		combobox.getSelectedItem(); 
//        }
//		
//        int row = 0;
//        int column = 0;
//        for (int i=0; i<gametable.getColumnCount(); i++) {
//        	if (gametable.getColumnName(i) == "Title") {
//        		column = i;
//        	}
//        }
//        String testString = "Message1";
//        
//		title.setText(testString);
//		Thread.sleep(100);
//		addbutton.doClick();
//		Thread.sleep(200);
//		updatebutton.doClick();
//		Thread.sleep(200);
//		assertEquals(testString, gametable.getValueAt(row, column));
//	}
//	
//	/*Test adding a CD from the "Add Item GUI" */
//	@Test
//	public void testAddCD() throws Exception {
//		
//		//First press the add item button from the main gui to pop up the add item gui on the screen
//		final JButton additembutton = (JButton)TestUtils.getChildNamed(gui, "add item button");  //getChildNamed(parent, name)
//		assertNotNull(additembutton);
//		assertNotNull(addgui);  // Instantiated?
//		
//		JTable cdtable = (JTable)TestUtils.getChildNamed(gui, "cd table"); 
//		JComboBox combobox = (JComboBox)TestUtils.getMenuItemChildNamed(addgui, "add item combo box");
//		JTextField title = (JTextField)TestUtils.getChildNamed(addgui, "title text field");
//		JButton addbutton = (JButton)TestUtils.getChildNamed(addgui, "add item done button");
//		JButton updatebutton = (JButton)TestUtils.getChildNamed(gui, "update table button");
//		
//		assertNotNull(cdtable); //Component found?
//		assertNotNull(combobox); // Component found?
//		assertNotNull(title); // Component found?
//		assertNotNull(addbutton); // Component found?		
//
//        for (int i=0; i<combobox.getItemCount(); i++) {
//        	if (combobox.getItemAt(i)=="CD");
//        		combobox.setSelectedItem("CD");
//        		combobox.getSelectedItem(); 
//        }
//		
//        int row = 0;
//        int column = 0;
//        for (int i=0; i<cdtable.getColumnCount(); i++) {
//        	if (cdtable.getColumnName(i) == "Title") {
//        		column = i;
//        	}
//        }
//        String testString = "Message1";
//        
//		title.setText(testString);
//		Thread.sleep(100);
//		addbutton.doClick();
//		Thread.sleep(200);
//		updatebutton.doClick();
//		Thread.sleep(200);
//		assertEquals(testString, cdtable.getValueAt(row, column));
//	}
//	
//	/*Test search for an item in the database; currently by text field entry of the title, accounts for all item types*/
//	@Test
//	public void testSearchItem() throws Exception {
//		final JButton searchguibutton = (JButton)TestUtils.getChildNamed(searchgui, "search item done button");  //getChildNamed(parent, name)
//		assertNotNull(searchgui);  // Instantiated?
//		assertNotNull(searchguibutton);
//		
//		String testString = "message1";
//		
//		JTable itemtable = (JTable)TestUtils.getChildNamed(searchgui, "all item table"); //add a table to the search gui for ALL items
//		JComboBox combobox = (JComboBox)TestUtils.getMenuItemChildNamed(searchgui, "search item combo box");
//		JTextField title = (JTextField)TestUtils.getChildNamed(searchgui, "title text field");
//		
//		assertNotNull(itemtable); //Component found?
//		assertNotNull(combobox); // Component found?
//		assertNotNull(title); // Component found?
//		
////        for (int i=0; i<combobox.getItemCount(); i++) {
////        	if (combobox.getItemAt(i)=="Movie");
////        		combobox.setEnabled(true);
////        }
//
//		combobox.setSelectedItem("Movie");
//        
//        int row = 0;
//        int column = 0;
//        
//        for (int i=0; i<itemtable.getColumnCount(); i++) {
//        	if (itemtable.getColumnName(i) == "Title") {
//        		column = i;
//        	}
//        }
//        System.out.println(column);
//		title.setText(testString);
//		Thread.sleep(100);
//		searchguibutton.doClick();
//		Thread.sleep(100);
//		//show result on first row
//		assertEquals(testString, itemtable.getValueAt(row, column));
//		
//		//assertEquals(1,2); //dummy code to show test fails right now
//		//once we implement a search gui, we can compile this
//	}
//	
//	/*
//	 * Test Remove by ensuring data is present in cell 0,0
//	 */
//	@Test
//	public void testRemoveItem() throws Exception {
//		
//		JTable movietable = (JTable)TestUtils.getChildNamed(gui, "cd table"); 
//		assertNotNull(movietable);
//	
//		//Enter data in the cell for something to test with
//		JButton removeitembutton = (JButton)TestUtils.getChildNamed(gui, "remove item button");  //getChildNamed(parent, name)
//		assertNotNull(removeitembutton);
//		String testMessage = "test remove";
//		movietable.setValueAt(testMessage, 0, 0);
//		
//		//Now must click the table row/cell to highlight the row/item
//		int x = movietable.getX();
//		int y = movietable.getY();
//		int mouseInt = MouseEvent.MOUSE_CLICKED;
//		long timeOfEvent = 0;
//		MouseEvent clickTable = new MouseEvent(movietable, mouseInt, timeOfEvent, mouseInt, x+2, y+2, 1, false);
//		movietable.dispatchEvent(clickTable);
//		Thread.sleep(100);
//		
//		//Now click remove item button, then wait for it to process, and compare
//		MouseEvent clickButton = new MouseEvent(removeitembutton, mouseInt, timeOfEvent, mouseInt, removeitembutton.getX()+2, removeitembutton.getY()+2, 1, false);
//		removeitembutton.dispatchEvent(clickTable);
//		Thread.sleep(100);
//		
//		//removeitembutton.doClick();
//
//		JButton updatebutton = (JButton)TestUtils.getChildNamed(gui, "update table button");
//		assertNotNull(updatebutton);
//		MouseEvent clickUpdate = new MouseEvent(updatebutton, mouseInt, timeOfEvent, mouseInt, updatebutton.getX()+2, updatebutton.getY()+2, 1, false);
//		removeitembutton.dispatchEvent(clickUpdate);		
//		//updatebutton.doClick();
//
//		
//		assertEquals(null, movietable.getValueAt(0,0));
//		
////		SwingUtilities.invokeLater(new Runnable() {
////			public void run() {
////				removeitembutton.doClick();
////			}
////		});
////		JButton ok = null;
////		// The dialog box will show up shortly
////		for (int i = 0; ok == null; ++i) {
////			Thread.sleep(200);
////			ok = (JButton)TestUtils.getChildIndexed(gui, "JButton", 0);
////			assertTrue(i < 10);
////		}
////		assertEquals(UIManager.getString("OptionPane.okButtonText"), ok.getText());
////		ok.doClick();
//	}
//	
//	
//	/*
//	 * checks if update button works by setting a value at position 0,0 in the cdtable and pressing update
//	 * then waiting for result to process, and assertsEquals at cdtable's 0,0 cell with the test message 
//	 */
//	@Test
//	public void testUpdateTable() throws Exception {
//		JButton updatebutton = (JButton)TestUtils.getChildNamed(gui, "update table button");
//		JTable cdtable = (JTable)TestUtils.getChildNamed(gui, "cd table");
//		assertNotNull(updatebutton);
//		assertNotNull(cdtable);
//		String testMessage = "Message1";
//		cdtable.setValueAt(testMessage, 0, 0);
//		updatebutton.doClick();;
//		Thread.sleep(100);
//		assertEquals(testMessage, cdtable.getValueAt(0,0));
//	}
//	
//	
//	/*
//	public void testStory() throws Exception {
//		// Type a string, change the color and popup
//
//		String testString = "message2";
//
//		JTextField    input = (JTextField)TestUtils.getChildNamed(gui, "input");
//		JMenuItem     red   = (JMenuItem)TestUtils.getMenuItemChildNamed(gui, "red");
//		JMenuItem     blue  = (JMenuItem)TestUtils.getMenuItemChildNamed(gui, "blue");
//		final JButton popup = (JButton)TestUtils.getMenuItemChildNamed(gui, "popup");
//
//		input.setText(testString);
//		input.postActionEvent();
//  
//		red.doClick();
//		assertEquals(testString + "?", input.getText());
//		assertEquals(Color.red, input.getForeground());
//  
//		blue.doClick();
//		assertEquals(testString + "?", input.getText());
//		assertEquals(Color.blue, input.getForeground());
//
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				popup.doClick();
//			}
//		});
//
//		JButton ok = null;
//		JTextArea message = null;
//
//		// The dialog box will show up shortly
//		for (int i = 0; ok == null || message == null; ++i) {
//			Thread.sleep(200);
//			ok = (JButton)TestUtils.getChildIndexed(gui, "JButton", 0);
//			message = (JTextArea)TestUtils.getChildIndexed(gui, "JTextArea", 0);
//			assertTrue(i < 10);
//		}
//		assertEquals(
//				UIManager.getString("OptionPane.okButtonText"), ok.getText());
//		assertEquals(testString + "? ... done.", message.getText());
//	  
//		ok.doClick();
//	}*/
//
//	
//}
//
//
