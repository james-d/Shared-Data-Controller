import java.io.IOException;

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
	private void addPerson() throws IOException {
		data.add( new Person(
				firstName.getText(), 
				lastName.getText(), 
				email.getText()
			)
		);
		SceneSelector selector = new SceneSelector(data);
		selector.selectScene("TableView.fxml", firstName.getScene());
	}
	
	@FXML
	private void clearFields() {
		firstName.setText("");
		lastName.setText("");
		email.setText("");
	}
}
