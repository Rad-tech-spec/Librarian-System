package application.librarian;


import dao.Data;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.WaitingList_Table_Info;
/**
 * <h2>View Waiting - JavaFX And Control</h2>
 * @author Rad Eshghi
 *
 */

public class Librarian_WaitingList_FX {
	
	
	public static TableView<WaitingList_Table_Info> tableview = new TableView<WaitingList_Table_Info>();
	public static int flag = 0;
	
	/**
	 * This method creates the tabl-view to show all the students that are in the waiting list<br>
	 * Also to delete once that student has been severed.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Parent ViewWaiting()
	{
		
		Text scenetitle = new Text("Waiting List");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		
		
		
		if(flag == 0)
		{
			TableColumn<WaitingList_Table_Info, String> RequestNum = new TableColumn<>("Request#");
			RequestNum.setCellValueFactory(new PropertyValueFactory<>("requestNum"));
			
			TableColumn<WaitingList_Table_Info, String>  StudentID = new TableColumn<>("ST ID");
			StudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
			
			TableColumn<WaitingList_Table_Info, String> StudentName = new TableColumn<>("ST Name");
			StudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
			
			TableColumn<WaitingList_Table_Info, String> StudentPhone = new TableColumn<>("ST Phone");
			StudentPhone.setCellValueFactory(new PropertyValueFactory<>("studentPhone"));
			
			TableColumn<WaitingList_Table_Info, String> StudentStanding = new TableColumn<>("ST Standing");
			StudentStanding.setCellValueFactory(new PropertyValueFactory<>("standing"));
			
			TableColumn<WaitingList_Table_Info, String> StudentDegree = new TableColumn<>("ST Degree");
			StudentDegree.setCellValueFactory(new PropertyValueFactory<>("degreelvl"));
			
			TableColumn<WaitingList_Table_Info, String> RequestDate = new TableColumn<>("Request Date");
			RequestDate.setCellValueFactory(new PropertyValueFactory<>("date"));
			
			TableColumn<WaitingList_Table_Info, String> ItemID = new TableColumn<>("Item ID");
			ItemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
		      
			
			tableview.getColumns().addAll(RequestNum, StudentID, StudentName, StudentPhone, StudentStanding, StudentDegree, RequestDate, ItemID);
		
			
			flag++;
		}
		
		Data.Get_Waitng_data();
		
		Button Delete = new Button("Delete");
		Delete.setMaxWidth(100);
		Delete.setMaxHeight(10);
		Delete.setOnAction(e ->{
			WaitingList_Table_Info selectedItem = tableview.getSelectionModel().getSelectedItem();
			tableview.getItems().remove(selectedItem);
		});


		// Create a VBox to store all the HBox's in there. 
		final VBox vbox = new VBox(15);
		vbox.setAlignment(Pos.CENTER);
		
		
		// Created a menu button to return to the main scene.
		Button Menu = new Button("Menu");
		Menu.setMaxWidth(100);
		Menu.setMaxHeight(20);
		Menu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					
					//flag = 0;
					Menu.getScene().setRoot(Librarian_Main_FX.LibrarianMenu());
				} catch (Exception e) {
				
					e.printStackTrace();
				}
			}
		});
		
		// Adding all the hbox;s and menu into the vbox.
		vbox.getChildren().addAll(scenetitle, tableview, Delete ,Menu);
		
		return vbox;		
	}
}
