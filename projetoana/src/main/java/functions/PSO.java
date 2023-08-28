package functions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import entity.PSOEntity;
import entity.FeedfowardEntity;
import feedfoward.Feedfoward;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import layers.ConvolutionalLayer;
import layers.PoolingLayer;
import util.Alerts;

public class PSO extends Application {
	ImageReader imageReader = new ImageReader();
	PoolingLayer poolingLayer = new PoolingLayer();
	ConvolutionalLayer convolutionalLayer = new ConvolutionalLayer();
	private static Scene mainScene;
	subMatrix subMat = new subMatrix();
	Feedfoward feedfoward = new Feedfoward();
	Classificador classificador = new Classificador();
	Error erro = new Error();
	FeedfowardEntity poolingEntity = new FeedfowardEntity();
	Alerts alert = new Alerts();
	
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

			int[] pooling1 = {1, 2}; //pooling mínimo, com tamanho 2
			int[] pooling2 = {2, 2}; //pooling médio, com tamanho 2
			int[] pooling3 = {3, 2}; //pooling máximo, com tamanho 2
			
			List<int[]> listaPoolings = new ArrayList<int[]>(Arrays.asList(pooling1, pooling2, pooling1, pooling3));
			poolingEntity.setListaPoolings(listaPoolings);
			
			List<Integer> listaOrdemOperacoes = new ArrayList<Integer>(Arrays.asList(0, 1, 0, 1, 0, 1, 0, 1, 0));

			char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
			
			float[][] imageMatriz;
			List<float[][]> listImageMatriz = new ArrayList<float[][]>();
			List<Character> listaLetrasCorretas = new ArrayList<Character>();
			FeedfowardEntity feedfowardEntity = new FeedfowardEntity();
			
			for (int j = 0; j < 9; j++) {
				imageMatriz = imageReader.imageReader("/images/"+alphabet[0]+(j+1)+".png");
				listImageMatriz.add(imageMatriz);
				listaLetrasCorretas.add(alphabet[0]);
			}
			
			imageMatriz = imageReader.imageReader("/images/B1.png");
			listImageMatriz.add(imageMatriz);
			listaLetrasCorretas.add('B');
			
			feedfowardEntity.setLetraCorreta(listaLetrasCorretas);
			
			List<PSOEntity> listaPsoEntity = new ArrayList<PSOEntity>();
			
			for (int x = 0; x < 2; x++) {
				List<List<float[][]>> listaListaKernels = criaListaListaKernels();
				PSOEntity psoEntity = new PSOEntity();
				psoEntity.setListaListaKernels(listaListaKernels);
				listaPsoEntity.add(psoEntity);
				for (int j = 0; j < 10; j++) {
					float[][] imageMatriz2 = listImageMatriz.get(j);
					List<float[][]> listImageMatriz2 = new ArrayList<float[][]>();
					listImageMatriz2.add(imageMatriz2);
					int tipoDeClassificador = 0;
					if (feedfoward.verificaTamanho(listaOrdemOperacoes, listImageMatriz2, psoEntity.getListaListaKernels(), poolingEntity.getListaPoolings()) == true) {
						float resultadoFinal = feedfoward.feedfoward(listaOrdemOperacoes, listImageMatriz2, psoEntity.getListaListaKernels(), poolingEntity.getListaPoolings());
						System.out.println("Resultado final: " + resultadoFinal);
						char letraClassificada = classificador.classifica(resultadoFinal, tipoDeClassificador);
						System.out.println("Letra classificada: " + letraClassificada);
						char letraCerta = feedfowardEntity.getLetraCorreta().get(j);
						System.out.println("Letra certa: " + letraCerta);
						System.out.println("Classificação certa? "+ erro.erro(letraClassificada, letraCerta));
					} else {
						alert.showAlert("Não é possível realizar as operações", null, null, AlertType.ERROR);
					}
				}
			}
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
