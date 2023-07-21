package application;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import functions.Convolutional;
import functions.ImageReader;
import functions.subMatrix;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
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
					{ 2, 4, 1, 5 }, 
					{ 6, 8, 3, 2 }, 
					{ 9, 7, 2, 1 }, 
					{ 8, 5, 3, 6 } 
			};

			float[][] kernel = { 
					{ 1, 1, 1 }, 
					{ 1, 1, 1 }, 
					{ 1, 1, 1 } 
			};
			
			float[][] kernel1 = { 
					{ 0, 1, 0 }, 
					{ 1, 1, 1 }, 
					{ 0, 1, 0 } 
			};
			
			List<float[][]> listaKernels = Arrays.asList(kernel, kernel1);
			

			// tamanho do pooling quadrado (nesse caso, 2x2)
			int poolSize = 2;

			/**
			 * CONVOLUÇÃO
			 * 
			 * 
			 * 630.0 720.0 810.0
			 * 
			 * 1080.0 1170.0 1260.0
			 * 
			 * 1530.0 1620.0 1710.0
			 * 
			 **/
			/*
			 * float[][] submatriz = new float[3][3]; //cria a matriz para a submatriz
			 * submatriz = subMat.subMatrix(imageMatriz, 2, 2, 2); //retorna todas as
			 * submatrizes
			 * 
			 * for (int i = 0; i < submatriz.length; i++) { for (int j = 0; j <
			 * submatriz[0].length; j++) { System.out.print(submatriz[i][j] + " "); }
			 * System.out.println("\n"); }
			 */
			// chama a função de convolução, passando como parâmetro:
			// tipo de convolução, matriz da imagem e o kernel a ser utilizado
			
			  List<float[][]> convolutionalMatrix = convolutionalLayer.ConvolutionalLayer(imageMatriz, listaKernels);
			  
			  System.out.println("\n Convolução: \n"); // Exibir a matriz resultante após max-pooling 
			  convolutionalMatrix.forEach(a -> {
				  for (int i = 0; i < a.length; i++) { 
					  for (int j  = 0; j < a[0].length; j++) {
						  System.out.print(a[i][j] + " "); 
					  }
					  System.out.println("\n"); 
					}
				  System.out.println("-------");
			  });
			
			/** POOLING **/
			/*
			// chama a função de pooling, passando como parâmetro:
			// tipo do pooling, matriz da imagem e o tamanho do pooling
			float[][] pooledMatrix = poolingLayer.PoolingLayer(3, imageMatriz, poolSize);

			System.out.println("\n Pooling: \n");
			// Exibir a matriz resultante após max-pooling
			for (int i = 0; i < pooledMatrix.length; i++) {
				for (int j = 0; j < pooledMatrix[0].length; j++) {
					System.out.print(pooledMatrix[i][j] + " ");
				}
				System.out.println("\n");
			}
			*/
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
