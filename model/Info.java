package model;
/**
 * <h2>Info Abstract - Stores Item information</h2>
 * @author Rad Eshghi
 *
 */
public abstract class Info {
	
	 private static String TABLE_NAME = "";	 
	 private static String COL_NAME = "";
	 private static int COL_ID = 0;
	 private static String COL_ID_S = "";
	 private static String COL_Author = "";
	 private static int COL_Quantity = 0; 
	 private static String COL_Quantity_S = ""; 
	 private static String COL_Status = "";
	 private static String COL_Category = "";
	 private static String COL_Kind = "";
	
	 /**
	  * Default constructor
	  */
	 public Info()
	 {
		 
	 }
	 
	 
	 /**
	  * This method initialize the TABLE_NAME to table.
	  * @param table
	  * Of type String.
	  */
	 public static void setTable_Name(String table)
	 {
		 TABLE_NAME = table;
	 }
	 
	 /**
	  * This method return the TABLE_NAME.
	  * @return
	  */
	 public static String getTable_Name()
	 {
		 return TABLE_NAME;
	 }
	 
	 /**
	  * This method initialize the COL_NAME to COlName.
	  * @param COlName
	  * Of type String.
	  */
	 public static void setCOL_Name(String COlName)
	 {
		 COL_NAME = COlName;
	 }
	 
	 /**
	  * This method return the COL_NAME.
	  * @return
	  */
	 public static String getCOL_Name()
	 {
		 return COL_NAME;
	 }
	 
	 
	 /**
	  * This method initialize the COL_ID to table
	  * @param COlID
	  * Of type Integer.
	  */
	 public static void setCOL_ID(int COlID)
	 {
		 COL_ID = COlID;
	 }
	 
	 
	 /**
	  * This method return the COL_ID.
	  * @return
	  */
	 public static int getCOL_ID()
	 {
		 return COL_ID;
	 }
	 
	 /**
	  * This method initialize the COL_ID_S to table.
	  * @param COlID_S
	  * Of type String.
	  */
	 public static void setCOL_ID_S(String COlID_S)
	 {
		 COL_ID_S = COlID_S;
	 }
	 
	 /**
	  * This method return COL_ID_S.
	  * @return
	  */
	 public static String getCOL_ID_S()
	 {
		 return COL_ID_S;
	 }
	 
	 /**
	  * This method initialize the COL_Author to table.
	  * @param COLAuthor
	  * Of type String.
	  */
	 public static void setCOL_Author(String COLAuthor)
	 {
		 COL_Author = COLAuthor;
	 }
	 
	 /**
	  * This method return COL_Author.
	  * @return
	  */
	 public static String getCOL_Author()
	 {
		 return COL_Author;
	 }
	 
	 /**
	  * This method initialize the COL_Quantity to table.
	  * @param COLQuantity
	  */
	 public static void setCOL_Quantity(int COLQuantity)
	 {
		 COL_Quantity = COLQuantity;
	 }
	 
	 /**
	  *  This method return COL_Quantity.
	  * @return
	  */
	 public static int getCOL_Quantity()
	 {
		 return COL_Quantity;
	 }
	 
	 /**
	  * This method initialize the COL_Quantity to table.
	  * @param COLQuantity_S
	  * Of type String
	  */
	 public static void setCOL_Quantity_S(String COLQuantity_S)
	 {
		 COL_Quantity_S = COLQuantity_S;
	 }
	 
	 /**
	  * This method initialize the COL_Quantity_S to table.
	  * @return
	  */
	 public static String getCOL_Quantity_S()
	 {
		 return COL_Quantity_S;
	 }
	 
	 /**
	  * This method initialize the COL_Status to table.
	  * @param COLStatus
	  * Of type String
	  */
	 public static void setCOL_Status(String COLStatus)
	 {
		 COL_Status = COLStatus;
	 }
	 

	 /**
	  * This method initialize the COL_Status to table.
	  * @return
	  */
	 public static String getCOL_Status()
	 {
		 return COL_Status;
	 }
	 
	 /**
	  * This method initialize the COL_Category to table.
	  * @param COLCategory
	  * Of type String
	  */
	 public static void setCOL_Category(String COLCategory)
	 {
		 COL_Category = COLCategory;
	 }
	 
	 /**
	  * This method initialize the COL_Category to table.
	  * @return
	  */
	 public static String getCOL_Category()
	 {
		 return COL_Category;
	 }
	 
	
	 /**
	  * This method initialize the COL_Kind to table.
	  * @param COLKind
	  * Of type String
	  */
	 public static void setCOL_Kind(String COLKind)
	 {
		 COL_Kind = COLKind;
	 }
	 
	 /**
	  * This method initialize the COL_Kind to table.
	  * @return
	  */
	 public static String getCOL_Kind()
	 {
		 return COL_Kind;
	 }	 
	
}
