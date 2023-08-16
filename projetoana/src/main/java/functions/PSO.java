package functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PSO {
	 public static void main(String[] args) {
		int numListasMatrizes = 5; //Número de listas de matrizes
	    int numMatrizesPorLista = 3; //Número de matrizes por lista
	    int tamMatrizes = 3; //Tamanho das matrizes (tamMatrizes x tamMatrizes)
	    
	    List<List<int[][]>> listaListaKernels = new ArrayList<List<int[][]>>();
	
	    Random random = new Random();
	    
	    for (int x = 0; x < numListasMatrizes; x++) {
	        List<int[][]> listaKernels = new ArrayList<>();
	        for (int j = 0; j < numMatrizesPorLista; j++) {
	            int[][] matrix = new int[tamMatrizes][tamMatrizes];
	            for (int k = 0; k < tamMatrizes; k++) {
	                for (int l = 0; l < tamMatrizes; l++) {
	                    matrix[k][l] = random.nextInt(26); //Número aleatório entre 0 e 25
	                }
	            }
	            listaKernels.add(matrix);
	        }
	        listaListaKernels.add(listaKernels);
	    }

	    for (int i = 0; i < listaListaKernels.size(); i++) {
	        List<int[][]> listaKernels = listaListaKernels.get(i);
	        for (int j = 0; j < listaKernels.size(); j++) {
	        	int[][] matrix = listaKernels.get(j);
	            for (int k = 0; k < tamMatrizes; k++) {
	                System.out.println(Arrays.toString(matrix[k]));
	            }
	            System.out.println("\n"); 
	        }
	        System.out.println("-------");
	    }
	 }
}