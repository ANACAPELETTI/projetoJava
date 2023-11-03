package functions;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import entity.PSOEntity;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Import {

	public List<List<float[][]>> loadExcelFile(
			// ManipulateNode ReturnE,
			int tamanhoKernel, int numeroKernels

	) throws Exception {
		File selectedFile;
		List<List<float[][]>> particulasKernel = new ArrayList<List<float[][]>>();

		String caminho = "";

		ExtensionFilter xls = new ExtensionFilter("Excel (*.xls, *.xlsx)", "*.xls", "*.xlsx");

		FileChooser fileChooser = new FileChooser();

		fileChooser.getExtensionFilters().addAll(xls);

		selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			caminho = selectedFile.getPath();
		}
		if (caminho != "") {
			FileInputStream file = new FileInputStream(new File(caminho));

			Workbook workBook = new XSSFWorkbook(file);
			List<Sheet> listaPlanilhas = new ArrayList<Sheet>();

			for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
				Sheet sheet = workBook.getSheetAt(i);
				listaPlanilhas.add(sheet);
				List<float[][]> listaKernel = new ArrayList<float[][]>();
				int contador = 0;
				for (int j = 0; j < numeroKernels; j++) {
					float[][] kernels = new float[tamanhoKernel][tamanhoKernel];
					for (int linha = 0; linha < tamanhoKernel; linha++) {
						for (int coluna = 0; coluna < tamanhoKernel; coluna++) {
							Cell cell = sheet.getRow(contador).getCell(coluna);
							kernels[linha][coluna] = Float.valueOf(cell.toString());
						}
						contador++;
					}
					listaKernel.add(kernels);
				}
				particulasKernel.add(listaKernel);
			}

		}
		return particulasKernel;
	}
}