
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SharedDataControllerExample extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
		loader.setControllerFactory(new Callback<Class<?>, Object>() {

			@Override
			public Object call(Class<?> type) {
				try {
					Constructor<?> constructor = type.getConstructor(ObservableList.class);
					return constructor.newInstance(createData());
				} catch (NoSuchMethodException nsme) {
					try {
						return type.newInstance();
					} catch (Exception e) {
						e.printStackTrace();
						return null ;
					} 
				} catch (Exception exc) {
					exc.printStackTrace();
					return null ;
				}				
			}
			
		});
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
