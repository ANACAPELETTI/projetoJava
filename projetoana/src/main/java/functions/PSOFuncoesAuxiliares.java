package functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import entity.PSOEntity;

public class PSOFuncoesAuxiliares {
	private int numListasMatrizes = 4; //Número de listas de matrizes
	private int numMatrizesPorLista = 10; //Número de matrizes por lista
	private int tamMatrizes = 4; //Tamanho das matrizes (tamMatrizes x tamMatrizes)
	
	public List<List<float[][]>> criaListaListaKernels () {
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
		List<List<float[][]>> listaListaKernels = new ArrayList<List<float[][]>>();

		for (int x = 0; x < numListasMatrizes; x++) {
			List<float[][]> listaKernels = new ArrayList<>();
			for (int j = 0; j < numMatrizesPorLista; j++) {
				float[][] matrix = new float[tamMatrizes][tamMatrizes];
				for (int k = 0; k < tamMatrizes; k++) {
					for (int l = 0; l < tamMatrizes; l++) {
						matrix[k][l] = (float) 0.00001;
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
	
	public void atualiza (List<PSOEntity> listaPsoEntity, List<Float> erros, float omega) {
		//System.out.println("Kernels atualiza: "+listaPsoEntity.get(0).getListaListaKernels().size());
		System.out.println("Melhor global: "+erros.indexOf(Collections.min(erros)));
		for (int p = 0; p < listaPsoEntity.size(); p++) { //partículas
			List<List<float[][]>> novaParticula = new ArrayList<List<float[][]>>();
			List<List<float[][]>> novaVelocidade = new ArrayList<List<float[][]>>();
			PSOEntity psoEntity = listaPsoEntity.get(p);
			for (int i = 0; i < psoEntity.getListaListaKernels().size(); i++) {
				List<float[][]> listaKernels = new ArrayList<float[][]>();
				List<float[][]> listaVelocidades = new ArrayList<float[][]>();
				for (int k = 0; k < psoEntity.getListaListaKernels().get(i).size(); k++) {
					float[][] matrizKernel = psoEntity.getListaListaKernels().get(i).get(k);
					float[][] matrizVelocidade = psoEntity.getVelocidade().get(i).get(k);
					float[][] matrizKernel2 =  new float[matrizKernel.length][matrizKernel[0].length];
					float[][] matrizVelocidade2 = new float[matrizVelocidade.length][matrizVelocidade[0].length];
					matrizVelocidade2 = atualizaVelocidade(matrizVelocidade, listaPsoEntity.get(erros.indexOf(Collections.min(erros))).getListaListaKernels().get(i).get(k), psoEntity.getMelhorLocal().get(i).get(k), matrizKernel, omega);
					matrizKernel2 = atualizaParticula(matrizKernel, matrizVelocidade2, psoEntity.isMelhorGlobal());

					listaKernels.add(matrizKernel2);
					listaVelocidades.add(matrizVelocidade2);
				}
				novaParticula.add(listaKernels);
				novaVelocidade.add(listaVelocidades);
			}
			psoEntity.setVelocidade(novaVelocidade);
			psoEntity.setListaListaKernels(novaParticula);
		}
	}
	
	public float[][] atualizaParticula (float[][] particula, float[][] velocidade, boolean melhor) {
		int m = particula.length;
		int n = particula[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(melhor) {
					particula[i][j] = particula[i][j];
				} else {
					particula[i][j] = particula[i][j] + velocidade[i][j];
				}
			}
		}
		return particula;
	}
	
	public float[][] atualizaVelocidade (float[][] velocidade, float[][] melhorGlobal, float[][] melhorLocal, float[][] kernel, float omega) {
		int m = velocidade.length;
		int n = velocidade[0].length;
		float pesoLocal = (float) 0.5;
		float pesoGlobal = (float) 2.0;
		Random random = new Random();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				velocidade[i][j] = velocidade[i][j] * omega + pesoGlobal * random.nextFloat() * (melhorGlobal[i][j] - kernel[i][j]) + pesoLocal * random.nextFloat() * (melhorLocal[i][j] - kernel[i][j]);
			}
		}
		return velocidade;
	}
}
