package gui;

import entity.FeedfowardEntity;
import feedfoward.Feedfoward;
import functions.Classificador;
import functions.ImageReader;
import functions.Import;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import util.Kernels;
import util.LoadView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ResultadoController {
	ImageReader imageReader = new ImageReader();
	FeedfowardEntity feedfowardEntity = new FeedfowardEntity();
	Feedfoward feedfoward = new Feedfoward();
	Import importe = new Import();
	Classificador classificador = new Classificador();
	Kernels kernelNovo = new Kernels();

	@FXML
	private AnchorPane pane3;

	public void setPane3(AnchorPane pane3) {
		this.pane3 = pane3;
	}
	
	public AnchorPane getPane3() {
		return this.pane3;
	}

	@FXML
	Button btReiniciar;

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

	public void setImage(Image image) {
		imagemView.setImage(image);
		imagemView.setPreserveRatio(true);
		System.out.println("Imagem setada");
	}
	
	public void setText(String textoResultado) {
		texto.setText(textoResultado);
		System.out.println("Texto inserido");
	}

	@FXML
	public void Reiniciar() {
		LoadView loadView = new LoadView();
		loadView.loadView("/gui/ClassificadorTeste.fxml", (ClassificadorTesteController controller) -> {
			controller.setAnchorPane(pane3);
		}, pane3);
	}

	@FXML
	public void copiarTexto() {
		String textoParaCopiar = texto.getText();
		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent content = new ClipboardContent();
		content.putString(textoParaCopiar);
		clipboard.setContent(content);
		System.out.println("Texto copiado para a área de transferência: " + textoParaCopiar);
	}

	@FXML
	public void exportarTexto() {
		String textoParaExportar = texto.getText();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Salvar Arquivo Texto");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Arquivos de Texto (*.txt)", "*.txt"));

		String nomePadrao = "TextoExportado.txt";
		fileChooser.setInitialFileName(nomePadrao);

		File file = fileChooser.showSaveDialog(null);

		if (file != null) {
			salvarTextoNoArquivo(textoParaExportar, file);
			System.out.println("Texto exportado para o arquivo: " + file.getAbsolutePath());
		}
	}

	private void salvarTextoNoArquivo(String texto, File file) {
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(texto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
