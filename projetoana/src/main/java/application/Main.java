package application;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import Feedfoward.Feedfoward;
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
					{10, 20, 30, 40, 50},
                    {60, 70, 80, 90, 100},
                    {110, 120, 130, 140, 150},
                    {160, 170, 180, 190, 200},
                    {210, 220, 230, 240, 250}
			};

			float[][] kernel = { 
					{ 1, 1 }, 
					{ 1, 1 }
			};
			
			float[][] kernel1 = { 
					{ 0, -1 }, 
					{ -1, 5 }
			};
			
			float[][] kernel2 = { 
					{ 1, 0 }, 
					{ 0, 1 } 
			};
			
			List<float[][]> listaKernels = Arrays.asList(kernel, kernel1, kernel2);
			
			// tamanho do pooling quadrado (nesse caso, 2x2)
			int poolSize = 2;
			
			float resultadoFinal = feedfoward.feedfoward(imageMatriz, listaKernels, poolSize, poolSize);
			
			System.out.println("Resultado final: " + resultadoFinal);

			/* Convolução */
			/*
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
			*/
			/** POOLING **/
			/*
			// chama a função de pooling, passando como parâmetro:
			// tipo do pooling, matriz da imagem e o tamanho do pooling
			float[][] pooledMatrix;
			System.out.println("\n Pooling: \n");
			for (int x = 1; x < 4; x++) {
				pooledMatrix = poolingLayer.PoolingLayer(x, imageMatriz, poolSize);
				for (int i = 0; i < pooledMatrix.length; i++) {
					for (int j = 0; j < pooledMatrix[0].length; j++) {
						System.out.print(pooledMatrix[i][j] + " ");
					}
					System.out.println("\n");
				}
				System.out.println("------");
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
