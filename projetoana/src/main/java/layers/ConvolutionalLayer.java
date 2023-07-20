package layers;

import functions.Convolutional;

public class ConvolutionalLayer {
	Convolutional convolutional = new Convolutional();
	float[][] convolutionalMatrix;
	public float[][] ConvolutionalLayer(int typeConvolutional, float tamanhoMatrizEntrada[][], float kernelConvolutional[][]) {
		/*
		 * Tipo Pooling = 
		 * 1 = Pooling mínimo
		 * 2 = Pooling médio
		 * 3 = Pooling máximo 
		 * 4 = Teste da classe subMatrix
		 */
		switch (typeConvolutional) {
			case 1:
				convolutionalMatrix = convolutional.teste(tamanhoMatrizEntrada, kernelConvolutional);
				return convolutionalMatrix;
			case 2:
				convolutionalMatrix = convolutional.teste(tamanhoMatrizEntrada, kernelConvolutional);
				return convolutionalMatrix;
			case 3:
				convolutionalMatrix = convolutional.teste(tamanhoMatrizEntrada, kernelConvolutional);
				return convolutionalMatrix;
			case 4:
				convolutionalMatrix = convolutional.teste(tamanhoMatrizEntrada, kernelConvolutional);
				return convolutionalMatrix;
			default:
				break;
			}
		return convolutionalMatrix;
	}
}
