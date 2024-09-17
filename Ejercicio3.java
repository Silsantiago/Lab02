import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Seleccione el método de ordenamiento:");
        System.out.println("1. Burbuja");
        System.out.println("2. Inserción");
        System.out.println("3. Selección");
        System.out.println("4. MergeSort");
        int opcion = scanner.nextInt();

        int[] tamaños = {100, 500, 1000, 5000, 10000};

        System.out.printf("%-10s %-10s%n", "Tamaño", "Tiempo (ms)");

        for (int tamaño : tamaños) {
            double[] arreglo = generarArregloAleatorio(tamaño);

            double[] arregloCopia = arreglo.clone();

            long tiempo = medirTiempo(() -> {
                switch (opcion) {
                    case 1:
                        burbuja(arregloCopia);
                        break;
                    case 2:
                        insercion(arregloCopia);
                        break;
                    case 3:
                        seleccion(arregloCopia);
                        break;
                    case 4:
                        mergesort(arregloCopia, 0, arregloCopia.length - 1);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        return;
                }
            });

            System.out.printf("%-10d %-10d%n", tamaño, tiempo);
        }

        scanner.close();
    }

    private static double[] generarArregloAleatorio(int tamaño) {
        Random rand = new Random();
        double[] arreglo = new double[tamaño];
        for (int i = 0; i < tamaño; i++) {
            arreglo[i] = rand.nextDouble() * 1000;
        }
        return arreglo;
    }

    private static long medirTiempo(Runnable algoritmo) {
        long inicio = System.currentTimeMillis();
        algoritmo.run();
        return System.currentTimeMillis() - inicio;
    }

    private static void burbuja(double[] arreglo) {
        int n = arreglo.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arreglo[j] > arreglo[j + 1]) {
                    double temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                }
            }
        }
    }

    private static void insercion(double[] arreglo) {
        int n = arreglo.length;
        for (int i = 1; i < n; i++) {
            double clave = arreglo[i];
            int j = i - 1;
            while (j >= 0 && arreglo[j] > clave) {
                arreglo[j + 1] = arreglo[j];
                j--;
            }
            arreglo[j + 1] = clave;
        }
    }

    private static void seleccion(double[] arreglo) {
        int n = arreglo.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arreglo[j] < arreglo[minIndex]) {
                    minIndex = j;
                }
            }
            double temp = arreglo[minIndex];
            arreglo[minIndex] = arreglo[i];
            arreglo[i] = temp;
        }
    }

    private static void mergesort(double[] arreglo, int izquierda, int derecha) {
        if (izquierda < derecha) {
            int medio = (izquierda + derecha) / 2;
            mergesort(arreglo, izquierda, medio);
            mergesort(arreglo, medio + 1, derecha);
            merge(arreglo, izquierda, medio, derecha);
        }
    }

    private static void merge(double[] arreglo, int izquierda, int medio, int derecha) {
        int n1 = medio - izquierda + 1;
        int n2 = derecha - medio;

        double[] izquierdaArray = new double[n1];
        double[] derechaArray = new double[n2];

        System.arraycopy(arreglo, izquierda, izquierdaArray, 0, n1);
        System.arraycopy(arreglo, medio + 1, derechaArray, 0, n2);

        int i = 0, j = 0;
        int k = izquierda;
        while (i < n1 && j < n2) {
            if (izquierdaArray[i] <= derechaArray[j]) {
                arreglo[k++] = izquierdaArray[i++];
            } else {
                arreglo[k++] = derechaArray[j++];
            }
        }

        while (i < n1) {
            arreglo[k++] = izquierdaArray[i++];
        }

        while (j < n2) {
            arreglo[k++] = derechaArray[j++];
        }
    }
}

