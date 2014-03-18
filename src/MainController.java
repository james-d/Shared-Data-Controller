import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Arrays;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.util.Callback;


public class MainController {
	@FXML
	private ToggleGroup contentSelectionToggleGroup ;
	@FXML
	private ToggleButton tableToggleButton ;
	@FXML
	private ToggleButton editorToggleButton ;
	@FXML
	private Pane contentHolder ;
	
	private ObservableList<Person> data ;
	
	private Callback<Class<?>, Object> controllerFactory ;
	
	public void initialize() {
		contentSelectionToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> obs,
					Toggle oldToggle, Toggle newToggle) {
				String fxmlFile = null ;
				if (newToggle == tableToggleButton) {
					fxmlFile = "TableView.fxml" ;
				} else if (newToggle == editorToggleButton) {
					fxmlFile = "Editor.fxml" ;
				}
				if (fxmlFile != null) {
					loadContent(fxmlFile);
				}
			}
			
		});
		
		createData();
		initializeControllerFactory();
	}
	
	private void loadContent(String fxmlFile) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
			loader.setControllerFactory(controllerFactory);
			Parent content = (Parent) loader.load();
			contentHolder.getChildren().setAll(content);
		} catch (IOException e) {
			e.printStackTrace();
			contentHolder.getChildren().setAll(new Label("An error occurred loading the user interface"));
		}
	}
	
	private void initializeControllerFactory() {
		controllerFactory = new Callback<Class<?>, Object>() {
			@Override
			public Object call(Class<?> type) {
				try {
					Constructor<?> constructor = type.getConstructor(ObservableList.class);
					return constructor.newInstance(data);
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
		};
	}
	
	private void createData() {
		data = FXCollections.observableArrayList(
			Arrays.asList(
					new Person("Jacob", "Smith", "jacob.smith@example.com"),
			        new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
			        new Person("Ethan", "Williams", "ethan.williams@example.com"),
			        new Person("Emma", "Jones", "emma.jones@example.com"),
			        new Person("Michael", "Brown", "michael.brown@example.com")
			)	
		);
	}
}
