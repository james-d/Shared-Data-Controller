import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class EditorController {
	@FXML
	private TextField firstName ;
	@FXML
	private TextField lastName ;
	@FXML
	private TextField email ;
	
	private ObservableList<Person> data ;
	
	public EditorController(ObservableList<Person> data) {
		this.data = data ;
	}

	@FXML
	private void addPerson() {
		data.add( new Person(
				firstName.getText(), 
				lastName.getText(), 
				email.getText()
			)
		);
		clearFields();
	}
	
	@FXML
	private void clearFields() {
		firstName.setText("");
		lastName.setText("");
		email.setText("");
	}
}
