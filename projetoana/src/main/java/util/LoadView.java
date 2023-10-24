package util;

import java.io.IOException;
import java.util.function.Consumer;

import application.Main;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import util.Alerts;

public class LoadView {

	public synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {

		try {

			Scene mainScene = Main.getMainScene();
			//String cssRibbonBar = this.getClass().getResource("/gui/css/ribbonbar.css").toExternalForm();
			//mainScene.getStylesheets().add(cssRibbonBar);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));

			VBox newVBox = loader.load();

			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVBox.getChildren().get(0);

			mainVBox.getChildren().clear();

			mainVBox.getChildren().add(mainMenu);

			mainVBox.getChildren().addAll(newVBox.getChildren());

			SequentialTransition seq = new SequentialTransition();

			for (int i = 1; i < mainVBox.getChildren().size(); i++) {
				FadeTransition ft = new FadeTransition(Duration.millis(200), mainVBox.getChildren().get(i));

				ft.setFromValue(0.0);
				ft.setToValue(1.0);
				seq.getChildren().add(ft);
			}
			seq.play();

			T controller = loader.getController();

			initializingAction.accept(controller);

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}