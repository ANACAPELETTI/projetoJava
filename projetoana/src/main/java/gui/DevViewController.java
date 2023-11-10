package gui;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class DevViewController {
	@FXML
	Button botao = new Button();
	
	@FXML
	GridPane matrizConfusao = new GridPane();
	
	@FXML
    public void criarMatrizConfusao() {
        // Chame o método para criar e exibir a matriz durante a inicialização da interface
        exibirMatriz();
    }
	
	private int[][] matriz = {
	            {1, 2, 3, 4},
	            {5, 6, 7, 8},
	            {9, 10, 11, 12},
	            {13, 14, 15, 16}
	};
	 
	double larguraFixa = 40.0;

    private void exibirMatriz() {
        // Limpe o GridPane antes de criar uma nova matriz
    	matrizConfusao.getChildren().clear();

        // Obtenha o número de linhas e colunas da matriz
        int linhas = matriz.length;
        int colunas = matriz[0].length;

        // Use loops para percorrer a matriz e exibir os valores no GridPane
        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                Label label = new Label(String.valueOf(matriz[linha][coluna]));
                label.setMinWidth(larguraFixa);
                label.setMinHeight(larguraFixa);
                label.setStyle("-fx-alignment: CENTER;"); // Alinha o texto ao centro
                matrizConfusao.add(label, coluna, linha);
            }
        }
    }
}