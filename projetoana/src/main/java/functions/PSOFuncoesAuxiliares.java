package functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import entity.PSOEntity;

public class PSOFuncoesAuxiliares {
	public List<List<float[][]>> criaListaListaKernels () {
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
						matrix[k][l] = (random.nextFloat() * 2 - 1)/26; //Número aleatório
					}
				}
				listaKernels.add(matrix);
			}
			listaListaKernels.add(listaKernels);
		}
		return listaListaKernels;
	}
	
	public List<List<float[][]>> criaVelocidade0 () {
		int numListasMatrizes = 5; //Número de listas de matrizes
		int numMatrizesPorLista = 10; //Número de matrizes por lista
		int tamMatrizes = 10; //Tamanho das matrizes (tamMatrizes x tamMatrizes)

		List<List<float[][]>> listaListaKernels = new ArrayList<List<float[][]>>();

		for (int x = 0; x < numListasMatrizes; x++) {
			List<float[][]> listaKernels = new ArrayList<>();
			for (int j = 0; j < numMatrizesPorLista; j++) {
				float[][] matrix = new float[tamMatrizes][tamMatrizes];
				for (int k = 0; k < tamMatrizes; k++) {
					for (int l = 0; l < tamMatrizes; l++) {
						matrix[k][l] = (float) 0;
					}
				}
				listaKernels.add(matrix);
			}
			listaListaKernels.add(listaKernels);
		}
		return listaListaKernels;
	}
	
	public void imprimeListaListaKernels (List<List<float[][]>> listaListaKernels) {
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
	
	public void atualiza (List<PSOEntity> listaPsoEntity, int indiceMelhorGlobal) {
		List<List<float[][]>> novaParticula = new ArrayList<List<float[][]>>();
		List<List<float[][]>> novaVelocidade = new ArrayList<List<float[][]>>();
	
		for (int p = 0; p < listaPsoEntity.size(); p++) { //partículas
			PSOEntity psoEntity = listaPsoEntity.get(p);
			for (int i = 0; i < psoEntity.getListaListaKernels().size(); i++) {
				List<float[][]> listaKernels = new ArrayList<float[][]>();
				List<float[][]> listaVelocidades = new ArrayList<float[][]>();
				for (int k = 0; k < psoEntity.getListaListaKernels().get(i).size(); k++) {
					float[][] matrizKernel = psoEntity.getListaListaKernels().get(i).get(k);
					float[][] matrizVelocidade = psoEntity.getVelocidade().get(i).get(k);
					matrizVelocidade = atualizaVelocidade(matrizVelocidade, listaPsoEntity.get(indiceMelhorGlobal).getListaListaKernels().get(i).get(k));
					matrizKernel = atualizaParticula(matrizKernel, matrizVelocidade);
					listaKernels.add(matrizKernel);
					listaVelocidades.add(matrizVelocidade);
				}
				novaParticula.add(listaKernels);
				novaVelocidade.add(listaVelocidades);
			}
			psoEntity.setVelocidade(novaVelocidade);
			psoEntity.setListaListaKernels(novaParticula);
		}
	}
	
	public float[][] atualizaParticula (float[][] particula, float[][] velocidade) {
		int m = particula.length;
		int n = particula[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				particula[i][j] = particula[i][j] + velocidade[i][j];
			}
		}
		return particula;
	}
	
	public float[][] atualizaVelocidade (float[][] velocidade, float[][] melhorGlobal) {
		int m = velocidade.length;
		int n = velocidade[0].length;
		Random random = new Random();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				velocidade[i][j] = velocidade[i][j] + random.nextFloat() * (melhorGlobal[i][j] - velocidade[i][j]);
			}
		}
		return velocidade;
	}
}
