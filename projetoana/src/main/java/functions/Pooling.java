package functions;

public class Pooling {
	//Função do teste
    public float[][] teste(float tamanhoMatrizEntrada[][], int tamanhoPooling) {
    	subMatrix subMat = new subMatrix();
    	//calcula o tamanho da matriz resultante após o min-pooling
        int pooledRows = (tamanhoMatrizEntrada.length - tamanhoPooling) + 1;
        int pooledCols = (tamanhoMatrizEntrada[0].length - tamanhoPooling) + 1;
        
        float[][] pooledMatrix = new float[pooledRows][pooledCols]; //inicializa a matriz resultante
        float[][] submatriz = new float[pooledRows][pooledCols]; //cria a matriz para a submatriz
        
       	for (int i = 0; i < pooledRows; i++) {
       		for (int j = 0; j < pooledCols; j++) {
       			submatriz = subMat.subMatrix(tamanhoMatrizEntrada, tamanhoPooling, i, j); //retorna todas as submatrizes
       			float maxVal = -1;
                for (int x = 0; x < tamanhoPooling; x++) {
                    for (int y = 0; y < tamanhoPooling; y++) {
                    	 if (submatriz[x][y] > maxVal) {
                             maxVal = submatriz[x][y];
                         }
                    }
                }
                pooledMatrix[i][j] = maxVal; //atribui o valor máximo à matriz resultante
           	}
       	}
        return pooledMatrix;
    }
	
	//Função do pooling mínimo
    public float[][] poolingMin(float tamanhoMatrizEntrada[][], int tamanhoPooling) {
    	subMatrix subMat = new subMatrix();
    	//calcula o tamanho da matriz resultante após o min-pooling
        int pooledRows = (tamanhoMatrizEntrada.length - tamanhoPooling) + 1;
        int pooledCols = (tamanhoMatrizEntrada[0].length - tamanhoPooling) + 1;
        
        float[][] pooledMatrix = new float[pooledRows][pooledCols]; //inicializa a matriz resultante
        
        float[][] submatriz = new float[pooledRows][pooledCols]; //cria a matriz para a submatriz
        
       	for (int i = 0; i < pooledRows; i++) {
       		for (int j = 0; j < pooledCols; j++) {
       			submatriz = subMat.subMatrix(tamanhoMatrizEntrada, tamanhoPooling, i, j); //retorna todas as submatrizes
       			float minVal = Float.MAX_VALUE;
                for (int x = 0; x < tamanhoPooling; x++) {
                    for (int y = 0; y < tamanhoPooling; y++) {
                        if (submatriz[x][y] < minVal) {
                            minVal = submatriz[x][y];
                        }
                    }
                }
                pooledMatrix[i][j] = minVal; //atribui o valor mínimo à matriz resultante
           	}
       	}
        return pooledMatrix;
    }
    
    //Função do pooling médio
    public float[][] poolingMed(float tamanhoMatrizEntrada[][], int tamanhoPooling) {
    	subMatrix subMat = new subMatrix();
    	//calcula o tamanho da matriz resultante após o min-pooling
        int pooledRows = (tamanhoMatrizEntrada.length - tamanhoPooling) + 1;
        int pooledCols = (tamanhoMatrizEntrada[0].length - tamanhoPooling) + 1;
        
        float[][] pooledMatrix = new float[pooledRows][pooledCols]; //inicializa a matriz resultante
        
        float[][] submatriz = new float[pooledRows][pooledCols]; //cria a matriz para a submatriz
        
       	for (int i = 0; i < pooledRows; i++) {
       		for (int j = 0; j < pooledCols; j++) {
       			submatriz = subMat.subMatrix(tamanhoMatrizEntrada, tamanhoPooling, i, j); //retorna todas as submatrizes
       			float sum = 0;
                for (int x = 0; x < tamanhoPooling; x++) {
                    for (int y = 0; y < tamanhoPooling; y++) {
                    	sum += submatriz[x][y];
                    }
                }
                float average = sum / (tamanhoPooling * tamanhoPooling);
                pooledMatrix[i][j] = average;  //atribui o valor médio à matriz resultante
           	}
       	}
        return pooledMatrix;
    }
    
    //Função do pooling máximo
    public float[][] poolingMax(float tamanhoMatrizEntrada[][], int tamanhoPooling) {
    	subMatrix subMat = new subMatrix();
    	//calcula o tamanho da matriz resultante após o min-pooling
        int pooledRows = (tamanhoMatrizEntrada.length - tamanhoPooling) + 1;
        int pooledCols = (tamanhoMatrizEntrada[0].length - tamanhoPooling) + 1;
        
        float[][] pooledMatrix = new float[pooledRows][pooledCols]; //inicializa a matriz resultante
        
        float[][] submatriz = new float[pooledRows][pooledCols]; //cria a matriz para a submatriz
        
       	for (int i = 0; i < pooledRows; i++) {
       		for (int j = 0; j < pooledCols; j++) {
       			submatriz = subMat.subMatrix(tamanhoMatrizEntrada, tamanhoPooling, i, j); //retorna todas as submatrizes
       			float maxVal = Float.MIN_VALUE;
                for (int x = 0; x < tamanhoPooling; x++) {
                    for (int y = 0; y < tamanhoPooling; y++) {
                    	 if (submatriz[x][y] > maxVal) {
                             maxVal = submatriz[x][y];
                         }
                    }
                }
                pooledMatrix[i][j] = maxVal; //atribui o valor máximo à matriz resultante
           	}
       	}
        return pooledMatrix;
	}
}