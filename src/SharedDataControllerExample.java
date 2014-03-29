
import java.io.IOException;
import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SharedDataControllerExample extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
		loader.setControllerFactory(SceneSelector.createControllerFactory(createData()));
		final Parent root = (Parent) loader.load();
		final Scene scene = new Scene(root, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private ObservableList<Person> createData() {
		return FXCollections.observableArrayList(
			Arrays.asList(
					new Person("Jacob", "Smith", "jacob.smith@example.com"),
			        new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
			        new Person("Ethan", "Williams", "ethan.williams@example.com"),
			        new Person("Emma", "Jones", "emma.jones@example.com"),
			        new Person("Michael", "Brown", "michael.brown@example.com")
			)	
		);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
