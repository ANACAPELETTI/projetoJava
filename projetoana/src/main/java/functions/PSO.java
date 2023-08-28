package functions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import entity.PSOEntity;
import entity.PoolingEntity;
import feedfoward.Feedfoward;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import layers.ConvolutionalLayer;
import layers.PoolingLayer;

public class PSO extends Application {
	ImageReader imageReader = new ImageReader();
	PoolingLayer poolingLayer = new PoolingLayer();
	ConvolutionalLayer convolutionalLayer = new ConvolutionalLayer();
	private static Scene mainScene;
	subMatrix subMat = new subMatrix();
	Feedfoward feedfoward = new Feedfoward();
	Classificador classificador = new Classificador();
	Error erro = new Error();
	PoolingEntity poolingEntity = new PoolingEntity();
	PSOEntity psoEntity = new PSOEntity();
	
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
			//float[][] imageMatriz = imageReader.imageReader("/images/A1.png");

			//Exemplo de matriz 2D representando a imagem
			/*float[][] imageMatriz0 = {                     
					{ 210,  35, 101, 174,  51,  38,  92, 213,  40, 115 },
					{ 137,  86, 140,  28,  72,  31,  95, 123, 227, 173 },
					{ 201,  65, 164, 142, 180, 247, 139,  44, 198, 207 },
					{  14, 195,  19,  34,  87, 152, 240, 146, 238, 115 },
					{ 223,  91,  53, 176, 103,  29,  11,  66,  47, 244 },
					{  58,  71,  15,  58, 160,  40,  80,  31, 167,  29 },
					{ 135, 129,  53,  69,  91, 241, 120, 125, 173, 187 },
					{ 185,  93,  96,  80, 148, 144, 194, 250, 171, 159 },
					{ 119, 252,  14, 236, 110, 157, 103,  43, 133,  89 },
					{  16,  80, 207,  48, 146,  61,  15,  19, 238,  54 }
			};
			
			float[][] imageMatriz = new float[10][10];
			
			for (int x = 0; x < 10; x++) {
				for (int j = 0; j < 10; j++) {
					imageMatriz[x][j] = imageMatriz0[x][j]/255; 
				}
			}

			List<float[][]> listImageMatriz = new ArrayList<float[][]>();
			listImageMatriz.add(imageMatriz);*/
			
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
			
			/*List<float[][]> listaKernels = Arrays.asList(kernel, kernel1, kernel2);
			List<float[][]> listaKernels2 = Arrays.asList(kernel1, kernel2);
			
			List<List<float[][]>> listaListaKernels = new ArrayList<List<float[][]>>();
			listaListaKernels.add(listaKernels);
			listaListaKernels.add(listaKernels2);*/
			
			//List<List<float[][]>> listaListaKernels = criaListaListaKernels();
			
			//List<List<float[][]>> listaListaKernels2 = new ArrayList<List<float[][]>>();
			//listaListaKernels2.add(listaKernels);
			//listaListaKernels2.add(listaKernels2);
			
			int[] pooling1 = {1, 2}; //pooling mínimo, com tamanho 2
			int[] pooling2 = {2, 2}; //pooling médio, com tamanho 2
			int[] pooling3 = {3, 2}; //pooling máximo, com tamanho 2
			
			List<int[]> listaPoolings = new ArrayList<int[]>(Arrays.asList(pooling1, pooling3));
			poolingEntity.setListaPoolings(listaPoolings);
			
			List<Integer> listaOrdemOperacoes = new ArrayList<Integer>(Arrays.asList(0, 1, 0, 1, 0, 1, 0, 1, 0));
			
			/*int tipoDeClassificador = 0;
			
			if (feedfoward.verificaTamanho(listaOrdemOperacoes, listImageMatriz, listaListaKernels, poolingEntity.getListaPoolings()) == true) {
				float resultadoFinal = feedfoward.feedfoward(listaOrdemOperacoes, listImageMatriz, listaListaKernels, poolingEntity.getListaPoolings());
				System.out.println("Resultado final: " + resultadoFinal);
				char letraClassificada = classificador.classifica(resultadoFinal, tipoDeClassificador);
				System.out.println("Letra classificada: " + letraClassificada);
				char letraCerta = erro.getFirstLetterFromImageName("/projetoana/src/main/java/images/A1.png");
				System.out.println("Letra certa: " + letraCerta);
				System.out.println("Classificação certa? "+ erro.erro(letraClassificada, letraCerta));
			} else {
				System.out.println("\n\nNão é possível realizar as operações\n\n");
			}*/
			char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
			for (int x = 0; x < 10; x++) {
				List<List<float[][]>> listaListaKernels = criaListaListaKernels();
				psoEntity.setListaListaKernels(listaListaKernels);
				for (int j = 0; j < 10; j++) {
					float[][] imageMatriz = imageReader.imageReader("/images/"+alphabet[0]+(j+1)+".png");
					List<float[][]> listImageMatriz = new ArrayList<float[][]>();
					listImageMatriz.add(imageMatriz);
					int tipoDeClassificador = 0;
					System.out.println("Lista ordem: "+ listaOrdemOperacoes);
					if (feedfoward.verificaTamanho(listaOrdemOperacoes, listImageMatriz, psoEntity.getListaListaKernels(), poolingEntity.getListaPoolings()) == true) {
						float resultadoFinal = feedfoward.feedfoward(listaOrdemOperacoes, listImageMatriz, psoEntity.getListaListaKernels(), poolingEntity.getListaPoolings());
						System.out.println("Resultado final: " + resultadoFinal);
						char letraClassificada = classificador.classifica(resultadoFinal, tipoDeClassificador);
						System.out.println("Letra classificada: " + letraClassificada);
						char letraCerta = erro.getFirstLetterFromImageName("/projetoana/src/main/java/images/A1.png");
						System.out.println("Letra certa: " + letraCerta);
						System.out.println("Classificação certa? "+ erro.erro(letraClassificada, letraCerta));
					} else {
						System.out.println("\n\nNão é possível realizar as operações\n\n");
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//Criar a lista de lista de kernels
		//List<List<float[][]>> lista;
		//lista = criaListaListaKernels();
		//imprimeListaListaKernels(lista);
		launch(args);
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}

	public static List<List<float[][]>> criaListaListaKernels () {
		int numListasMatrizes = 5; //Número de listas de matrizes
		int numMatrizesPorLista = 10; //Número de matrizes por lista
		int tamMatrizes = 10; //Tamanho das matrizes (tamMatrizes x tamMatrizes)

		List<List<float[][]>> listaListaKernels = new ArrayList<List<float[][]>>();

		Random random = new Random();

		for (int x = 0; x < numListasMatrizes; x++) {
			List<float[][]> listaKernels = new ArrayList<>();
			for (int j = 0; j < numMatrizesPorLista; j++) {
				float[][] matrix = new float[tamMatrizes][tamMatrizes];
				for (int k = 0; k < tamMatrizes; k++) {
					for (int l = 0; l < tamMatrizes; l++) {
						matrix[k][l] = random.nextFloat() * 2 - 1; //Número aleatório
					}
				}
				listaKernels.add(matrix);
			}
			listaListaKernels.add(listaKernels);
		}
		return listaListaKernels;
	}
	
	public static void imprimeListaListaKernels (List<List<float[][]>> listaListaKernels) {
		for (int i = 0; i < listaListaKernels.size(); i++) {
			List<float[][]> listaKernels = listaListaKernels.get(i);
			for (int j = 0; j < listaKernels.size(); j++) {
				float[][] matrix = listaKernels.get(j);
				for (int k = 0; k < listaListaKernels.get(0).size(); k++) {
					System.out.println(Arrays.toString(matrix[k]));
				}
				System.out.println("\n");
			}
			System.out.println("-------");
		}
	}
}
