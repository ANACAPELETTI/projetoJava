package functions;

import java.io.File;

public class Error {
	public boolean erro(char letraClassificada, char classificacao) {
		if (letraClassificada == classificacao) {
			return true;
		} else {
			return false;
		}
	}
	
	public static char getFirstLetterFromImageName(String imageName) {
        if (imageName != null && !imageName.isEmpty()) {
            String fileName = new File(imageName).getName();
            if (!fileName.isEmpty()) {
                char firstLetter = fileName.charAt(0);
                return firstLetter;
            }
        }
        return '\0';  // Valor padrão caso não seja possível obter a primeira letra
    }
}
