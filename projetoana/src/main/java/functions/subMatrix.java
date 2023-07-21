package functions;

public class subMatrix {
	public float[][] subMatrix(float tamanhoMatrizEntrada[][], int tamanho, int linhaInicio, int colunaInicio) {
		// inicializa a matriz resultante
		int rows = tamanhoMatrizEntrada.length;
		int cols = tamanhoMatrizEntrada[0].length;

		float[][] submatrices = new float[tamanho][tamanho];

		for (int x = 0; x < tamanho; x++) {
			for (int y = 0; y < tamanho; y++) {
				submatrices[x][y] = tamanhoMatrizEntrada[linhaInicio + x][colunaInicio + y];
			}
		}
		return submatrices;
	}
}
