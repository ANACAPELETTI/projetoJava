package layers;

import java.util.ArrayList;
import java.util.List;

import functions.Convolutional;

public class ConvolutionalLayer {
	Convolutional convolutional = new Convolutional();
	public List<float[][]> ConvolutionalLayer(float tamanhoMatrizEntrada[][], List<float[][]> kernelConvolutional) {
		List<float[][]> convolutionalMatrix = new ArrayList<float[][]>();
		kernelConvolutional.forEach(a -> convolutionalMatrix.add(convolutional.desfoque(tamanhoMatrizEntrada, a)));
		return convolutionalMatrix;
	}
}
