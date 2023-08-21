package Feedfoward;

import java.util.ArrayList;
import java.util.List;
import layers.ConvolutionalLayer;
import layers.PoolingLayer;

public class Feedfoward {
	PoolingLayer poolingLayer = new PoolingLayer();
	ConvolutionalLayer convolutionalLayer = new ConvolutionalLayer();
	
	public float feedfoward(List<Integer> ordem, List<float[][]> matrizEntrada, 
			List<List<float[][]>> kernelsConvolutional, List<int[]> listPoolings) {
		float sum = 0;
		List<List<float[][]>> listFeedfoward = new ArrayList<List<float[][]>>();
		
		listFeedfoward.add(matrizEntrada);
		for (int i = 0; i < matrizEntrada.get(0).length; i++) { 
			  for (int j  = 0; j < matrizEntrada.get(0).length; j++) {
				  System.out.print(matrizEntrada.get(0)[i][j] + " "); 
			  }
			  System.out.println("\n"); 
			}
		  System.out.println("-------");
		
		for (int w = 0; w < ordem.size(); w++) {
			if(ordem.get(w) == 0) { //convolução
				System.out.println("Convolução");
				listFeedfoward.add(convolutionalLayer.ConvolutionalLayer(listFeedfoward.get(listFeedfoward.size()-1), kernelsConvolutional.get(0)));
				if (!kernelsConvolutional.isEmpty() && kernelsConvolutional.size() > 0) {
					kernelsConvolutional.remove(0);
		        }
			} else if (ordem.get(w) == 1) { //pooling
				System.out.println("Pooling");
				listFeedfoward.add(poolingLayer.PoolingLayer(listPoolings.get(0)[0], listFeedfoward.get(listFeedfoward.size()-1), listPoolings.get(0)[1]));
				removeFirst(listPoolings);	
			} else { 
				System.out.println("Algo errado");
			}
			listFeedfoward.get(w+1).forEach(a -> {
				  for (int i = 0; i < a.length; i++) { 
					  for (int j  = 0; j < a[0].length; j++) {
						  System.out.print(a[i][j] + " "); 
					  }
					  System.out.println("\n"); 
					}
				  System.out.println("-------");
			  });
		}
		for (int w = 0; w < listFeedfoward.get(listFeedfoward.size()-1).size(); w++) { //está indo até o tamanho da última lista de matrizes no listfeed...
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
		int tamanhoInicial = 0, tamanhoFinal = 0;
		for (int w = 0; w < ordem.size(); w++) {
			if(ordem.get(w) == 0) { //convolução
				if (tamanhoFinal == 0) {
					tamanhoInicial = (matrizEntrada.get(0).length - kernelsConvolutional.get(0).get(0).length) + 1;
					tamanhoFinal = tamanhoInicial;
					if (!kernelsConvolutional.isEmpty() && kernelsConvolutional.size() > 0) {
						kernelsConvolutional.remove(0);
			        }
				} else {
					tamanhoFinal = (tamanhoInicial - kernelsConvolutional.get(0).get(0).length) + 1;
					if (!kernelsConvolutional.isEmpty() && kernelsConvolutional.size() > 0) {
						kernelsConvolutional.remove(0);
			        }
					tamanhoInicial = tamanhoFinal;
				}
			} else if (ordem.get(w) == 1) { //pooling
				if (tamanhoFinal == 0) {
					tamanhoInicial = (matrizEntrada.get(0).length - listPoolings.get(0).length) + 1;
					tamanhoFinal = tamanhoInicial;
					removeFirst(listPoolings);	
				} else {
					tamanhoFinal = (tamanhoInicial - listPoolings.get(0)[1]) + 1;
					removeFirst(listPoolings);	
					tamanhoInicial = tamanhoFinal;
				}
			} else { 
				System.out.println("Algo errado");
			}
		}
		System.out.println("Tamanho final mesmo: \n" + tamanhoFinal);
		if (tamanhoFinal > 0) {
			return true;
		} else {
			return false;
		}
	}
}
