package functions;

import java.util.ArrayList;
import java.util.List;

import entity.FeedfowardEntity;
import entity.PSOEntity;
import feedfoward.Feedfoward;
import javafx.scene.control.Alert.AlertType;
import util.Alerts;

public class AtualizaPSO {
	public List<PSOEntity> atualizaPSO(List<Integer> listaOrdemOperacoes, List<float[][]> listImageMatriz,
			FeedfowardEntity feedfowardEntity, List<PSOEntity> listaPsoEntity, int indiceMelhorGlobal) {
		PSOFuncoesAuxiliares psoFuncoesAuxiliares = new PSOFuncoesAuxiliares();
		Feedfoward feedfoward = new Feedfoward();
		Classificador classificador = new Classificador();
		Alerts alert = new Alerts();
		Error erro = new Error();

		for (int x = 0; x < listaPsoEntity.size(); x++) {
			float erroGeral = 0;
			List<List<float[][]>> listaListaKernels = listaPsoEntity.get(x).getListaListaKernels();
			List<List<float[][]>> listaListaVelocidade = listaPsoEntity.get(x).getVelocidade();
			for (int j = 0; j < listImageMatriz.size(); j++) {
				int tipoDeClassificador = 0;
				if (feedfoward.verificaTamanho(listaOrdemOperacoes, listImageMatriz, listaListaKernels,
						feedfowardEntity.getListaPoolings()) == true) {
					float resultadoFinal = feedfoward.feedfoward(listaOrdemOperacoes, listImageMatriz,
							listaListaKernels, feedfowardEntity.getListaPoolings());
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
			System.out.println("\n\nErro geral: " + erroGeral);
			listaPsoEntity.get(x).setErro(erroGeral);
			if (erroGeral < listaPsoEntity.get(indiceMelhorGlobal).getErro() && x != indiceMelhorGlobal) {
				listaPsoEntity.get(indiceMelhorGlobal).setMelhorGlobal(false);
				listaPsoEntity.get(x).setMelhorGlobal(true);
			}
		}

		listaPsoEntity.forEach(a -> {
			System.out.println(a.isMelhorGlobal());
		});
		return listaPsoEntity;
	}
}
