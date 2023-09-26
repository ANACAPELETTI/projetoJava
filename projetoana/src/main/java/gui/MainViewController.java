package gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainViewController {
	private synchronized <T> void loadView(String absoluteName, String title) {
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			stage.setTitle(title);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.show();
		} catch (IOException e) {
		}
	}
}
