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
	
	boolean isMenuOpen = false;
	
	@FXML
    private AnchorPane pane2, pane3;

    @FXML
    private ImageView exit, menu;
    
    public void initialize(URL location, ResourceBundle resources) {
        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        
        TranslateTransition translateTransition5 = new TranslateTransition(Duration.seconds(0.5),pane2);
        translateTransition5.setByX(-900);
        translateTransition5.play();
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
	public void onClick () {
		if(isMenuOpen) {
            TranslateTransition translateTransition3 = new TranslateTransition(Duration.seconds(0.5),pane2);
            translateTransition3.setByX(-900);
            translateTransition3.play();
            
            TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.5),pane3);
            pane3.setPrefWidth(pane3.getWidth() - 90);
            translateTransition2.setByX( - 90);
            translateTransition2.play();
            isMenuOpen = false;
		}
	}
	
	@FXML
	public void openOnClick () {
		if(!isMenuOpen) {
		    TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5),pane2);
		    translateTransition1.setByX(+900);
		    translateTransition1.play();
		    
		    TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.5),pane3);
		    pane3.setPrefWidth(pane3.getWidth() + 90);
		    translateTransition2.setByX( + 90);
		    translateTransition2.play();
		    isMenuOpen = true;
		} else {
			TranslateTransition translateTransition3 = new TranslateTransition(Duration.seconds(0.5),pane2);
            translateTransition3.setByX(-900);
            translateTransition3.play();
            
            TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.5),pane3);
            pane3.setPrefWidth(pane3.getWidth() - 90);
            translateTransition2.setByX( - 90);
            translateTransition2.play();
            isMenuOpen = false;
		}
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
