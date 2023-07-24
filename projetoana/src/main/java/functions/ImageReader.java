package functions;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public class ImageReader {
	public void imageReader() {
		Image image = new Image("/images/casa.png");
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
		//System.out.println(matrixImageRed);
	}
}

