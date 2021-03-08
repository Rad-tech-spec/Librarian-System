/**
 * 
 */
package librarian;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import config.Data_Connection;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * <h2>Librarian - Data management</h2>
 * This class extends a Abstract (Info) and implements two interfaces (Student_Table_Info, Data_Connection)<br>
 * to reduce duplication of code and reuse. 
 * @author Rad Eshghi
 */

public class Data extends Info implements Student_Table_Info, Data_Connection{
	
	static String queryItems = "SELECT * FROM Items";
	static String queryIssue = "SELECT * FROM Issued";
	static String queryReturn = "SELECT * FROM returned";
	static int count = 0;

	
	/**
	 * This method is responsible to add a new item into the data base table also it is<br>
	 * responsible to create the "Items" table as well if it dose not exist.<br>
	 * every time this method is executed it will first connect to the data base<br>.
	 * Then checks if the table is created if not then it well create one base on the<br>
	 * column values that has been provided.<br>
	 * This method receives the information needed in the parameter to set and create.<br>
	 * Also it is using try catch block where is needed to avoid and handle exceptions.<br>
	 * It show a message once Adding the item has been processed to the user.<br>
	 * @param name
	 * Item name of type string. 
	 * @param Id
	 * Item id of type integer.
	 * @param author
	 * Item author of type string.
	 * @param category
	 * Item category of type string.
	 * @param status
	 * Item status of type string.
	 * @param kind
	 * Item type of type string.
	 * @return
	 */
	//AddBook_Class///////////////////////////////////////////////////////////////////////////////  MODIFIED AND WORKS
	public static Parent AddItem(String name, int Id, String author, String category, String status, String kind)
	{
		
		setTable_Name("Items");
		setCOL_Name("Name");
		setCOL_ID_S("ID");
		setCOL_Author("Author");
		setCOL_Status("Status");
		setCOL_Category("Category"); 
		setCOL_Kind("Type");
	

        try
        {
            Connection connection = DriverManager.getConnection(Data_Connection.jdbcURL, Data_Connection.username, Data_Connection.password);
            System.out.println("Connected to PostgreSQL server.");
            Statement statement = connection.createStatement();
            
            try
            {
            	statement.execute("create table if not exists "+ getTable_Name() + "(" + getCOL_ID_S() +" INTEGER, " + getCOL_Name() +" VARCHAR, " + getCOL_Author() +" VARCHAR, " 
            						+ getCOL_Category() +" VARCHAR, " + getCOL_Status() +" VARCHAR, "+ getCOL_Kind() + " VARCHAR)");
          
            }
            catch(SQLException e)
            {
            	System.out.println("Error: Creating Table Faild!");
            	e.printStackTrace();
            }
            
            
            try
            {
            	
            	String query = "Insert into " + getTable_Name() +" (" + getCOL_ID_S() + "," + getCOL_Name() + "," + getCOL_Author() + "," + getCOL_Category() + "," + getCOL_Status() + "," 
            					+ getCOL_Kind() + ")" + " Values (?,?,?,?,?,? )";
            	
            	PreparedStatement prestat = connection.prepareStatement(query);
            	prestat.setInt(1, Id);
            	prestat.setString(2, name);
            	prestat.setString(3, author);
            	prestat.setString(4, category);
            	prestat.setString(5, status);
            	prestat.setString(6, kind);
            	
            	prestat.execute();
            	
            	System.out.println("Adding Book Completed.");
            	Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setContentText(getCOL_Name() + " ID: " + getCOL_ID() +" New Item has been added to the table.");
				alert.setHeaderText(null);
				alert.showAndWait();
            
            }
            catch(SQLException e)
            {
            	System.out.println("Error: Could not add new Book!");
            	e.printStackTrace();
            }
            
            
            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error. Couldn't connect to PostgreSQL server!");
            e.printStackTrace();
        }
        
		return null;
	}
	
	
	
	/**
	 * This method is responsible for generating a output file of all the items that are in the items table in the data base<br>
	 * it includes all the information about the items with the date of creation and quantity of the items of each category.<br>
	 * Also try catch method has been used to handle and reduce exceptions.<br>
	 * This method connects and disconnects once is out of scope from the data base same as output file.
	 * @return
	 */
	//ViewBook_Class//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static Parent GenerateFile() 
	{
		FileWriter outputStream = null;
		String outFileStr = "LibraryItems.txt";
	
	
        try
        {
            Connection connection = DriverManager.getConnection(Data_Connection.jdbcURL, Data_Connection.username, Data_Connection.password);
            System.out.println("Connected to PostgreSQL server.");

            
            
           try
           {
        	   outputStream = new FileWriter(outFileStr, true);
        	   
        	   Statement statement = connection.createStatement();
        	   ResultSet result = statement.executeQuery(queryItems);
        	   
        	   if(count == 0)
         	  {
        		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        		   LocalDateTime now = LocalDateTime.now();
	        	   outputStream.write("ID\t\t\t\tTitle\t\t\t\tAuthor/Director\t\tCategory\t\t\t\tStatus\t\t\t\t\tType\n");
	        	   outputStream.write("-------------------------------------------------------------------------------------------------------------\n");
	        
	        		   
	        	   while(result.next())
	        	   {
	        		   setCOL_ID(result.getInt("id"));
	        		   setCOL_Name(result.getString("name")); 
	        		   setCOL_Author(result.getString("author"));
	        		   setCOL_Category(result.getString("category"));
	        		   setCOL_Status(result.getString("status"));
	        		   setCOL_Kind(result.getString("type"));
	        		   
        		   
	        		   outputStream.write(String.format("%s %20s %20s %20s %20s %20s\n", getCOL_ID() + " |", getCOL_Name() + " |", getCOL_Author() 
	        				   							+ " |", getCOL_Category() + " |", getCOL_Status() + " |", getCOL_Kind() + " |"));
	        	   }
	        	   
  	       	   
	        	   outputStream.write("--------------------------------------------------END OF FILE--------------------------------------------------\n");
	        	   outputStream.write("Generated in: " +  dtf.format(now) + "\t\t Books#: " + Librarian_ViewBook_FX.getNumOfBook() + "\t Magazine#: " + Librarian_ViewBook_FX.getNumOfMagazine() 
	        	   	+ "\t Video#: " + Librarian_ViewBook_FX.getNumOfVideo());  
	        	   outputStream.write("\n---------------------------------------------------------------------------------------------------------------\n\n\n");
	        	   outputStream.close();
	        	   statement.close();
	        	   count++;
	        	   
	        	   
	        	   
	        	   	Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setContentText("Record has been generated and added to the file.");
					alert.setHeaderText(null);
					alert.showAndWait();
	        	}
	   
           }
           catch(SQLException e)
           {
        	 System.out.println("Error: On Search query!");
        	 e.printStackTrace();   
           }          
           catch(FileNotFoundException e) { e.printStackTrace();}
           catch(IOException e) { e.printStackTrace();}
           
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error. Couldn't connect to PostgreSQL server!");
            e.printStackTrace();
        }
        
		return null;
	}

	
	
	/**
	 * This method is responsible to count the number of books , magazines and videos in the library by connection to the data base.<br>
	 * This method also uses a try catch method to reduce and handle exceptions.
	 */
	public static void GetQuantityType()
	{
        try
        {
            Connection connection = DriverManager.getConnection(Data_Connection.jdbcURL, Data_Connection.username, Data_Connection.password);
            System.out.println("Connected to PostgreSQL server.");

           try
           {
        	   Statement statement = connection.createStatement();
        	   ResultSet result = statement.executeQuery(queryItems);
        	   
        	   while(result.next())
        	   {
        		  
        		   setCOL_Kind(result.getString("type"));
        		   
        		   if(getCOL_Kind().equals("Book"))
        			   Librarian_ViewBook_FX.setNumOfBook(1);
        		   else if (getCOL_Kind().equals("Magazines"))
        			   Librarian_ViewBook_FX.setNumOfMagazine(1);
        		   else
        			   Librarian_ViewBook_FX.setNumOfVideo(1);
        	   }

        	   statement.close();
           }
           catch(SQLException e)
           {
        	 System.out.println("Error: On Search query (GetQuantity)!");
        	 e.printStackTrace();   
           }

            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error. Couldn't connect to PostgreSQL server! (GetQuantity)");
            e.printStackTrace();
        }
	}
	
	
	//Issue_Class///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * This method is responsible for issuing a item to the student or user.<br>
	 * This method is using try catch blocks to handle exceptions.<br>
	 * This method connects into data base and creates a new table called "Issued" which<br>
	 * will be used to add all the borrowed items in there with the information such as: <br>
	 * Item name, Item ID, Student name, Student ID, student Number and the date of issue.<br>
	 * and it will change the status of the available item to borrowed.
	 * @param name
	 * Item name of type string.
	 * @param id
	 * Item id of type integer.
	 * @param avalibility
	 * Availability of the time to issue if greater then 0 of type integer.
	 * @param StudentName
	 * Student name of type string.
	 * @param StudentID
	 * Student ID of type integer.
	 * @param StudentPhone
	 * Student phone of type string.
	 * @return
	 */
	
	public static Parent Issue(String name, int id, int avalibility, String StudentName, int StudentID, String StudentPhone)
	{
		
		boolean flag = true;
		
        try
        {
            Connection connection = DriverManager.getConnection(Data_Connection.jdbcURL, Data_Connection.username, Data_Connection.password);
            System.out.println("Connected to PostgreSQL server.");
            Statement statement = connection.createStatement();
            setTable_Name("Issued");
            setCOL_Name("Name");
            setCOL_ID_S("ID");
             
            
             try
             {
             	
             	statement.execute("create table if not exists "+ getTable_Name() + "(" + getCOL_Name() + " VARCHAR, " + getCOL_ID_S() + " INTEGER, " + Student_Table_Info.COL_StudentName + " VARCHAR, " 
             																	+ Student_Table_Info.COL_StudentID + " INTEGER, " + Student_Table_Info.COL_StudentPhone + " VARCHAR, " + Student_Table_Info.COL_Date + " VARCHAR)");
             }
             catch(SQLException e)
             {
             	System.out.println("Error: Creating Table Faild!");
             	e.printStackTrace();
             }
 
             
             
             try
             {
            	 ResultSet result = statement.executeQuery(queryIssue);
             	
            	  while(result.next())
           	   {
           		  
            		setCOL_ID(result.getInt("id"));
           		    
           		    
           		    if(getCOL_ID() == id)
           		    {
           		    	flag = false; 
           		    }
           	   }
            	 

            	 if(avalibility != 0 && flag == true)
            	 {
            		 String matchQuery = "UPDATE Items" + " SET status = 'Borrowed'" + " WHERE id = ?";
            		 PreparedStatement prestat = connection.prepareStatement(matchQuery);
                 	 prestat.setInt(1, id);
                 	 prestat.execute();
           
                 	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                 	 LocalDateTime now = LocalDateTime.now();  
                 	 
                 	 
        			String query = "Insert into " + getTable_Name() +" (" + getCOL_ID_S()  + "," + getCOL_Name() + "," + Student_Table_Info.COL_StudentName + "," + Student_Table_Info.COL_StudentID + "," + Student_Table_Info.COL_StudentPhone + "," + Student_Table_Info.COL_Date +")" + " Values (?,?,?,?,?,?)";
    			
                 	PreparedStatement prestat2 = connection.prepareStatement(query);
                 	prestat2.setInt(1, id);
                 	prestat2.setString(2, name);
                 	prestat2.setString(3, StudentName);
                 	prestat2.setInt(4, StudentID);
                 	prestat2.setString(5, StudentPhone);
                 	prestat2.setString(6, dtf.format(now));
                 	prestat2.execute();
                 	
                 	System.out.println("Issuing Item Completed.");
                 	Alert alert = new Alert(AlertType.INFORMATION);
    				alert.setTitle("Success");
    				alert.setContentText(name + " ID:" + id + " Item as been issued.");
    				alert.setHeaderText(null);
    				alert.showAndWait();
            	 }
            	 else
            	 {
            		Alert alert = new Alert(AlertType.ERROR);
     				alert.setTitle("Error:");
     				alert.setContentText(name + " ID: " + id + " Has already been borrowed!");
     				alert.setHeaderText(null);
     				alert.showAndWait();
            	 }
             }
             catch(SQLException e)
             {
             	System.out.println("Error: Could not add new Item!");
             	e.printStackTrace();
             }
           
            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error. Couldn't connect to PostgreSQL server!");
            e.printStackTrace();
        }
        
		return null;
	}
	
	
	/**
	 * This method is responsible to search through the items table and if the item that has been enter its status is available it will count<br>
	 * and increment the availability variable by 1 until there is no more.
	 * @param name
	 * Item name of type string.
	 */
	public static void SearchQuantity(String name)
	{
        try
        {
            Connection connection = DriverManager.getConnection(Data_Connection.jdbcURL, Data_Connection.username, Data_Connection.password);
            System.out.println("Connected to PostgreSQL server.");
            Librarian_IssueItem_FX.setAvalibility_V(0); 

           try
           {
        	   Statement statement = connection.createStatement();
        	   ResultSet result = statement.executeQuery(queryItems);
        	   
        	   while(result.next())
        	   {
        		  
        		   setCOL_Name(result.getString("name"));
        		   
        		   
        		   if(getCOL_Name().equals(name))
        		   {
        			   setCOL_Status(result.getString("status"));
        			   
        			   if(getCOL_Status().equals("Available"))
        				   Librarian_IssueItem_FX.setAvalibility_V(1);
        		   } 
        	   }

        	   statement.close();
           }
           catch(SQLException e)
           {
        	 System.out.println("Error: On Search query (Search)!");
        	 e.printStackTrace();   
           }

            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error. Couldn't connect to PostgreSQL server! (Search)");
            e.printStackTrace();
        }
	}
	
	
	//Returning/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * This method is responsible for returning the issued item back to the items table by updating the status of that item to available<br>
	 * also it will store the record of the returned item into the returned table.<br>
	 * It will create the borrowed table if it is not already made.
	 * @param name
	 * Item name of type string.
	 * @param id
	 * Item id of type integer.
	 * @param StudentName
	 * Student name of type string.
	 * @param StudentID
	 * Student id of type string.
	 * @param StudentPhone
	 * Student phone number of type string.
	 * @return
	 */
	public static Parent Return(String name, int id,String StudentName, int StudentID, String StudentPhone)
	{
		
		setTable_Name("returned");
		setCOL_Name("Name");
		setCOL_ID_S( "ID");
		boolean flag = false , flag2 = false;
		
        try
        {
            Connection connection = DriverManager.getConnection(Data_Connection.jdbcURL, Data_Connection.username, Data_Connection.password);
            System.out.println("Connected to PostgreSQL server.");
            Statement statement = connection.createStatement();
            
             
             try
             {
             	
             	statement.execute("create table if not exists "+ getTable_Name() + "(" + getCOL_Name() + " VARCHAR, " + getCOL_ID_S() + " INTEGER, " + Student_Table_Info.COL_StudentName + " VARCHAR, " 
             																	+ Student_Table_Info.COL_StudentID + " INTEGER, " + Student_Table_Info.COL_StudentPhone + " VARCHAR, " + Student_Table_Info.COL_Date + " VARCHAR)");
             }
             catch(SQLException e)
             {
             	System.out.println("Error: Creating Table Faild!");
             	e.printStackTrace();
             }
 
                         
             try
             {
	              ResultSet result = statement.executeQuery(queryIssue);
	              	
	           	  while(result.next())
	          	   {
	          		  
	           		setCOL_ID(result.getInt("id"));
	          		    
	          		    
	          		    if(getCOL_ID() == id)
	          		    {
	          		    	flag = true; 
	          		    }
	          		    
	          		   String Name = result.getString("name");
	          		   String St_Name = result.getString("student_name");
	          		   int St_ID = result.getInt("student_id");
	          		   String COL_StudentPhone2 = result.getString("student_phone");
	          		    
	          		    if(Name.equals(name) && St_Name.equals(StudentName) && St_ID == StudentID && COL_StudentPhone2.equals(StudentPhone))
	          		    {
	          		    	flag2 = true;
	          		    } 		    
	          	   }
            	 
	           	  	// To check if id exist
            	 	if(flag == true)
            	 	{
            	 		
            	 		// To check if the info is correct
            	 		if(flag2 == true)
            	 		{
	            		 String matchQuery = "UPDATE Items" + " SET status = 'Available'" + " WHERE id = ?";
	            		 PreparedStatement prestat = connection.prepareStatement(matchQuery);
	                 	 prestat.setInt(1, id);
	                 	 prestat.execute();
	                 	 
	                 	 
	                 	 String DeletQuery = "DELETE FROM Issued" + " WHERE student_id = ?";
	            		 PreparedStatement prestat2 = connection.prepareStatement(DeletQuery);
	                 	 prestat2.setInt(1, StudentID);
	                 	 prestat2.execute();
	                 	 
	                 	 
	                 	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	                 	 LocalDateTime now = LocalDateTime.now();  
	                 	 
	                 	 
	        			String query = "Insert into " + getTable_Name() +" (" + getCOL_Name() + "," + getCOL_ID_S() + "," + Student_Table_Info.COL_StudentName + "," + Student_Table_Info.COL_StudentID + "," + Student_Table_Info.COL_StudentPhone + "," + Student_Table_Info.COL_Date +")" + " Values (?,?,?,?,?,?)";
	    			
	                 	PreparedStatement prestat3 = connection.prepareStatement(query);
	                 	prestat3.setString(1, name);
	                 	prestat3.setInt(2, id);
	                 	prestat3.setString(3, StudentName);
	                 	prestat3.setInt(4, StudentID);
	                 	prestat3.setString(5, StudentPhone);
	                 	prestat3.setString(6, dtf.format(now));
	                 	prestat3.execute();
	                 	
	                 	System.out.println("Returned Item Completed.");
	                	Alert alert = new Alert(AlertType.INFORMATION);
	    				alert.setTitle("Success");
	    				alert.setContentText(name + " ID: " + id + " has returned.");
	    				alert.setHeaderText(null);
	    				alert.showAndWait();
            	 		}
            	 		else
            	 		{
            	 			Alert alert = new Alert(AlertType.ERROR);
            				alert.setTitle("Error");
            				alert.setContentText("Wrong Information has been provided!");
            				alert.setHeaderText(null);
            				alert.showAndWait();
            	 		}
            	 	}
            	 	else
            	 	{
            	 		Alert alert = new Alert(AlertType.ERROR);
        				alert.setTitle("Error");
        				alert.setContentText(name + " ID: " + id + " is not borrowed!");
        				alert.setHeaderText(null);
        				alert.showAndWait();
            	 	}
            	 
             }
             catch(SQLException e)
             {
             	System.out.println("Error: Could not add new Book!");
             	e.printStackTrace();
             }
           

            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println("Error. Couldn't connect to PostgreSQL server!");
            e.printStackTrace();
        }
        
		return null;
	}
}
