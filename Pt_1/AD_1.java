import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AD_1 {

	public static void main(String[] args) {
		int nombreCaracters = 0;
		int nombreLinies = 0;
		int nombreParaules = 0;
		int c;
		int[] frequencia = new int[65536];
		boolean dinsParaula = false;
		boolean retornAnterior = false;
		boolean hiHaCaractersLinia = false;

		try (FileReader fr = new FileReader("text.txt")) {
			// Llegim el fitxer una sola vegada, caràcter a caràcter.
			while ((c = fr.read()) != -1) {
				if (c != '\n' && c != '\r') {
					nombreCaracters++;
					hiHaCaractersLinia = true;
					retornAnterior = false;
				}

				// Un retorn de carro seguit de salt de línia és una sola línia.
				if (c == '\r') {
					nombreLinies++;
					hiHaCaractersLinia = false;
					retornAnterior = true;
				} else if (c == '\n') {
					if (!retornAnterior) {
						nombreLinies++;
					}
					hiHaCaractersLinia = false;
					retornAnterior = false;
				}

				boolean separador = c == ' ' || c == '\t' || c == '\n' || c == '\r';
				if (separador) {
					dinsParaula = false;
				} else if (!dinsParaula) {
					nombreParaules++;
					dinsParaula = true;
				}

				if (c != ' ' && c != '\t' && c != '\n' && c != '\r') {
					frequencia[c]++;
				}
			}

			// La darrera línia compta si no acaba amb un salt de línia.
			if (hiHaCaractersLinia) {
				nombreLinies++;
			}

			int majorFrequencia = 0;
			char caracterMesRepetit = 0;
			for (int i = 0; i < frequencia.length; i++) {
				if (frequencia[i] > majorFrequencia) {
					majorFrequencia = frequencia[i];
					caracterMesRepetit = (char) i;
				}
			}

			System.out.println("Nombre de caràcters: " + nombreCaracters);
			System.out.println("Nombre de línies: " + nombreLinies);
			System.out.println("Nombre de paraules: " + nombreParaules);
			if (majorFrequencia == 0) {
				System.out.println("Caràcter més repetit: Cap");
			} else {
				System.out.println("Caràcter més repetit: " + caracterMesRepetit);
			}
		} catch (FileNotFoundException e) {
			System.out.println("No s'ha trobat el fitxer text.txt.");
		} catch (IOException e) {
			System.out.println("S'ha produït un error en llegir el fitxer.");
		} catch (SecurityException e) {
			System.out.println("No es permet l'accés al fitxer.");
		}
	}
}
