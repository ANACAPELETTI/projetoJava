package entity;

import java.util.ArrayList;
import java.util.List;

public class PSOEntity {
	private List<List<float[][]>> listaListaKernels;
	private List<List<float[][]>> velocidade;
	private float erro;
	private boolean melhorGlobal;
	private boolean melhorLocalAtual;
	private List<List<float[][]>> melhorLocal;
	
	public List<List<float[][]>> getMelhorLocal() {
		List<List<float[][]>> novoMelhorLocal = new ArrayList<List<float[][]>>(melhorLocal);
		return novoMelhorLocal;
	}
	public void setMelhorLocal(List<List<float[][]>> melhorLocal) {
		this.melhorLocal = melhorLocal;
	}
	public float getErro() {
		return erro;
	}
	public void setErro(float erro) {
		this.erro = erro;
	}
	public List<List<float[][]>> getVelocidade() {
		List<List<float[][]>> novaVelocidade = new ArrayList<List<float[][]>>(velocidade);
		return novaVelocidade;
	}
	public void setVelocidade(List<List<float[][]>> velocidade) {
		this.velocidade = velocidade;
	}
	public boolean isMelhorGlobal() {
		return melhorGlobal;
	}
	public void setMelhorGlobal(boolean melhorGlobal) {
		this.melhorGlobal = melhorGlobal;
	}
	public boolean isMelhorLocal() {
		return melhorLocalAtual;
	}
	public void setMelhorLocalAtual(boolean melhorLocalAtual) {
		this.melhorLocalAtual = melhorLocalAtual;
	}
	public List<List<float[][]>> getListaListaKernels() {
		List<List<float[][]>> listaListaKernelsNova = new ArrayList<List<float[][]>>(listaListaKernels);
		return listaListaKernelsNova;
	}
	public void setListaListaKernels(List<List<float[][]>> listaListaKernels) {
		this.listaListaKernels = listaListaKernels;
	}
	@Override
	public String toString() {
		return "PSOEntity [listaListaKernels=" + listaListaKernels + ", melhorGlobal=" + melhorGlobal + ", melhorLocal="
				+ melhorLocal + "]";
	}
}
