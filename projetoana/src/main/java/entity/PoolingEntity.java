package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PoolingEntity {
	private List<int[]> listaPoolings;

	public List<int[]> getListaPoolings() {
		List<int[]> listaPoolingsNova = new ArrayList<int[]>(listaPoolings);
		return listaPoolingsNova;
	}

	public void setListaPoolings(List<int[]> listaPoolings) {
		this.listaPoolings = listaPoolings;
	}
	
}
