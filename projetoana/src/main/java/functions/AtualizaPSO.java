package functions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import entity.FeedfowardEntity;
import entity.PSOEntity;
import feedfoward.Feedfoward;
import javafx.scene.control.Alert.AlertType;
import util.Alerts;

public class AtualizaPSO {
	public void atualizaPSO(List<Integer> listaOrdemOperacoes, List<float[][]> listImageMatriz,
			FeedfowardEntity feedfowardEntity, List<PSOEntity> listaPsoEntity, List<Float> erros) {
		Feedfoward feedfoward = new Feedfoward();
		Classificador classificador = new Classificador();
		Alerts alert = new Alerts();
		Error erro = new Error();

		for (int x = 0; x < listaPsoEntity.size(); x++) {
			float erroGeral = 0;
			for (int j = 0; j < listImageMatriz.size(); j++) {
				int tipoDeClassificador = 0;
				List<float[][]> newImageMatrizList = new ArrayList<float[][]>();
				newImageMatrizList.add(listImageMatriz.get(j));
				if (feedfoward.verificaTamanho(listaOrdemOperacoes, newImageMatrizList, listaPsoEntity.get(x).getListaListaKernels(),
						feedfowardEntity.getListaPoolings()) == true) {
					float resultadoFinal = feedfoward.feedfoward(listaOrdemOperacoes, newImageMatrizList,
							listaPsoEntity.get(x).getListaListaKernels(), feedfowardEntity.getListaPoolings());
					//System.out.println("Resultado final: " + resultadoFinal);
					char letraClassificada = classificador.classifica(resultadoFinal, tipoDeClassificador);
					//System.out.println("Letra classificada: " + letraClassificada);
					char letraCerta = feedfowardEntity.getLetraCorreta().get(j);
					//System.out.println("Letra certa: " + letraCerta);
					//System.out.println("Classificação certa? " + erro.erro(letraClassificada, letraCerta));
					erroGeral += erro.erro(letraClassificada, letraCerta);
					//System.out.println("-------------");
				} else {
					alert.showAlert("Não é possível realizar as operações", null, null, AlertType.ERROR);
				}
			}
			System.out.println("\n\nErro geral: " + erroGeral + " particula: " +x);
			listaPsoEntity.get(x).setErro(erroGeral);
			if (erroGeral < listaPsoEntity.get(x).getErro()) {
				listaPsoEntity.get(x).setMelhorLocal(listaPsoEntity.get(x).getListaListaKernels()); //atualizando o melhor local, com o novo melhor
				listaPsoEntity.get(x).setErro(erroGeral);
				System.out.println("\nNovo melhor local: " + listaPsoEntity.get(x).getErro());
			}
			if (erroGeral < Collections.min(erros)) {
				for(int melhor = 0; melhor < listaPsoEntity.size(); melhor++) {
					if(melhor != x) {
						listaPsoEntity.get(melhor).setMelhorGlobal(false);
					}
				}
				listaPsoEntity.get(x).setMelhorGlobal(true);
			}
			erros.set(x, erroGeral);
		}
		listaPsoEntity.forEach(a -> {
			System.out.println(a.isMelhorGlobal());
		});
	}
}
