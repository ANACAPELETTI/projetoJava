package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Feedfoward.Feedfoward;
import functions.ImageReader;
import functions.subMatrix;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import layers.ConvolutionalLayer;
import layers.PoolingLayer;

public class Main extends Application {
	ImageReader imageReader = new ImageReader();
	PoolingLayer poolingLayer = new PoolingLayer();
	ConvolutionalLayer convolutionalLayer = new ConvolutionalLayer();
	private static Scene mainScene;
	subMatrix subMat = new subMatrix();
	Feedfoward feedfoward = new Feedfoward();

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollPane = loader.load();
			scrollPane.getLayoutBounds();
			scrollPane.getViewportBounds();
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			mainScene = new Scene(scrollPane);
			primaryStage.setResizable(true);
			primaryStage.setHeight(200);
			primaryStage.setWidth(300);
			// primaryStage.getIcons().add(new Image("/images/2.png"));
			// primaryStage.setMaximized(true);
			primaryStage.setScene(mainScene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("projetoAna");
			primaryStage.show();
			FadeTransition ft = new FadeTransition(Duration.millis(200), scrollPane);
			ft.setFromValue(0.0);
			ft.setToValue(1.0);
			ft.play();
			imageReader.imageReader();

			// exemplo de matriz 2D representando a imagem
			float[][] imageMatriz = {                     
					{ 192, 146,  88,  58,  11, 222, 130,  87, 186 },
					{  46,  85,  73,  94, 150, 180,  37, 176,  91 },
					{  99,  15,  22,  73, 168, 139, 119, 235,  78 },
					{ 241, 213, 242,  32,  74, 184,  37,  58, 218 },
					{ 149,  46, 196,  38,  62,  84,  46, 249, 115 },
					{  64, 207,  15, 136,  21,  85, 238,  40, 103 },
					{  98,  96, 234,  42,  67,  51, 249, 178, 156 },
					{  77,  31, 148,  84,  64, 163, 206,  56,  19 },
					{  41,  30,  18,  72,  83, 223,  53, 226, 252 }
			};

			List<float[][]> listImageMatriz = new ArrayList<float[][]>();
			listImageMatriz.add(imageMatriz);
			
			float[][] kernel = { 
                    { 1, 1, 1, 1 }, 
                    { 1, 1, 1, 1 }, 
                    { 1, 1, 1, 1 }, 
                    { 1, 1, 1, 1 } 
            };
            
            float[][] kernel1 = { 
                    { 0, 1, 0, 0 }, 
                    { 1, 5, 5, 1 }, 
                    { 1, 5, 5, 1 }, 
                    { 0, 1, 0, 0 } 
            };
            
            float[][] kernel2 = { 
                    { 0, 0, 0, 0 }, 
                    { 1, 1, 0, 0 }, 
                    { 0, 0, 1, 1 }, 
                    { 0, 0, 0, 0 } 
            };
			
			List<float[][]> listaKernels = Arrays.asList(kernel, kernel1, kernel2);
			List<float[][]> listaKernels2 = Arrays.asList(kernel1, kernel2);
			List<List<float[][]>> listaListaKernels = new ArrayList<List<float[][]>>();
			listaListaKernels.add(listaKernels);
			listaListaKernels.add(listaKernels2);
			
			int[] pooling1 = {1, 2}; //pooling mínimo, com tamanho 2
			int[] pooling2 = {2, 2}; //pooling médio, com tamanho 2
			int[] pooling3 = {3, 2}; //pooling máximo, com tamanho 2
			
			List<int[]> listaPoolings = new ArrayList<int[]>(Arrays.asList(pooling1, pooling3));
			
			List<Integer> listaOrdemOperacoes = new ArrayList<Integer>(Arrays.asList(0,1,0,1));
			
			float resultadoFinal = feedfoward.feedfoward(listaOrdemOperacoes, listImageMatriz, listaListaKernels, listaPoolings);
			
			System.out.println("Resultado final: " + resultadoFinal);
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
