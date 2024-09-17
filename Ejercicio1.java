import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio1 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Ingrese el tamaño del arreglo: ");
            int tamaño = scanner.nextInt();
            
            double[] arreglo = generarArregloAleatorio(tamaño);
            System.out.println("Arreglo generado: " + Arrays.toString(arreglo));
            
            double media = calcularMedia(arreglo);
            double mediana = calcularMediana(arreglo);
            double varianza = calcularVarianza(arreglo, media);
            double desviacionEstandar = Math.sqrt(varianza);
            double moda = calcularModa(arreglo);
            
            System.out.printf("Media: %.2f\n", media);
            System.out.printf("Mediana: %.2f\n", mediana);
            System.out.printf("Varianza: %.2f\n", varianza);
            System.out.printf("Desviación estándar: %.2f\n", desviacionEstandar);
            System.out.printf("Moda: %.2f\n", moda);
        }
    }

    private static double[] generarArregloAleatorio(int tamaño) {
        Random rand = new Random();
        double[] arreglo = new double[tamaño];
        for (int i = 0; i < tamaño; i++) {
            arreglo[i] = rand.nextDouble() * 1000;
        }
        return arreglo;
    }

    private static double calcularMedia(double[] arreglo) {
        double suma = 0;
        for (double num : arreglo) {
            suma += num;
        }
        return suma / arreglo.length;
    }

    private static double calcularMediana(double[] arreglo) {
        Arrays.sort(arreglo);
        int n = arreglo.length;
        if (n % 2 == 0) {
            return (arreglo[n / 2 - 1] + arreglo[n / 2]) / 2.0;
        } else {
            return arreglo[n / 2];
        }
    }

    private static double calcularVarianza(double[] arreglo, double media) {
        double suma = 0;
        for (double num : arreglo) {
            suma += Math.pow(num - media, 2);
        }
        return suma / arreglo.length;
    }

    private static double calcularModa(double[] arreglo) {
        Map<Double, Integer> frecuencia = new HashMap<>();
        for (double num : arreglo) {
            frecuencia.put(num, frecuencia.getOrDefault(num, 0) + 1);
        }
        double moda = arreglo[0];
        int maxFrecuencia = 0;
        for (Map.Entry<Double, Integer> entry : frecuencia.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                maxFrecuencia = entry.getValue();
                moda = entry.getKey();
            }
        }
        return moda;
    }
}
