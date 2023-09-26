package layers;

import java.util.ArrayList;
import java.util.List;
import functions.Pooling;

public class PoolingLayer {
	Pooling pooling = new Pooling();
	public List<float[][]> PoolingLayer(int tipoPooling, List<float[][]> tamanhoMatrizEntrada, int tamanhoPooling) {
		/*
		 * Tipo Pooling = 
		 * 1 = Pooling mínimo
		 * 2 = Pooling médio
		 * 3 = Pooling máximo 
		 * 4 = Teste da classe subMatrix
		 */
		List<float[][]> pooledMatrix = new ArrayList<float[][]>();
		switch (tipoPooling) {
			case 1:
				tamanhoMatrizEntrada.forEach(a -> {
					pooledMatrix.add(pooling.poolingMin(a, tamanhoPooling));
				});
				return pooledMatrix;
			case 2:
				tamanhoMatrizEntrada.forEach(a -> {
					pooledMatrix.add(pooling.poolingMed(a, tamanhoPooling));
				});
				return pooledMatrix;
			case 3:
				tamanhoMatrizEntrada.forEach(a -> {
					pooledMatrix.add(pooling.poolingMax(a, tamanhoPooling));
				});
				return pooledMatrix;
			case 4:
				tamanhoMatrizEntrada.forEach(a -> {
					pooledMatrix.add(pooling.teste(a, tamanhoPooling));
				});
				return pooledMatrix;
			default:
				break;
			}
		return pooledMatrix;
	}
}
