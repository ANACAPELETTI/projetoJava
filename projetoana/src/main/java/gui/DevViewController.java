package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DevViewController {
	@FXML
	Button botao = new Button();
	
	@FXML
	GridPane matrizConfusao = new GridPane();
	
	@FXML
	public void criarMatrizConfusao() {
		Label texto = new Label();
		texto.setText("Opa");
		Label texto1 = new Label();
		texto1.setText("Opa2");
		Label texto2 = new Label();
		texto2.setText("Opa3");
		matrizConfusao.addColumn(0, texto);
		matrizConfusao.addColumn(1, texto1);
		matrizConfusao.addColumn(2, texto2);
		
		matrizConfusao.getColumnConstraints().clear();
		ColumnConstraints colResultados = new ColumnConstraints();
		colResultados.setFillWidth(false);
		colResultados.setMinWidth(50);
		colResultados.setMaxWidth(50);
		colResultados.setHalignment(HPos.CENTER);
		RowConstraints rowResultados = new RowConstraints();
		rowResultados.setMinHeight(50);
		rowResultados.setMaxHeight(50);
		rowResultados.setValignment(VPos.CENTER);
		matrizConfusao.getColumnConstraints().add(colResultados);
		matrizConfusao.getColumnConstraints().add(colResultados);
		matrizConfusao.getColumnConstraints().add(colResultados);
		
		matrizConfusao.getRowConstraints().clear();
		
		matrizConfusao.getRowConstraints().add(rowResultados);
		matrizConfusao.getRowConstraints().add(rowResultados);
		matrizConfusao.getRowConstraints().add(rowResultados);
	}
}