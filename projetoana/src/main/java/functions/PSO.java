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
	FeedfowardEntity feedfowardEntity = new FeedfowardEntity();
	Alerts alert = new Alerts();
	PSOFuncoesAuxiliares psoFuncoesAuxiliares = new PSOFuncoesAuxiliares();
	IniciarPSO iniciarPso = new IniciarPSO();
	AtualizaPSO atualizaPso = new AtualizaPSO();
	
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
			
			List<int[]> listaPoolings = new ArrayList<int[]>(Arrays.asList(pooling1, pooling1, pooling1, pooling1));
			feedfowardEntity.setListaPoolings(listaPoolings);
			
			List<Integer> listaOrdemOperacoes = new ArrayList<Integer>(Arrays.asList(0, 1, 0, 1, 0, 1, 0, 1, 0));

			char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
			
			float[][] imageMatriz;
			List<float[][]> listImageMatriz = new ArrayList<float[][]>();
			List<Character> listaLetrasCorretas = new ArrayList<Character>();
			
			for (int j = 0; j < 3; j++) {
				imageMatriz = imageReader.imageReader("/images/"+alphabet[0]+(j+1)+".png");
				listImageMatriz.add(imageMatriz);
				listaLetrasCorretas.add(alphabet[0]);
			}
			
			imageMatriz = imageReader.imageReader("/images/B1.png");
			listImageMatriz.add(imageMatriz);
			listaLetrasCorretas.add('B');
			
			feedfowardEntity.setLetraCorreta(listaLetrasCorretas);
			
			int numeroParticulas = 3;
			
			List<PSOEntity> listaPsoEntity = iniciarPso.inicializaPSO(listaOrdemOperacoes, listImageMatriz, feedfowardEntity, numeroParticulas);
			int indiceMelhorGlobal = 0;
			
			for (int i = 0; i < listaPsoEntity.size(); i++) {
				if(listaPsoEntity.get(i).isMelhorGlobal()) {
					indiceMelhorGlobal = i;
				}
			}
			
			psoFuncoesAuxiliares.atualiza(listaPsoEntity, indiceMelhorGlobal);
			atualizaPso.atualizaPSO(listaOrdemOperacoes, listImageMatriz, feedfowardEntity, listaPsoEntity, indiceMelhorGlobal);
			
			for (int i = 0; i < listaPsoEntity.size(); i++) {
				if(listaPsoEntity.get(i).isMelhorGlobal()) {
					indiceMelhorGlobal = i;
				}
			}
			
			psoFuncoesAuxiliares.atualiza(listaPsoEntity, indiceMelhorGlobal);
			atualizaPso.atualizaPSO(listaOrdemOperacoes, listImageMatriz, feedfowardEntity, listaPsoEntity, indiceMelhorGlobal);
			
			for (int i = 0; i < listaPsoEntity.size(); i++) {
				if(listaPsoEntity.get(i).isMelhorGlobal()) {
					indiceMelhorGlobal = i;
				}
			}
			
			psoFuncoesAuxiliares.atualiza(listaPsoEntity, indiceMelhorGlobal);
			atualizaPso.atualizaPSO(listaOrdemOperacoes, listImageMatriz, feedfowardEntity, listaPsoEntity, indiceMelhorGlobal);
			
			for (int i = 0; i < listaPsoEntity.size(); i++) {
				if(listaPsoEntity.get(i).isMelhorGlobal()) {
					indiceMelhorGlobal = i;
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

}
