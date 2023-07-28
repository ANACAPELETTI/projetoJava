package Feedfoward;

import java.util.List;

import layers.ConvolutionalLayer;
import layers.PoolingLayer;

public class Feedfoward {
	PoolingLayer poolingLayer = new PoolingLayer();
	ConvolutionalLayer convolutionalLayer = new ConvolutionalLayer();
	
	public float feedfoward(float tamanhoMatrizEntrada[][], List<float[][]> kernelsConvolutional, int poolSize1, int poolSize2) {
		
		List<float[][]> convolutionalMatrix = convolutionalLayer.ConvolutionalLayer(tamanhoMatrizEntrada, kernelsConvolutional);
		List<float[][]> convolutionalMatrix2;
		float[][] pooledMatrix, pooledMatrix2;
		float sum = 0;

		for (int x = 0; x < convolutionalMatrix.size(); x++) {
			pooledMatrix = poolingLayer.PoolingLayer(1, convolutionalMatrix.get(x), poolSize1); //pega cada uma das matrizes de retorno da convolutionalMatrix, retorna uma nova matriz com o pooling mínimo
			convolutionalMatrix2 = convolutionalLayer.ConvolutionalLayer(pooledMatrix, kernelsConvolutional);
			for (int z = 0; z < convolutionalMatrix2.size(); z++) {
				pooledMatrix2 = poolingLayer.PoolingLayer(3, convolutionalMatrix2.get(x), poolSize1); //pega cada uma das matrizes de retorno da convolutionalMatrix2, retorna uma nova matriz com o pooling máximo
				for (int i = 0; i < pooledMatrix2.length; i++) {
					for (int j = 0; j < pooledMatrix2[0].length; j++) {
						sum += pooledMatrix2[i][j];
					}
				}
			}
		}
		return sum;
	}
}
