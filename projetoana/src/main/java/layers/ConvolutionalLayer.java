package layers;

import java.util.List;

import functions.Convolutional;

public class ConvolutionalLayer {
	Convolutional convolutional = new Convolutional();
	List<float[][]> convolutionalMatrix;
	public List<float[][]> ConvolutionalLayer(float tamanhoMatrizEntrada[][], List<float[][]> kernelConvolutional) {
		System.out.println("\nA\n");
		kernelConvolutional.forEach(a -> convolutionalMatrix.add(convolutional.desfoque(tamanhoMatrizEntrada, a)));
		
		System.out.println("\nB\n");
		/*
		 * Tipo Pooling = 
		 * 1 = Pooling mínimo
		 * 2 = Pooling médio
		 * 3 = Pooling máximo 
		 * 4 = Teste da classe subMatrix
		 */
		/*switch (typeConvolutional) {
			case 1:
				convolutionalMatrix = convolutional.teste(tamanhoMatrizEntrada, kernelConvolutional);
				return convolutionalMatrix;
			case 2:
				convolutionalMatrix = convolutional.desfoque(tamanhoMatrizEntrada, kernelConvolutional);
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
		*/
		return convolutionalMatrix;
	}
}
