package functions;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public class ImageReader {
	public float[][] imageReader(String caminhoImage) {
		Image image = new Image(caminhoImage);
		PixelReader reader = image.getPixelReader();
		int height = (int) image.getHeight();
		int width = (int) image.getWidth();

		List<List<Float>> matrixImageGray = new ArrayList<List<Float>>();
		
		for(int i = 0 ; i < height; i++) {
			List<Float> linhaGray = new ArrayList<Float>();
			for(int j = 0 ; j < width; j++) {
				Color c = reader.getColor(i, j);
				linhaGray.add((float) (c.getRed() * 0.3 + c.getGreen() * 0.59 + c.getBlue() * 0.11)); //fórmula do Funck
			}
			matrixImageGray.add(linhaGray);
		}
		return listaParaMatriz(matrixImageGray); 
	}
	
	public float[][] listaParaMatriz(List<List<Float>> listaRGB) {
		int height = (int) listaRGB.size();
		int width = (int) listaRGB.get(0).size();
		float[][] matrizRGB = new float[height][width];
		for(int i = 0 ; i < height; i++) {
			for(int j = 0 ; j < width; j++) {
				matrizRGB[i][j] = listaRGB.get(j).get(i);
			}
		}
		return matrizRGB;
	}
}

