package functions;

import java.io.File;
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

public class PSO {
	ImageReader imageReader = new ImageReader();
	PoolingLayer poolingLayer = new PoolingLayer();
	ConvolutionalLayer convolutionalLayer = new ConvolutionalLayer();
	private static Scene mainScene;
	subMatrix subMat = new subMatrix();
	Feedfoward feedfoward = new Feedfoward();
	Classificador classificador = new Classificador();
	List<Float> erros = new ArrayList<Float>();
	Error erro = new Error();
	FeedfowardEntity feedfowardEntity = new FeedfowardEntity();
	Alerts alert = new Alerts();
	PSOFuncoesAuxiliares psoFuncoesAuxiliares = new PSOFuncoesAuxiliares();
	IniciarPSO iniciarPso = new IniciarPSO();
	AtualizaPSO atualizaPso = new AtualizaPSO();
	
	public void pso() {
			int[] pooling1 = {1, 2}; //pooling mínimo, com tamanho 2
			int[] pooling2 = {2, 2}; //pooling médio, com tamanho 2
			int[] pooling3 = {3, 2}; //pooling máximo, com tamanho 2
			
			List<int[]> listaPoolings = new ArrayList<int[]>(Arrays.asList(pooling1, pooling1, pooling1, pooling1));
			feedfowardEntity.setListaPoolings(listaPoolings);
			
			List<Integer> listaOrdemOperacoes = new ArrayList<Integer>(Arrays.asList(0, 1, 0, 1, 0, 0));

			char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
			
			float[][] imageMatriz;
			List<float[][]> listImageMatriz = new ArrayList<float[][]>();
			List<Character> listaLetrasCorretas = new ArrayList<Character>();
			
			/*for (int j = 0; j < 3; j++) {
				imageMatriz = imageReader.imageReader("/images/"+alphabet[0]+(j)+".png");
				listImageMatriz.add(imageMatriz);
				listaLetrasCorretas.add(alphabet[0]);
			}
			
			imageMatriz = imageReader.imageReader("/images/B1.png");
			listImageMatriz.add(imageMatriz);
			listaLetrasCorretas.add('B');
			*/
			
			File diretorio = new File("src\\main\\java\\images\\");
			File[] arquivos = diretorio.listFiles();
	
			for (int j = 0; j < arquivos.length; j++) {
				//System.out.println(diretorio.getAbsolutePath() + "\\" + arquivos[j].getName());
				imageMatriz = imageReader.imageReader(diretorio.getAbsolutePath() + "\\" + arquivos[j].getName());
				listImageMatriz.add(imageMatriz);
				listaLetrasCorretas.add(arquivos[j].getName().charAt(0));
			}
			
			feedfowardEntity.setLetraCorreta(listaLetrasCorretas);
			
			int numeroParticulas = 20, numeroIteracoes = 20;
			
			List<PSOEntity> listaPsoEntity = iniciarPso.inicializaPSO(listaOrdemOperacoes, listImageMatriz, feedfowardEntity, numeroParticulas);
			float omegaI = (float) 0.9, omegaF = (float) 0.5;
			for (int iteracao = 0; iteracao < numeroIteracoes; iteracao++) {
				erros.clear();
				float omega = omegaI + (omegaF - omegaI) * iteracao/numeroIteracoes;
				for (int i = 0; i < listaPsoEntity.size(); i++) {
					erros.add(listaPsoEntity.get(i).getErro());
				}
				psoFuncoesAuxiliares.atualiza(listaPsoEntity, erros, omega);
				atualizaPso.atualizaPSO(listaOrdemOperacoes, listImageMatriz, feedfowardEntity, listaPsoEntity, erros);
			}
		System.exit(0); //remover depois
	}
}
