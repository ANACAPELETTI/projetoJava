package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FeedfowardEntity {
	private List<int[]> listaPoolings;
	private List<Integer> listaOrdemOperacoes;
	private List<float[][]> listImageMatriz;
	private List<Character> letraCorreta;

	public List<Character> getLetraCorreta() {
		return letraCorreta;
	}

	public void setLetraCorreta(List<Character> letraCorreta) {
		this.letraCorreta = letraCorreta;
	}

	public List<Integer> getListaOrdemOperacoes() {
		return listaOrdemOperacoes;
	}

	public void setListaOrdemOperacoes(List<Integer> listaOrdemOperacoes) {
		this.listaOrdemOperacoes = listaOrdemOperacoes;
	}

	public List<float[][]> getListImageMatriz() {
		return listImageMatriz;
	}

	public void setListImageMatriz(List<float[][]> listImageMatriz) {
		this.listImageMatriz = listImageMatriz;
	}

	public List<int[]> getListaPoolings() {
		List<int[]> listaPoolingsNova = new ArrayList<int[]>(listaPoolings);
		return listaPoolingsNova;
	}

	public void setListaPoolings(List<int[]> listaPoolings) {
		this.listaPoolings = listaPoolings;
	}
	
}
