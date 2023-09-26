package functions;

import java.util.ArrayList;
import java.util.Collections;
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
		List<Float> erros = new ArrayList<Float>();
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
			psoEntity.setMelhorLocal(listaListaKernels);
			listaPsoEntity.add(psoEntity);
			for (int j = 0; j < listImageMatriz.size(); j++) {
				int tipoDeClassificador = 0;
				List<float[][]> newImageMatrizList = new ArrayList<float[][]>();
				newImageMatrizList.add(listImageMatriz.get(j));
				if (feedfoward.verificaTamanho(listaOrdemOperacoes, newImageMatrizList, psoEntity.getListaListaKernels(),
						feedfowardEntity.getListaPoolings()) == true) {
					float resultadoFinal = feedfoward.feedfoward(listaOrdemOperacoes, newImageMatrizList,
							psoEntity.getListaListaKernels(), feedfowardEntity.getListaPoolings());
					//System.out.println("Resultado final: " + resultadoFinal);
					char letraClassificada = classificador.classifica(resultadoFinal, tipoDeClassificador);
					//System.out.println("Letra classificada: " + letraClassificada);
					char letraCerta = feedfowardEntity.getLetraCorreta().get(j);
					//System.out.println("Letra certa: " + letraCerta);
					//System.out.println("Classificação certa? " + erro.erro(letraClassificada, letraCerta));
					//psoEntity.setErro(erro.erro(letraClassificada, letraCerta)); //adicionando o erro a partícula
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
				for(int melhor = 0; melhor < x; melhor++) {
					if (erroGeral < listaPsoEntity.get(melhor).getErro()) {
						listaPsoEntity.get(melhor).setMelhorGlobal(false);
					}
				}
				if (erroGeral < Collections.min(erros)) {
					listaPsoEntity.get(x).setMelhorGlobal(true);
				}
			}
			erros.add(erroGeral);
		}
		listaPsoEntity.forEach(a -> {
			System.out.println(a.isMelhorGlobal());
		});
		return listaPsoEntity;
	}
}
