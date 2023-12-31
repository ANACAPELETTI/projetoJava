package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.FeedfowardEntity;
import feedfoward.Feedfoward;
import functions.Classificador;
import functions.ImageReader;
import functions.Import;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
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
	AnchorPane anchorPane;
	
	public AnchorPane getAnchorPane() {
		return anchorPane;
	}

	public void setAnchorPane(AnchorPane anchorPane) {
		this.anchorPane = anchorPane;
	}

	@FXML
	Button botao = new Button();
	
	@FXML
	public void selectFile() {
		String caminho = "";
		
		ExtensionFilter image = new ExtensionFilter("Imagem (*.png)", "*.png", "*.jfif");
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
				
				loadView.loadView("/gui/Resultado.fxml", (ResultadoController controller) -> {
	                controller.setPane3(anchorPane);
	                controller.setImage(imagem);

	                Platform.runLater(() -> {
	                    System.out.println("Chama classifica texto");
	                    classificaTexto(anchorPane, listImageMatriz, imagem);
	                });
	            }, anchorPane);
			} catch (FileNotFoundException e) {
				System.out.println("Teste");
			}
		}
	}
	
	@FXML
	void handleFileOverEvent(DragEvent event) {
	    Dragboard db = event.getDragboard();
	    if (db.hasFiles()) {
	        event.acceptTransferModes(TransferMode.COPY);
	    } else{
	        event.consume();
	    }
	}
	
	@FXML
	void handleFileDroppedEvent(DragEvent event) { //função que pega a imagem do usuário, quando ele arrastar ela
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
				
				/*loadView.loadView("/gui/Resultado.fxml", (ResultadoController controller) -> {
			        controller.setPane3(anchorPane);
			        controller.setImage(imagem);
			    }, anchorPane);*/
				
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
				
				/*loadView.loadView("/gui/Resultado.fxml", (ResultadoController controller) -> {
			        controller.setPane3(anchorPane); // Certifique-se de que este método existe em ResultadoController
			        controller.setText(resultado);
			    }, anchorPane);*/
				
				System.out.println(classificador.classifica(resultadoFeedfoward, 0));
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Teste");
			}
		}
	}
	
	public void classificaTexto(AnchorPane anchorPane, List<float[][]> listImageMatriz, Image imagem) {
		System.out.println("Classifica texto");
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
	            controller.setPane3(anchorPane);
	            controller.setImage(imagem);
	            System.out.println("Chamando setText");
	            controller.setText(resultado); // Certifique-se de chamar o método correto para setar o texto
	        }, anchorPane);
		System.out.println(classificador.classifica(resultadoFeedfoward, 0));
	}
}
