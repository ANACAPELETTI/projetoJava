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
	
	private synchronized <T> void loadView(String absoluteName, String title) {
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			stage.setTitle(title);
			stage.setScene(scene);
			stage.setResizable(true);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.show();
		} catch (IOException e) {
		}
	}
	
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
