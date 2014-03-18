import java.io.IOException;
import java.lang.reflect.Constructor;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;


public class SceneSelector {
	
	private final Callback<Class<?>, Object> controllerFactory ;
	
	public SceneSelector(final ObservableList<Person> data) {
		controllerFactory = new Callback<Class<?>, Object>() {

			@Override
			public Object call(Class<?> type) {
				try {
					Constructor<?> constructor = type
							.getConstructor(ObservableList.class);
					return constructor.newInstance(data);
				} catch (NoSuchMethodException e) {
					try {
						return type.newInstance();
					} catch (Exception e1) {
						e1.printStackTrace();
						return null ;
					} 
				} catch (Exception exc) {
					exc.printStackTrace();
					return null ;
				}
			}
			
		};
	}
	
	public void selectScene(String fxmlFile, Scene scene) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
		loader.setControllerFactory(controllerFactory);
		scene.setRoot((Parent)loader.load());
	}
}
