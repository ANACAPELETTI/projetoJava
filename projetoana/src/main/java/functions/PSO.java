package functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import entity.FeedfowardEntity;
import entity.PSOEntity;
import feedfoward.Feedfoward;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import layers.ConvolutionalLayer;
import layers.PoolingLayer;
import util.Alerts;

public class PSO {
	ImageReader imageReader = new ImageReader();
	PoolingLayer poolingLayer = new PoolingLayer();
	ConvolutionalLayer convolutionalLayer = new ConvolutionalLayer();
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
	Import importe = new Import();
	
	public void pso() {
			int[] pooling1 = {1, 2}; //pooling mínimo, com tamanho 2
			int[] pooling2 = {2, 2}; //pooling médio, com tamanho 2
			int[] pooling3 = {3, 2}; //pooling máximo, com tamanho 2

			try {
				importe.loadExcelFile(3, 2); //Tamanho dos kernels (3x3) e Quantidade de matrizes diferentes em cada planilha
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			List<int[]> listaPoolings = new ArrayList<int[]>(Arrays.asList(pooling1, pooling1, pooling1, pooling1));
			feedfowardEntity.setListaPoolings(listaPoolings);
			
			List<Integer> listaOrdemOperacoes = new ArrayList<Integer>(Arrays.asList(0, 1, 0, 1, 0, 0));

			float[][] imageMatriz;
			List<float[][]> listImageMatriz = new ArrayList<float[][]>();
			List<Character> listaLetrasCorretas = new ArrayList<Character>();
			
			File diretorio = new File("src\\main\\java\\images\\");
			File[] arquivos = diretorio.listFiles();
	
			for (int j = 0; j < arquivos.length; j++) {
				imageMatriz = imageReader.imageReader(diretorio.getAbsolutePath() + "\\" + arquivos[j].getName());
				listImageMatriz.add(imageMatriz);
				listaLetrasCorretas.add(arquivos[j].getName().charAt(0));
			}
			
			feedfowardEntity.setLetraCorreta(listaLetrasCorretas);
			
			int numeroParticulas = 20, numeroIteracoes = 20;
			
			
			
			
			List<List<float [][]>> teste = new ArrayList<List<float [][]>>();
			List<float [][]> listaTeste = new ArrayList<float [][]>();
			List<float [][]> listaTeste2 = new ArrayList<float [][]>();
			float [][] matriz1 = {
					{1, 2, -3},
					{1, -3, 2},
					{-2, 1, 3},
			};
			
			float [][] matriz2 = {
					{0, 0, 0, 0},
					{0, 0, 0, 0},
					{0, 0, 0, 0},
					{0, 0, 0, 0},
			};
			
			
			listaTeste.add(matriz1);
			listaTeste.add(matriz1);
			listaTeste2.add(matriz1);
			listaTeste2.add(matriz2);
			listaTeste2.add(matriz2);
			
			teste.add(listaTeste);
			teste.add(listaTeste2);
			teste.add(listaTeste);
			
			Export export = new Export();
			try {
				export.exportExcel(teste);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
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
