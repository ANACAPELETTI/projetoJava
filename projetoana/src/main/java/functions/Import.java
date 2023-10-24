package functions;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import entity.PSOEntity;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Import {
	private int numeroColunas;
	private int numeroLinhas;
	private GridPane dadosExperimentaisGridPane = new GridPane();
	private GridPane resultadosGridPane = new GridPane();
	private PSOEntity psoEntity = new PSOEntity();
	//private ManipulateNode Return = new ManipulateNode();
	
	public List<List<float[][]>> loadExcelFile(
			//ManipulateNode ReturnE,
			int tamanhoKernel,
			int numeroKernels
		
			) throws Exception {
		File selectedFile;
		List<List<float[][]>> particulasKernel = new ArrayList<List<float[][]>>();

		String caminho = "";

		ExtensionFilter xls = new ExtensionFilter("Excel (*.xls, *.xlxs)", "*.*");

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
			//Sheet sheet = workBook.getSheetAt(0);
			
			for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
				Sheet sheet = workBook.getSheetAt(i);
				listaPlanilhas.add(sheet);
				List<float[][]> listaKernel = new ArrayList<float[][]>();
				int contador = 0;
				for (int j = 0; j < numeroKernels; j++) {
					float[][] kernels = new float[tamanhoKernel][tamanhoKernel];
					for (int linha = contador; linha < tamanhoKernel * numeroKernels; linha++) {
						for (int coluna = 0; coluna < tamanhoKernel; coluna++) {
							kernels[i][j] = (float) sheet.getRow(linha).getCell(coluna).getNumericCellValue();
						}
						contador++;
					}
					listaKernel.add(kernels);
				}
				particulasKernel.add(listaKernel);
			}
			psoEntity.setListaListaKernels(particulasKernel);
		}
		return particulasKernel;
	}
}
