package layers;

import java.util.ArrayList;
import java.util.List;

import functions.Convolutional;

public class ConvolutionalLayer {
	Convolutional convolutional = new Convolutional();
	public List<float[][]> ConvolutionalLayer(List<float[][]> tamanhoMatrizEntrada, List<float[][]> kernelConvolutional) {
		List<float[][]> convolutionalMatrix = new ArrayList<float[][]>();
		tamanhoMatrizEntrada.forEach(b -> {
			kernelConvolutional.forEach(a -> convolutionalMatrix.add(convolutional.desfoque(b, a)));
		});
		return convolutionalMatrix;
	}
}
