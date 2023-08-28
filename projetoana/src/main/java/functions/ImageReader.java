package functions;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public class ImageReader {
	public float[][] imageReader(String caminhoImage) {
		Image image = new Image(caminhoImage);
		System.out.println("Leu: "+caminhoImage);
		PixelReader reader = image.getPixelReader();
		int height = (int) image.getHeight();
		int width = (int) image.getWidth();
		
		List<List<Float>> matrixImageRed = new ArrayList<List<Float>>();
		List<List<Float>> matrixImageGreen = new ArrayList<List<Float>>();
		List<List<Float>> matrixImageBlue = new ArrayList<List<Float>>();
		
		for(int i = 0 ; i < height; i++) {
			List<Float> linhaRed = new ArrayList<Float>();
			List<Float> linhaGreen = new ArrayList<Float>();
			List<Float> linhaBlue = new ArrayList<Float>();
			for(int j = 0 ; j < width; j++) {
				Color c = reader.getColor(i, j);
				linhaRed.add((float) c.getRed());
				linhaGreen.add((float) c.getGreen());
				linhaBlue.add((float) c.getBlue());
			}
			matrixImageRed.add(linhaRed);
			matrixImageGreen.add(linhaGreen);
			matrixImageBlue.add(linhaBlue);
		}
		return listaParaMatriz(matrixImageBlue);
	}
	
	/*
	public List<List<List<Float>>> imageReader(String caminhoImage) {
		Image image = new Image(caminhoImage);
		System.out.println("Leu: "+ caminhoImage);
		PixelReader reader = image.getPixelReader();
		int height = (int) image.getHeight();
		int width = (int) image.getWidth();
		
		List<List<List<Float>>> listImageMatriz = new ArrayList<List<List<Float>>>();
		List<List<Float>> matrixImageRed = new ArrayList<List<Float>>();
		List<List<Float>> matrixImageGreen = new ArrayList<List<Float>>();
		List<List<Float>> matrixImageBlue = new ArrayList<List<Float>>();
		
		for(int i = 0 ; i < height; i++) {
			List<Float> linhaRed = new ArrayList<Float>();
			List<Float> linhaGreen = new ArrayList<Float>();
			List<Float> linhaBlue = new ArrayList<Float>();
			for(int j = 0 ; j < width; j++) {
				Color c = reader.getColor(i, j);
				linhaRed.add((float) c.getRed());
				linhaGreen.add((float) c.getGreen());
				linhaBlue.add((float) c.getBlue());
			}
			matrixImageRed.add(linhaRed);
			matrixImageGreen.add(linhaGreen);
			matrixImageBlue.add(linhaBlue);
		}
		listImageMatriz.add(matrixImageRed);
		listImageMatriz.add(matrixImageGreen);
		listImageMatriz.add(matrixImageBlue);
		return listImageMatriz;
	}
	*/
	
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

