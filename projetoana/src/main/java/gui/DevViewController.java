package gui;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class DevViewController {
	@FXML
	Button botao = new Button();
	
	@FXML
	Label labelVertical = new Label();
	
	@FXML
	Label labelHorizontal = new Label();
	
	@FXML
	GridPane matrizConfusao = new GridPane();
	
	@FXML
    public void criarMatrizConfusao() {
        // Chame o método para criar e exibir a matriz durante a inicialização da interface
        exibirMatriz();
    }
	
	
	private int[][] matriz = {
	            {1, 2, 3, 4, 5}, 
	            {6, 7, 8, 9, 10},
	            {11, 12, 13, 14, 15},
	            {16, 17, 18, 19, 20},
	            {21, 22, 23, 24, 25}
	};
	 
	double larguraFixa = 40.0;

	private void exibirMatriz() {
		labelVertical.setVisible(true);
        labelHorizontal.setVisible(true);
        // Limpe o GridPane antes de criar uma nova matriz
        matrizConfusao.getChildren().clear();
        matrizConfusao.getColumnConstraints().clear();
        matrizConfusao.getRowConstraints().clear();

        matrizConfusao.setGridLinesVisible(true);

        // Obtenha o número de linhas e colunas da matriz
        int linhas = matriz.length;
        int colunas = matriz[0].length;

        // Use loops para percorrer a matriz e exibir os valores no GridPane
        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                Label label = new Label(String.valueOf(matriz[linha][coluna]));
                label.setMinSize(larguraFixa, larguraFixa);
                label.setMaxSize(larguraFixa, larguraFixa);
                label.setPrefSize(larguraFixa, larguraFixa);
                label.setAlignment(Pos.CENTER);

                matrizConfusao.add(label, coluna, linha); // Adiciona a label na coluna e linha corretas

                if (matrizConfusao.getColumnConstraints().size() <= coluna) { // Configuração para ajustar o tamanho de todas as colunas
                    matrizConfusao.getColumnConstraints().add(new ColumnConstraints(larguraFixa));
                } else {
                    matrizConfusao.getColumnConstraints().get(coluna).setMinWidth(larguraFixa);
                    matrizConfusao.getColumnConstraints().get(coluna).setMaxWidth(larguraFixa);
                    matrizConfusao.getColumnConstraints().get(coluna).setPrefWidth(larguraFixa);
                }
            }
            matrizConfusao.getRowConstraints().add(new RowConstraints(larguraFixa)); // Configuração para ajustar o tamanho de todas as linhas
        }
    }

}