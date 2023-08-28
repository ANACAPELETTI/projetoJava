package functions;

import java.io.File;

public class Error {
	public float erro(char letraClassificada, char classificacao) {
		if (letraClassificada == classificacao) {
			return 0; //Verdadeiro
		} else {
			return 1; //Falso
		}
	}
}
