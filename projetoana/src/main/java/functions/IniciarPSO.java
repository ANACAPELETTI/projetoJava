package functions;

import java.util.ArrayList;
import java.util.List;

import entity.FeedfowardEntity;
import entity.PSOEntity;
import feedfoward.Feedfoward;
import javafx.scene.control.Alert.AlertType;
import util.Alerts;

public class IniciarPSO {
	public List<PSOEntity> inicializaPSO(List<Integer> listaOrdemOperacoes, List<float[][]> listImageMatriz,
			FeedfowardEntity feedfowardEntity, int numeroParticulas) {
		PSOFuncoesAuxiliares psoFuncoesAuxiliares = new PSOFuncoesAuxiliares();
		List<PSOEntity> listaPsoEntity = new ArrayList<PSOEntity>();
		Feedfoward feedfoward = new Feedfoward();
		Classificador classificador = new Classificador();
		Alerts alert = new Alerts();
		Error erro = new Error();

		for (int x = 0; x < numeroParticulas; x++) {
			float erroGeral = 0;
			List<List<float[][]>> listaListaKernels = psoFuncoesAuxiliares.criaListaListaKernels();
			List<List<float[][]>> listaListaVelocidade = psoFuncoesAuxiliares.criaVelocidade0();
			PSOEntity psoEntity = new PSOEntity();
			psoEntity.setListaListaKernels(listaListaKernels);
			psoEntity.setVelocidade(listaListaVelocidade);
			listaPsoEntity.add(psoEntity);
			for (int j = 0; j < listImageMatriz.size(); j++) {
				float[][] imageMatriz2 = listImageMatriz.get(j);
				int tipoDeClassificador = 0;
				//if (feedfoward.verificaTamanho(listaOrdemOperacoes, listImageMatriz, psoEntity.getListaListaKernels(),
				if (feedfoward.verificaTamanho(listaOrdemOperacoes, listImageMatriz, listaPsoEntity.get(x).getListaListaKernels(),
						feedfowardEntity.getListaPoolings()) == true) {
					float resultadoFinal = feedfoward.feedfoward(listaOrdemOperacoes, listImageMatriz,
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
			System.out.println("\n\nErro geral: " + erroGeral);
			psoEntity.setErro(erroGeral);
			if (x == 0) {
				psoEntity.setMelhorGlobal(true);
			} else {
				if (listaPsoEntity.get(x - 1).getErro() > erroGeral) {
					listaPsoEntity.get(x - 1).setMelhorGlobal(false);
					listaPsoEntity.get(0).setMelhorGlobal(false);
					psoEntity.setMelhorGlobal(true);
				} else {
					psoEntity.setMelhorGlobal(false);
				}
			}
		}

		listaPsoEntity.forEach(a -> {
			System.out.println(a.isMelhorGlobal());
		});
		return listaPsoEntity;
	}
}
