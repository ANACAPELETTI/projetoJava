package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.FeedfowardEntity;
import feedfoward.Feedfoward;
import functions.Classificador;
import functions.ImageReader;
import functions.Import;
import functions.PSO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import util.Kernels;
import util.LoadView;

public class ResultadoController {
	ImageReader imageReader = new ImageReader();
	FeedfowardEntity feedfowardEntity = new FeedfowardEntity();
	Feedfoward feedfoward = new Feedfoward();
	Import importe = new Import();
	Classificador classificador = new Classificador();
	Kernels kernelNovo = new Kernels();
	
	@FXML
	Button btReiniciar = new Button();
	
	@FXML
	Button btCopiar = new Button();
	
	@FXML
	Button btExportar = new Button();
	
	@FXML
	GridPane grid = new GridPane();
	
	@FXML
	ImageView imagemView = new ImageView();
	
	@FXML
	ScrollPane scrollPane = new ScrollPane();
	
	@FXML
	Label texto = new Label();
	
	public void init(Image image, String textoResultado) {
		imagemView.setImage(image);
		texto.setText(textoResultado);
	}
}
