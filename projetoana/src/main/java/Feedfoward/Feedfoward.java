package Feedfoward;

import java.util.List;

import layers.ConvolutionalLayer;
import layers.PoolingLayer;

public class Feedfoward {
	PoolingLayer poolingLayer = new PoolingLayer();
	ConvolutionalLayer convolutionalLayer = new ConvolutionalLayer();
	
	public float feedfoward(List<Integer> ordem, List<float[][]> matrizEntrada, 
			List<float[][]> kernelsConvolutional, List<int[]> listPoolings) {
		List<float[][]> convolutionalMatrix = convolutionalLayer.ConvolutionalLayer(matrizEntrada.get(0), kernelsConvolutional);
		List<float[][]> convolutionalMatrix2;
		float[][] pooledMatrix, pooledMatrix2;
		float sum = 0;

		for (int x = 0; x < convolutionalMatrix.size(); x++) {
			pooledMatrix = poolingLayer.PoolingLayer(listPoolings.get(0)[0], //pega o tipo de pooling do primeiro pooling, nesse caso, pooling mínimo 
					convolutionalMatrix.get(x), //pega cada uma das matrizes de retorno da convolutionalMatrix, retorna uma nova matriz com o pooling mínimo
					listPoolings.get(0)[1]); //pegando o tamanho do pooling, no primeiro pooling da lista
			System.out.println("\n Pooling 1: \n");
			for (int i = 0; i < pooledMatrix.length; i++) {
				for (int j = 0; j < pooledMatrix[0].length; j++) {
					System.out.print(pooledMatrix[i][j] + " ");
				}
				System.out.println("\n");
			}
			System.out.println("------");
			convolutionalMatrix2 = convolutionalLayer.ConvolutionalLayer(pooledMatrix, kernelsConvolutional);

			System.out.println("\n Convolução 2: \n"); // Exibir a matriz resultante após max-pooling 
			  convolutionalMatrix2.forEach(a -> {
				  for (int i = 0; i < a.length; i++) { 
					  for (int j  = 0; j < a[0].length; j++) {
						  System.out.print(a[i][j] + " "); 
					  }
					  System.out.println("\n"); 
					}
				  System.out.println("-------");
			  });
			for (int z = 0; z < convolutionalMatrix2.size(); z++) {
				pooledMatrix2 = poolingLayer.PoolingLayer(listPoolings.get(1)[0], //pega o tipo de pooling do segundo pooling, nesse caso, pooling máximo  
						convolutionalMatrix2.get(x), //pega cada uma das matrizes de retorno da convolutionalMatrix2, retorna uma nova matriz com o pooling máximo
						listPoolings.get(1)[1]); //pegando o tamanho do pooling, do segundo pooling da lista
				
				System.out.println("\n Pooling 2: \n");
				for (int i = 0; i < pooledMatrix2.length; i++) {
					for (int j = 0; j < pooledMatrix2[0].length; j++) {
						System.out.print(pooledMatrix2[i][j] + " ");
					}
					System.out.println("\n");
				}
				System.out.println("------");
				
				//System.out.println("Sum: \n" + sum);
				for (int i = 0; i < pooledMatrix2.length; i++) {
					//System.out.println("Sum: \n" + sum);
					for (int j = 0; j < pooledMatrix2[0].length; j++) {
						sum += pooledMatrix2[i][j];
						System.out.println("Sum: \n" + sum);
					}
					 System.out.println("\n"); 
				}
				//System.out.println("-------");
			}
		}
		return sum;
	}
}
