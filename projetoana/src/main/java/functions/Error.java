package functions;

public class Error {
	public float erro(char letraClassificada, char letraCerta) {
		char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		int numLetraClassificada = 0, numLetraClassificacao = 0;
		float erroFinal = 0;
		for (int i = 0; i < 26; i++) {
			if (letraClassificada == alphabet[i]) {
				numLetraClassificada = i;
			}
		}
		for (int j = 0; j < 26; j++) {
			if (letraCerta == alphabet[j]) {
				numLetraClassificacao = j;
			}
		}
		erroFinal = Math.abs(numLetraClassificacao - numLetraClassificada);
		return erroFinal;
	}
}
