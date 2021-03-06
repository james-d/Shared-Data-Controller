import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;


public class TableViewController {
	@FXML 
	private TableView<Person> table ;
	
	private ObservableList<Person> data ;
	
	public TableViewController(ObservableList<Person> data) {
		this.data = data ;
	}
	
	public void initialize() {
		table.setItems(data);
	}
	
	@FXML
	private void showEditor() throws IOException {
		SceneSelector selector = new SceneSelector(data);
		selector.selectScene("Editor.fxml", table.getScene());
	}
}
