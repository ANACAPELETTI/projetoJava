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

public class ClassificadorTesteController {
	ImageReader imageReader = new ImageReader();
	FeedfowardEntity feedfowardEntity = new FeedfowardEntity();
	Feedfoward feedfoward = new Feedfoward();
	Import importe = new Import();
	Classificador classificador = new Classificador();
	Kernels kernelNovo = new Kernels();
	LoadView loadView = new LoadView();
	
	@FXML
	Button botao = new Button();
	
	@FXML
	ImageView imagemView = new ImageView();
	
	@FXML
	Label texto = new Label();
	
	@FXML
	public void selectFile() {
		String caminho = "";
		
		ExtensionFilter image = new ExtensionFilter("Imagem (*.png)", "*.png");
		FileChooser fileChooser = new FileChooser();

		fileChooser.getExtensionFilters().addAll(image);

		File selectedFile = fileChooser.showOpenDialog(null);
		
		if (selectedFile != null) {
			caminho = selectedFile.getPath();
		}
		if (caminho != "") {
			try {
				FileInputStream file = new FileInputStream(new File(caminho));
				float[][] imageMatriz = imageReader.imageReader(selectedFile.getAbsolutePath());
				List<float[][]> listImageMatriz = new ArrayList<float[][]>();
				listImageMatriz.add(imageMatriz);
				Image imagem = new Image(selectedFile.getAbsolutePath());
				imagemView.setImage(imagem);
				
				imagemView.setFitHeight(30);
				imagemView.setFitWidth(30);
				
				int[] pooling1 = {1, 2}; //pooling mínimo, com tamanho 2
				int[] pooling2 = {2, 2}; //pooling médio, com tamanho 2
				int[] pooling3 = {3, 2}; //pooling máximo, com tamanho 2
				
				
				List<int[]> listaPoolings = new ArrayList<int[]>(Arrays.asList(pooling1, pooling1, pooling1, pooling1));
				feedfowardEntity.setListaPoolings(listaPoolings);
				
				List<Integer> listaOrdemOperacoes = new ArrayList<Integer>(Arrays.asList(0, 1, 0, 1, 0, 0));
				List<List<List<float[][]>>> kernels = new ArrayList<List<List<float[][]>>>();
				
				kernels.add(kernelNovo.listaDeMatrizes());
				
				float resultadoFeedfoward = feedfoward.feedfoward(listaOrdemOperacoes, listImageMatriz, kernels.get(0), listaPoolings);
				texto.setText(String.valueOf(classificador.classifica(resultadoFeedfoward, 0)));
				
				System.out.println(classificador.classifica(resultadoFeedfoward, 0));
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	public void selectFile() {
		String caminho = "";
		
		ExtensionFilter image = new ExtensionFilter("Imagem (*.png)", "*.*");
		FileChooser fileChooser = new FileChooser();

		fileChooser.getExtensionFilters().addAll(image);

		File selectedFile = fileChooser.showOpenDialog(null);
		
		if (selectedFile != null) {
			caminho = selectedFile.getPath();
		}
		if (caminho != "") {
			try {
				FileInputStream file = new FileInputStream(new File(caminho));
				float[][] imageMatriz = imageReader.imageReader(selectedFile.getAbsolutePath());
				List<float[][]> listImageMatriz = new ArrayList<float[][]>();
				listImageMatriz.add(imageMatriz);
				Image imagem = new Image(selectedFile.getAbsolutePath());
				imagemView.setImage(imagem);
				
				imagemView.setFitHeight(30);
				imagemView.setFitWidth(30);
				
				matrizConfusao.getColumnConstraints().clear();
				ColumnConstraints colResultados = new ColumnConstraints();
				colResultados.setFillWidth(false);
				colResultados.setMinWidth(50);
				colResultados.setMaxWidth(50);
				colResultados.setHalignment(HPos.CENTER);
				matrizConfusao.getColumnConstraints().add(colResultados);
				matrizConfusao.getColumnConstraints().add(colResultados);
				
				int[] pooling1 = {1, 2}; //pooling mínimo, com tamanho 2
				int[] pooling2 = {2, 2}; //pooling médio, com tamanho 2
				int[] pooling3 = {3, 2}; //pooling máximo, com tamanho 2
				
				
				List<int[]> listaPoolings = new ArrayList<int[]>(Arrays.asList(pooling1, pooling1, pooling1, pooling1));
				feedfowardEntity.setListaPoolings(listaPoolings);
				
				List<Integer> listaOrdemOperacoes = new ArrayList<Integer>(Arrays.asList(0, 1, 0, 1, 0, 0));
				List<List<List<float[][]>>> kernels = new ArrayList<List<List<float[][]>>>();
				
				try {
					kernels.add(importe.loadExcelFile(4, 10)); //Tamanho dos kernels (3x3) e Quantidade de matrizes diferentes em cada planilha
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				float resultadoFeedfoward = feedfoward.feedfoward(listaOrdemOperacoes, listImageMatriz, kernels.get(0), listaPoolings);
				texto.setText(String.valueOf(classificador.classifica(resultadoFeedfoward, 0)));
				
				System.out.println(classificador.classifica(resultadoFeedfoward, 0));
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	 */
	
	@FXML
	void handleFileOverEvent(DragEvent event)
	{
	    Dragboard db = event.getDragboard();
	    if (db.hasFiles())
	    {
	        event.acceptTransferModes(TransferMode.COPY);
	    }
	    else
	    {
	        event.consume();
	    }
	}
	
	@FXML
	void handleFileDroppedEvent(DragEvent event){ //função que pega a imagem do usuário, quando ele arrastar ela
	    Dragboard db = event.getDragboard();
	    String caminho = "";
	    File file2 = db.getFiles().get(0);
	    if (file2 != null) {
			caminho = file2.getPath();
		}
		if (caminho != "") {
			try {
				FileInputStream file = new FileInputStream(new File(caminho));
				float[][] imageMatriz = imageReader.imageReader(file2.getAbsolutePath());
				List<float[][]> listImageMatriz = new ArrayList<float[][]>();
				listImageMatriz.add(imageMatriz);
				Image imagem = new Image(file2.getAbsolutePath());
				
				int[] pooling1 = {1, 2}; //pooling mínimo, com tamanho 2
				int[] pooling2 = {2, 2}; //pooling médio, com tamanho 2
				int[] pooling3 = {3, 2}; //pooling máximo, com tamanho 2
				
				
				List<int[]> listaPoolings = new ArrayList<int[]>(Arrays.asList(pooling1, pooling1, pooling1, pooling1));
				feedfowardEntity.setListaPoolings(listaPoolings);
				
				List<Integer> listaOrdemOperacoes = new ArrayList<Integer>(Arrays.asList(0, 1, 0, 1, 0, 0));
				List<List<List<float[][]>>> kernels = new ArrayList<List<List<float[][]>>>();
				
				kernels.add(kernelNovo.listaDeMatrizes());
				
				float resultadoFeedfoward = feedfoward.feedfoward(listaOrdemOperacoes, listImageMatriz, kernels.get(0), listaPoolings);
				
				String resultado = String.valueOf(classificador.classifica(resultadoFeedfoward, 0));
				
				loadView.loadView("/gui/Resultado.fxml", (ResultadoController controller) -> {
					controller.imagemView.setImage(imagem);
					controller.imagemView.setFitWidth(imagem.getWidth());
					controller.imagemView.setFitHeight(imagem.getHeight());
					controller.texto.setText(resultado);
					//controller.scrollPane.setPrefWidth()
				});
				
				System.out.println(classificador.classifica(resultadoFeedfoward, 0));
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Teste");
			}
		}
	}
}