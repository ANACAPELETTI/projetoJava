package util;

import java.io.IOException;
import java.util.function.Consumer;
import javafx.application.Platform;
import application.Main;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import util.Alerts;

public class LoadView {
	public synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction, AnchorPane ancorPane) {
		try {
			Scene mainScene = Main.getMainScene();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			AnchorPane newVBox = loader.load();
			Platform.runLater(() -> {
				ancorPane.getChildren().clear();
				ancorPane.getChildren().add(newVBox);
				T controller = loader.getController();
                initializingAction.accept(controller);
			});

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}