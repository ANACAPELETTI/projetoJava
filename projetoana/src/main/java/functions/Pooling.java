package functions;

import java.util.Iterator;
import java.util.List;
import functions.subMatrix;

public class Pooling {
	//Função do teste
    public float[][] teste(float tamanhoMatrizEntrada[][], int tamanhoPooling) {
    	subMatrix subMat = new subMatrix();
    	//calcula o tamanho da matriz resultante após o min-pooling
        int pooledRows = (tamanhoMatrizEntrada.length - tamanhoPooling) + 1;
        int pooledCols = (tamanhoMatrizEntrada[0].length - tamanhoPooling) + 1;

        //inicializa a matriz resultante
        float[][] pooledMatrix = new float[pooledRows][pooledCols];
        
        //cria a matriz para a submatriz
        float[][] submatriz = new float[pooledRows][pooledCols];
        
       	for (int i = 0; i < pooledRows; i++) {
       		for (int j = 0; j < pooledCols; j++) {
       			submatriz = subMat.subMatrix(tamanhoMatrizEntrada, tamanhoPooling, i, j); //submatriz
       			float minVal = 256;
                for (int x = 0; x < tamanhoPooling; x++) {
                    for (int y = 0; y < tamanhoPooling; y++) {
                        if (submatriz[x][y] < minVal) {
                            minVal = submatriz[x][y];
                        }
                    }
                }
                //atribui o valor mínimo à matriz resultante
                pooledMatrix[i][j] = minVal;
           	}
       	}
        return pooledMatrix;
    }
	
	//Função do pooling mínimo
    public float[][] poolingMin(float tamanhoMatrizEntrada[][], int tamanhoPooling) {
        int rows = tamanhoMatrizEntrada.length;  //linhas da matriz de entrada
        int cols = tamanhoMatrizEntrada[0].length;  //colunas da matriz de entrada

        //calcula o tamanho da matriz resultante após o min-pooling
        int pooledRows = (rows - tamanhoPooling) + 1;
        int pooledCols = (cols - tamanhoPooling) + 1;

        //inicializa a matriz resultante
        float[][] pooledMatrix = new float[pooledRows][pooledCols];

        //aplicando o min-pooling
        for (int i = 0; i < pooledRows; i++) {
            for (int j = 0; j < pooledCols; j++) {
            	//para auxiliar a encontrar o valor mínimo
                float minVal = 256;
                for (int x = i; x < i + tamanhoPooling; x++) {
                    for (int y = j; y < j + tamanhoPooling; y++) {
                        if (tamanhoMatrizEntrada[x][y] < minVal) {
                            minVal = tamanhoMatrizEntrada[x][y];
                        }
                    }
                }
                //atribui o valor mínimo à matriz resultante
                pooledMatrix[i][j] = minVal;
            }
        }
        return pooledMatrix;
    }
    
    //Função do pooling médio
    public float[][] poolingMed(float tamanhoMatrizEntrada[][], int tamanhoPooling) {
        int rows = tamanhoMatrizEntrada.length; //linhas da matriz de entrada
        int cols = tamanhoMatrizEntrada[0].length; //colunas da matriz de entrada

        //calcula o tamanho da matriz resultante após o average-pooling
        int pooledRows = (rows - tamanhoPooling) + 1;
        int pooledCols = (cols - tamanhoPooling) + 1;

        //inicializa a matriz resultante, arredondando as casas decimais para baixo
        float[][] pooledMatrix = new float[pooledRows][pooledCols];
        
        //aplicando o average-pooling
        for (int i = 0; i < pooledRows; i++) {
            for (int j = 0; j < pooledCols; j++) {
            	//para auxiliar a encontrar o valor médio
                float sum = 0;
                for (int x = i; x < i + tamanhoPooling; x++) {
                    for (int y = j; y < j + tamanhoPooling; y++) {
                        sum += tamanhoMatrizEntrada[x][y];
                        
                    }
                }
                float average = sum / (tamanhoPooling * tamanhoPooling);
                //atribui o valor médio à matriz resultante
                pooledMatrix[i][j] = average;
            }
        }
        return pooledMatrix;
    }
    
    //Função do pooling máximo
    public float[][] poolingMax(float tamanhoMatrizEntrada[][], int tamanhoPooling) {
		int rows = tamanhoMatrizEntrada.length; //linhas da matriz de entrada
        int cols = tamanhoMatrizEntrada[0].length; //colunas da matriz de entrada

        //calcula o tamanho da matriz resultante após o max-pooling
        int pooledRows = (rows - tamanhoPooling) + 1;
        int pooledCols = (cols - tamanhoPooling) + 1;

        //inicializa a matriz resultante
        float[][] pooledMatrix = new float[pooledRows][pooledCols];

        //aplicando o max-pooling
        for (int i = 0; i < pooledRows; i++) {
            for (int j = 0; j < pooledCols; j++) {
            	//para auxiliar a encontrar o valor máximo
                float maxVal = -1;
                for (int x = i; x < i + tamanhoPooling; x++) {
                    for (int y = j; y < j + tamanhoPooling; y++) {
                        if (tamanhoMatrizEntrada[x][y] > maxVal) {
                            maxVal = tamanhoMatrizEntrada[x][y];
                        }
                    }
                }
                //atribui o valor máximo à matriz resultante
                pooledMatrix[i][j] = maxVal;
            }
        }
        return pooledMatrix;
	}
}
