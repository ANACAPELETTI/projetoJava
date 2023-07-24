package functions;

import functions.subMatrix;

public class Convolutional {
	//Função do box blur/convolução de desfoque
    public float[][] teste(float tamanhoMatrizEntrada[][], float kernelConvolutional[][]) {
        int kernelSize = kernelConvolutional.length;
        int convolutionalRows = (tamanhoMatrizEntrada.length - kernelSize) + 1;
        int convolutionalCols = (tamanhoMatrizEntrada[0].length - kernelSize) + 1;
        
        float[][] convolutedMatrix = new float[convolutionalRows][convolutionalCols];

        for (int i = 0; i < convolutedMatrix.length; i++) {
            for (int j = 0; j < convolutedMatrix[0].length; j++) {
            	float sum = 0;
                for (int x = 0; x < kernelSize; x++) {
                    for (int y = 0; y < kernelSize; y++) {
                        sum += tamanhoMatrizEntrada[i + x][j + y] * kernelConvolutional[x][y];
                    }
                }
                convolutedMatrix[i][j] = sum;
            }
        }

        return convolutedMatrix;
    }
    
    public float[][] desfoque(float tamanhoMatrizEntrada[][], float kernelConvolutional[][]) {
    	subMatrix subMat = new subMatrix();
        int kernelSize = kernelConvolutional.length;
        int convolutionalRows = (tamanhoMatrizEntrada.length - kernelSize) + 1;
        int convolutionalCols = (tamanhoMatrizEntrada[0].length - kernelSize) + 1;
        
        float[][] convolutedMatrix = new float[convolutionalRows][convolutionalCols]; //inicializa a matriz resultante
        float[][] submatriz = new float[convolutionalRows][convolutionalCols]; //cria a matriz para a submatriz
        for (int i = 0; i < convolutedMatrix.length; i++) {
            for (int j = 0; j < convolutedMatrix[0].length; j++) { 	
            	submatriz = subMat.subMatrix(tamanhoMatrizEntrada, kernelSize, i, j); //retorna todas as submatrizes
            	float sum = 0;
                for (int x = 0; x < kernelSize; x++) {
                    for (int y = 0; y < kernelSize; y++) {
                        sum += submatriz[x][y] * kernelConvolutional[x][y];
                    }
                }
                convolutedMatrix[i][j] = sum;
            }
        }
        return convolutedMatrix;
    }
}