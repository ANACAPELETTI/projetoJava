package gui;

import java.io.IOException;

import functions.PSO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.LoadView;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable{
	
	@FXML
    private AnchorPane pane1, pane2, pane3;

    @FXML
    private ImageView exit, menu;
    
    public void initialize(URL location, ResourceBundle resources) {
        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });

        pane1.setVisible(false);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5),pane1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5),pane2);
        translateTransition.setByX(-600);
        translateTransition.play();

        menu.setOnMouseClicked(event -> {
            pane1.setVisible(true);

            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5),pane1);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.15);
            fadeTransition1.play();

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5),pane2);
            translateTransition1.setByX(+600);
            translateTransition1.play();
            
            
            TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.5),pane3);
            pane3.setPrefWidth(pane3.getWidth() + 193);
            translateTransition2.setByX(+193);
            translateTransition2.play();
        });

        pane1.setOnMouseClicked(event -> {
            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5),pane1);
            fadeTransition1.setFromValue(0.15);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                pane1.setVisible(false);
            });

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5),pane2);
            translateTransition1.setByX(-600);
            translateTransition1.play();
            
            TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.5),pane3);
            pane3.setPrefWidth(pane3.getWidth() - 193);
            translateTransition2.setByX(-193);
            translateTransition2.play();
        });
    }
	
	@FXML
	Button novaTela;
	
	@FXML
	Button PSO;
	
	@FXML
	Button classificador;
	
	@FXML
	public void classificar () {
    	LoadView loadView = new LoadView();
        loadView.loadView("/gui/DevView.fxml", (DevViewController controller) -> {}, pane3 );
	}
	
	@FXML
	public void classificarTeste () {
		LoadView loadView = new LoadView();
        loadView.loadView("/gui/ClassificadorTeste.fxml", (ClassificadorTesteController controller) -> {
        	controller.setAnchorPane(pane3);
        }, pane3 );
	}
	
	@FXML
	public void executarPso () {
        PSO pso = new PSO();
        Thread thread = new Thread(() -> { // Executa o PSO em uma nova Thread
            pso.pso();
        });
        thread.start();
	}
}
