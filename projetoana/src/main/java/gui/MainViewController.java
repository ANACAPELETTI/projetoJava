package gui;

import java.net.URL;
import java.util.ResourceBundle;

import functions.PSO;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import util.LoadView;

public class MainViewController implements Initializable{
	
	@FXML
    private AnchorPane pane1, pane2, pane3;

    @FXML
    private ImageView exit, exit1, menu;
    
    public void initialize(URL location, ResourceBundle resources) {
        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        
        exit1.setOnMouseClicked(event -> {
            System.exit(0);
        });

        pane1.setVisible(false);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5),pane1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        
        TranslateTransition translateTransition5 = new TranslateTransition(Duration.seconds(0.5),pane2);
        translateTransition5.setByX(-900);
        translateTransition5.play();

        menu.setOnMouseClicked(event -> {
            pane1.setVisible(true);
            
            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.01),pane1);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.15);
            fadeTransition1.play();
            
            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.001),pane2);
            translateTransition1.setByX(+900);
            translateTransition1.play();
            
            TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.001),pane3);
            pane3.setPrefWidth(pane3.getWidth() + 90);
            translateTransition2.setByX( + 90);
            translateTransition2.play();
        });

        pane1.setOnMouseClicked(event -> {
        	
            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.01),pane1);
            fadeTransition1.setFromValue(0.15);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                pane1.setVisible(false);
            });

            TranslateTransition translateTransition3 = new TranslateTransition(Duration.seconds(0.001),pane2);
            translateTransition3.setByX(-900);
            translateTransition3.play();
            
            TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.001),pane3);
            pane3.setPrefWidth(pane3.getWidth() - 90);
            translateTransition2.setByX( - 90);
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
