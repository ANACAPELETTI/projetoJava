package functions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Export {
	
	public void exportExcel(List<List<float [][]>> listaListaKernel) throws IOException {

		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
		List<Sheet> listaPlanilhas = new ArrayList<Sheet>();
		for(int i = 0 ; i < listaListaKernel.size(); i++) {
			Sheet sheet = workbook.createSheet("Lista" + i); //criando as planilhas de listas de kernels
			listaPlanilhas.add(sheet);
			for (int j = 0; j < listaListaKernel.get(i).size(); j++) {
				for(int linha = 0 ; linha < listaListaKernel.get(i).get(j).length; linha++) {
					sheet.createRow(j*listaListaKernel.get(i).size() + linha + j);
					System.out.println("Num:\n" + (j*listaListaKernel.get(i).size() + linha + j));
					for(int k = 0 ; k < listaListaKernel.get(i).get(j).length; k++) {
						listaPlanilhas.get(i).getRow(j*listaListaKernel.get(i).size() + linha + j).createCell(k);
						listaPlanilhas.get(i).getRow(j*listaListaKernel.get(i).size() + linha + j).getCell(k).setCellValue(listaListaKernel.get(i).get(j)[linha][k]);
					}
				}
			}
		}
		
		DecimalFormat formatter = new DecimalFormat("#0.00");
		FileOutputStream fileOut = new FileOutputStream("ListaKernels.xlsx");
		workbook.write(fileOut);
		fileOut.close();

	}
}