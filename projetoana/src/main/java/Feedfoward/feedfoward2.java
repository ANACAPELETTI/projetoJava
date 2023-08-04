package Feedfoward;

import java.util.ArrayList;
import java.util.List;

import layers.ConvolutionalLayer;
import layers.PoolingLayer;

public class feedfoward2 {
	PoolingLayer poolingLayer = new PoolingLayer();
	ConvolutionalLayer convolutionalLayer = new ConvolutionalLayer();
	
	public float feedfoward(List<Integer> ordem, List<float[][]> matrizEntrada, 
			List<float[][]> kernelsConvolutional, List<int[]> listPoolings) {
		
		float sum = 0;
		List<float[][]> lista = new ArrayList<float[][]>();
		List<float[][]> auxiliar = new ArrayList<float[][]>();
		float[][] pooledMatrix;
		
		for (int w = 0; w < ordem.size(); w++) {
			if(ordem.get(w) == 0) { //convolução
				System.out.println("Convolução");
				if(auxiliar.isEmpty()) { //começando com a convolução
					lista = convolutionalLayer.ConvolutionalLayer(matrizEntrada.get(0), kernelsConvolutional);	
					auxiliar = lista; //fazendo uma cópia de segurança
				} else if (!auxiliar.isEmpty()) {
					lista = null; //apaga o objeto 'lista' original 
					lista = new ArrayList<float[][]>(); //cria novamente o objeto 'lista' para que possamos utilizá-lo
					for (int x = 0; x < auxiliar.size(); x++) {
						System.out.println("Auxiliar get: \n" + auxiliar.get(x));
						lista = convolutionalLayer.ConvolutionalLayer(auxiliar.get(0), kernelsConvolutional);
					}
					System.out.println("\n Convolução 2: \n"); // Exibir a matriz resultante após max-pooling
					  lista.forEach(a -> {
						  for (int i = 0; i < a.length; i++) { 
							  for (int j  = 0; j < a[0].length; j++) {
								  System.out.print(a[i][j] + " "); 
							  }
							  System.out.println("\n"); 
							}
						  System.out.println("-------");
					  });
					System.out.println("Tamanho convolucional: \n" + lista.size());
					auxiliar = null; //apaga o objeto 'auxiliar' original 
					auxiliar = lista;  //cópia de segurança
					System.out.println("Tamanho auxiliar: \n" + auxiliar.size());
				}
			} else if (ordem.get(w) == 1) { //pooling
				System.out.println("Pooling");
				if(!auxiliar.isEmpty()) { 
					lista = null; //apaga o objeto 'lista' original 
					lista = new ArrayList<float[][]>(); //cria novamente o objeto 'lista' para que possamos utilizá-lo
					for (int x = 0; x < auxiliar.size(); x++) {
						pooledMatrix = poolingLayer.PoolingLayer(listPoolings.get(0)[0], //pega o tipo de pooling do primeiro pooling, nesse caso, pooling mínimo 
								auxiliar.get(x), //pega cada uma das matrizes de retorno da convolutionalMatrix, retorna uma nova matriz com o pooling mínimo
								listPoolings.get(0)[1]); //pegando o tamanho do pooling, no primeiro pooling da lista
						lista.add(pooledMatrix); //adiciona as novas matrizes na 'lista'	
						
					}
					removeFirst(listPoolings);
					auxiliar = null; //apaga o objeto 'auxiliar' original 
					auxiliar = lista; //cópia de segurança
				} else if (auxiliar.isEmpty()) { //começando com o pooling
					System.out.println("Pooling, vazia");
				}
			} else { 
				System.out.println("Algo errado");
				//sum = lista.stream().mapToInt(num -> Integer.parseInt(num)).sum();
		        //System.out.println(sum);
			}
		}
		
        System.out.println("Lista: \n");
		  lista.forEach(a -> {
			  for (int i = 0; i < a.length; i++) { 
				  for (int j  = 0; j < a[0].length; j++) {
					  System.out.print(a[i][j] + " "); 
				  }
				  System.out.println("\n"); 
				}
			  System.out.println("-------");
		  });
		
		/*ordem.forEach(operacao -> {
			if(operacao == 0) { //convolução
				List<float[][]> lista1 = convolutionalLayer.ConvolutionalLayer(matrizEntrada.get(0), kernelsConvolutional);
				lista = lista1;
			}else if (operacao == 1) { //pooling
				
			}else { //soma do resultado final
				
			}
		});*/
		
		/*List<float[][]> convolutionalMatrix = convolutionalLayer.ConvolutionalLayer(matrizEntrada.get(0), kernelsConvolutional);
		List<float[][]> convolutionalMatrix2;
		float[][] pooledMatrix, pooledMatrix2;
		float sum = 0;

		for (int x = 0; x < convolutionalMatrix.size(); x++) {
			pooledMatrix = poolingLayer.PoolingLayer(listPoolings.get(0)[0], //pega o tipo de pooling do primeiro pooling, nesse caso, pooling mínimo 
					convolutionalMatrix.get(x), //pega cada uma das matrizes de retorno da convolutionalMatrix, retorna uma nova matriz com o pooling mínimo
					listPoolings.get(0)[1]); //pegando o tamanho do pooling, no primeiro pooling da lista
			convolutionalMatrix2 = convolutionalLayer.ConvolutionalLayer(pooledMatrix, kernelsConvolutional);
			for (int z = 0; z < convolutionalMatrix2.size(); z++) {
				pooledMatrix2 = poolingLayer.PoolingLayer(listPoolings.get(1)[0], //pega o tipo de pooling do segundo pooling, nesse caso, pooling máximo  
						convolutionalMatrix2.get(x), //pega cada uma das matrizes de retorno da convolutionalMatrix2, retorna uma nova matriz com o pooling máximo
						listPoolings.get(1)[1]); //pegando o tamanho do pooling, do segundo pooling da lista
				for (int i = 0; i < pooledMatrix2.length; i++) {
					for (int j = 0; j < pooledMatrix2[0].length; j++) {
						sum += pooledMatrix2[i][j];
					}
				}
			}
		}*/
		return sum;
	}
	
	
	public void removeFirst(List<int[]> listPoolings) {
        if (!listPoolings.isEmpty() && listPoolings.size() > 0) {
        	listPoolings.remove(0);
        }
    }
}
