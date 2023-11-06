package application;

import java.io.IOException;
import functions.PSO;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application {
	private static Scene mainScene;
	double x,y = 0;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			//FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			Parent loader = FXMLLoader.load(getClass().getResource("/gui/MainView.fxml"));
			primaryStage.initStyle(StageStyle.UNDECORATED);
			
			loader.setOnMousePressed(event -> {
	            x = event.getSceneX();
	            y = event.getSceneY();
	        });

			loader.setOnMouseDragged(event -> {
	            primaryStage.setX(event.getScreenX() - x);
	            primaryStage.setY(event.getScreenY() - y);
	        });
			
	        mainScene = new Scene(loader, 800, 500); // Atribuindo a cena à variável mainScene
	        primaryStage.setScene(mainScene);
	        primaryStage.show();
			
			/*ScrollPane scrollPane = loader.load();
			scrollPane.getLayoutBounds();
			scrollPane.getViewportBounds();
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			mainScene = new Scene(scrollPane);
			primaryStage.setResizable(true);*/
			//primaryStage.setHeight(200);
			//primaryStage.setWidth(300);
			// primaryStage.getIcons().add(new Image("/images/2.png"));
			// primaryStage.setMaximized(true);
			/*primaryStage.setScene(mainScene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("projetoAna");
			primaryStage.show();
			FadeTransition ft = new FadeTransition(Duration.millis(200), scrollPane);
			ft.setFromValue(0.0);
			ft.setToValue(1.0);
			ft.play();*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}
}
