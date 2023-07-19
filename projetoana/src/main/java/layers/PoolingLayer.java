package layers;

import java.util.ArrayList;
import java.util.List;

import functions.Pooling;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public class PoolingLayer {
	Pooling pooling = new Pooling();
	float[][] pooledMatrix;
	public float[][] PoolingLayer(int tipoPooling, float tamanhoMatrizEntrada[][], int tamanhoPooling) {
		/*
		 * Tipo Pooling = 
		 * 1 = Pooling mínimo
		 * 2 = Pooling médio
		 * 3 = Pooling máximo 
		 * 4 = Teste da classe subMatrix
		 */
		switch (tipoPooling) {
			case 1:
				pooledMatrix = pooling.poolingMin(tamanhoMatrizEntrada, tamanhoPooling);
				return pooledMatrix;
			case 2:
				pooledMatrix = pooling.poolingMed(tamanhoMatrizEntrada, tamanhoPooling);
				return pooledMatrix;
			case 3:
				pooledMatrix = pooling.poolingMax(tamanhoMatrizEntrada, tamanhoPooling);
				return pooledMatrix;
			case 4:
				pooledMatrix = pooling.teste(tamanhoMatrizEntrada, tamanhoPooling);
				return pooledMatrix;
			default:
				break;
			}
		return pooledMatrix;
	}
}
