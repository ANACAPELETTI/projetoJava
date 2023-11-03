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

public class MainViewController {
	@FXML
	MenuItem novaTela;
	
	@FXML
	MenuItem PSO;
	
	@FXML
	MenuItem classificador;
	
	@FXML
	public void classificar () {
		LoadView loadView = new LoadView();
		loadView.loadView("/gui/DevView.fxml", (DevViewController controller) -> {
		});
	}
	
	@FXML
	public void classificarTeste () {
		LoadView loadView = new LoadView();
		loadView.loadView("/gui/ClassificadorTeste.fxml", (ClassificadorTesteController controller) -> {
		});
	}
	
	@FXML
	public void executarPso () {
		PSO pso = new PSO();
		pso.pso();
	}
}
