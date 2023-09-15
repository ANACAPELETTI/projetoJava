package functions;

import java.io.File;

public class Error {
	public float erro(char letraClassificada, char classificacao) {
		char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		int numLetraClassificada = 0, numLetraClassificacao = 0;
		float erroFinal = 0;
		for (int i = 0; i < 26; i++) {
			if (letraClassificada == alphabet[i]) {
				numLetraClassificada = i;
			}
		}
		
		for (int j = 0; j < 26; j++) {
			if (classificacao == alphabet[j]) {
				numLetraClassificacao = j;
			}
		}
		
		erroFinal = Math.abs(numLetraClassificacao - numLetraClassificada);
		//System.out.println("\n numLetraClassificacao: "  + numLetraClassificacao);
		//System.out.println("\n numLetraClassificada: "  + numLetraClassificada);
		//System.out.println("\n Erro final: "+ erroFinal + "\n ------------------ \n");
		
		return erroFinal;
	}
}
