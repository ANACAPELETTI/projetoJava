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
		//System.out.println("Kernels atualiza: "+listaPsoEntity.get(0).getListaListaKernels().size());
		System.out.println("Melhor global: "+indiceMelhorGlobal);
	
		for (int p = 0; p < listaPsoEntity.size(); p++) { //partículas
			List<List<float[][]>> novaParticula = new ArrayList<List<float[][]>>();
			List<List<float[][]>> novaVelocidade = new ArrayList<List<float[][]>>();
			PSOEntity psoEntity = listaPsoEntity.get(p);
			//System.out.println("Kernels número: "+psoEntity.getListaListaKernels().size());
			for (int i = 0; i < psoEntity.getListaListaKernels().size(); i++) {
				List<float[][]> listaKernels = new ArrayList<float[][]>();
				List<float[][]> listaVelocidades = new ArrayList<float[][]>();
				for (int k = 0; k < psoEntity.getListaListaKernels().size(); k++) {
					float[][] matrizKernel = psoEntity.getListaListaKernels().get(i).get(k);
					if(p == indiceMelhorGlobal && i < 2 && k < 2) {
						System.out.println(" \n" + matrizKernel[i][k]);
					}
					float[][] matrizVelocidade = psoEntity.getVelocidade().get(i).get(k);
					float[][] matrizKernel2 =  new float[matrizKernel.length][matrizKernel[0].length];
					float[][] matrizVelocidade2 = new float[matrizVelocidade.length][matrizVelocidade[0].length];
					matrizVelocidade2 = atualizaVelocidade(matrizVelocidade, listaPsoEntity.get(indiceMelhorGlobal).getListaListaKernels().get(i).get(k), matrizKernel);
					matrizKernel2 = atualizaParticula(matrizKernel, matrizVelocidade2);
					if(p == indiceMelhorGlobal && i < 2 && k < 2) {
						System.out.println(" \n" + matrizKernel[i][k]);
						System.out.println(" " + matrizKernel2[i][k]);
					}
					listaKernels.add(matrizKernel2);
					listaVelocidades.add(matrizVelocidade2);
				}
				novaParticula.add(listaKernels);
				novaVelocidade.add(listaVelocidades);
			}
			psoEntity.setVelocidade(novaVelocidade);
			psoEntity.setListaListaKernels(novaParticula);
		}
		//System.out.println("Kernels atualizado: "+listaPsoEntity.get(0).getListaListaKernels().size());
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
	
	public float[][] atualizaVelocidade (float[][] velocidade, float[][] melhorGlobal, float[][] kernel) {
		int m = velocidade.length;
		int n = velocidade[0].length;
		Random random = new Random();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				velocidade[i][j] = random.nextFloat() * (melhorGlobal[i][j] - kernel[i][j]);
			}
		}
		return velocidade;
	}
}
