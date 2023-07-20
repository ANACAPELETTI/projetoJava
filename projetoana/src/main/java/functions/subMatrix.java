package functions;

public class subMatrix {
    public float[][] subMatrix(float tamanhoMatrizEntrada[][], int tamanho, int linhaInicio, int colunaInicio) {
        //inicializa a matriz resultante
    	int rows = tamanhoMatrizEntrada.length;
        int cols = tamanhoMatrizEntrada[0].length;

        float[][] submatrices = new float[tamanho][tamanho];

        for (int i = linhaInicio; i <= rows - tamanho; i++) {
            for (int j = colunaInicio; j <= cols - tamanho; j++) {
                for (int x = 0; x < tamanho; x++) {
                    for (int y = 0; y < tamanho; y++) {
                        submatrices[x][y] = tamanhoMatrizEntrada[i + x][j + y];
                    }
                }
                return submatrices;
            }
        }
        return submatrices;
    }
}
