package functions;

public class Classificador {
	public char classifica(float resultadoFeedfoward, int tipoClassificador) {
		char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char letraClassificada = ' ';
		if (tipoClassificador == 0) {
			double letra = sigmoid(resultadoFeedfoward) * 26;
			//System.out.println("Resultado sigmoide: "+ letra);
			for (int i = 0; i < 26; i++) {
				if (letra > i && letra <= i + 1) {
					letraClassificada = alphabet[i];
				}
			}
		} else if (tipoClassificador == 1) {
			double letra = tangenteHiperbolica(resultadoFeedfoward) * 26;
			//System.out.println("Resultado tangente hiperbólica: "+ letra);
			for (int i = 0; i < 26; i++) {
				if (letra > i && letra <= i + 1) {
					letraClassificada = alphabet[i];
				}
			}
		} else {
			System.out.println("Tipo de classificador inserido incorretamente");
		}
		return letraClassificada;
	}
	
	public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }
	
	public static double tangenteHiperbolica(double x) {
        return Math.tanh(x);
    }
}
