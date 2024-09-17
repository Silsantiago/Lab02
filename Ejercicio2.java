import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ejercicio2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce una línea de texto:");
        String linea = scanner.nextLine();
        
        Map<Character, Integer> frecuencia = new HashMap<>();
        for (char c : linea.toCharArray()) {
            frecuencia.put(c, frecuencia.getOrDefault(c, 0) + 1);
        }
        
        char caracterMaxFrecuencia = obtenerCaracterMaximo(frecuencia);
        String resultadoReemplazo = reemplazarVocales(linea, caracterMaxFrecuencia);
        String resultadoInvertido = new StringBuilder(resultadoReemplazo).reverse().toString();
        
        System.out.println("Texto con vocales sustituidas: " + resultadoReemplazo);
        System.out.println("Texto invertido: " + resultadoInvertido);
        
        scanner.close();
    }

    private static char obtenerCaracterMaximo(Map<Character, Integer> frecuencia) {
        char maxChar = '\0';
        int maxFreq = 0;
        
        for (Map.Entry<Character, Integer> entry : frecuencia.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                maxChar = entry.getKey();
            }
        }
        
        return maxChar;
    }

    private static String reemplazarVocales(String linea, char caracterReemplazo) {
        return linea.replaceAll("[aeiouAEIOU]", String.valueOf(caracterReemplazo));
    }
}

