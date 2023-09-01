package feedfoward;

import java.util.ArrayList;
import java.util.List;
import layers.ConvolutionalLayer;
import layers.PoolingLayer;
import util.Alerts;

public class Feedfoward {
	PoolingLayer poolingLayer = new PoolingLayer();
	ConvolutionalLayer convolutionalLayer = new ConvolutionalLayer();
	
	public float feedfoward(List<Integer> ordem, List<float[][]> matrizEntrada, 
			List<List<float[][]>> kernelsConvolutional, List<int[]> listPoolings) {
		float sum = 0;
		List<List<float[][]>> listFeedfoward = new ArrayList<List<float[][]>>();
		
		listFeedfoward.add(matrizEntrada);
		for (int w = 0; w < ordem.size(); w++) {
			if(ordem.get(w) == 0) { //Convolução
				listFeedfoward.add(convolutionalLayer.ConvolutionalLayer(listFeedfoward.get(listFeedfoward.size()-1), kernelsConvolutional.get(0)));
				if (!kernelsConvolutional.isEmpty() && kernelsConvolutional.size() > 0) {
					kernelsConvolutional.remove(0);
		        }
			} else if (ordem.get(w) == 1) { //Pooling
				listFeedfoward.add(poolingLayer.PoolingLayer(listPoolings.get(0)[0], listFeedfoward.get(listFeedfoward.size()-1), listPoolings.get(0)[1]));
				removeFirst(listPoolings);	
			} else { 
				System.out.println("Algo errado");
			}
		}
		for (int w = 0; w < listFeedfoward.get(listFeedfoward.size()-1).size(); w++) { //Está indo até o tamanho da última lista de matrizes no listfeed...
			sum += listFeedfoward.get(listFeedfoward.size()-1).get(w)[0][0];
		}
		return sum;
	}
	
	public void removeFirst(List<int[]> listPoolings) {
        if (!listPoolings.isEmpty() && listPoolings.size() > 0) {
        	listPoolings.remove(0);
        }
    }
	
	public boolean verificaTamanho (List<Integer> ordem, List<float[][]> matrizEntrada, 
			List<List<float[][]>> kernelsConvolutional, List<int[]> listPoolings) {
		int tamanhoEntrada = matrizEntrada.get(0).length;
		System.out.println("Tamanho enrtada: "+ matrizEntrada.size());
		System.out.println("Tamanho ordem: "+ ordem.size());
		System.out.println("Tamanho kernel: " + kernelsConvolutional.size());
		System.out.println("Tamanho pooling: "+listPoolings.size());
		int convolucao = 0, pooling = 0;
		for (int w = 0; w < ordem.size(); w++) {
			if(ordem.get(w) == 0) { //Convolução
				try {
					tamanhoEntrada = tamanhoEntrada - kernelsConvolutional.get(convolucao).get(0).length + 1; 
					convolucao++;
				}catch (Exception e) {
					System.out.println("Erro convolução");
					return false;
				}
			} else if (ordem.get(w) == 1) { //Pooling
				try {
					tamanhoEntrada = tamanhoEntrada - listPoolings.get(pooling).length + 1; 
					pooling++;
				}catch (Exception e) {
					System.out.println("Erro pooling");
					return false;
				}
			} else { 
				Alerts alert = new Alerts();
				alert.showAlert("Algo deu errado", null, null, null);
			}
		}
		return true;
	}
}
