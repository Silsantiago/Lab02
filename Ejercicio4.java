import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Ejercicio4 {

    
    private static Carro[] carros;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Ejercicio4::crearInterfaz);
    }

    
    private static void crearInterfaz() {
        JFrame frame = new JFrame("Gestión de Carros Usados");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new GridLayout(5, 2));

        JTextField txtMarca = new JTextField();
        JTextField txtModelo = new JTextField();
        JTextField txtColor = new JTextField();
        JTextField txtKilometraje = new JTextField();
        JTextField txtCantidad = new JTextField();

        panelDatos.add(new JLabel("Marca:"));
        panelDatos.add(txtMarca);
        panelDatos.add(new JLabel("Modelo:"));
        panelDatos.add(txtModelo);
        panelDatos.add(new JLabel("Color:"));
        panelDatos.add(txtColor);
        panelDatos.add(new JLabel("Kilometraje:"));
        panelDatos.add(txtKilometraje);
        panelDatos.add(new JLabel("Cantidad de Carros:"));
        panelDatos.add(txtCantidad);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());

        JButton btnAgregar = new JButton("Agregar Carro");
        JButton btnOrdenarBurbuja = new JButton("Ordenar Burbuja");
        JButton btnOrdenarMerge = new JButton("Ordenar MergeSort");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnOrdenarBurbuja);
        panelBotones.add(btnOrdenarMerge);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        frame.add(panelDatos, BorderLayout.NORTH);
        frame.add(panelBotones, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String marca = txtMarca.getText();
                    String modelo = txtModelo.getText();
                    String color = txtColor.getText();
                    double kilometraje = Double.parseDouble(txtKilometraje.getText());
                    int cantidad = Integer.parseInt(txtCantidad.getText());

                    if (carros == null || carros.length != cantidad) {
                        carros = new Carro[cantidad];
                    }

                    for (int i = 0; i < cantidad; i++) {
                        carros[i] = new Carro(marca, modelo, color, kilometraje);
                    }

                    textArea.append("Carros agregados:\n");
                    for (Carro carro : carros) {
                        textArea.append(carro.toString() + "\n");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ingrese datos válidos.");
                }
            }
        });

        btnOrdenarBurbuja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (carros != null) {
                    burbuja(carros);
                    textArea.append("Carros ordenados por Burbuja:\n");
                    for (Carro carro : carros) {
                        textArea.append(carro.toString() + "\n");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Primero agregue los carros.");
                }
            }
        });

        btnOrdenarMerge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (carros != null) {
                    mergesort(carros, 0, carros.length - 1);
                    textArea.append("Carros ordenados por MergeSort:\n");
                    for (Carro carro : carros) {
                        textArea.append(carro.toString() + "\n");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Primero agregue los carros.");
                }
            }
        });

        frame.setVisible(true);
    }

    private static void burbuja(Carro[] carros) {
        int n = carros.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (carros[j].getKilometraje() > carros[j + 1].getKilometraje()) {
                    Carro temp = carros[j];
                    carros[j] = carros[j + 1];
                    carros[j + 1] = temp;
                }
            }
        }
    }

    private static void mergesort(Carro[] carros, int izquierda, int derecha) {
        if (izquierda < derecha) {
            int medio = (izquierda + derecha) / 2;
            mergesort(carros, izquierda, medio);
            mergesort(carros, medio + 1, derecha);
            merge(carros, izquierda, medio, derecha);
        }
    }

    private static void merge(Carro[] carros, int izquierda, int medio, int derecha) {
        int n1 = medio - izquierda + 1;
        int n2 = derecha - medio;

        Carro[] izquierdaArray = new Carro[n1];
        Carro[] derechaArray = new Carro[n2];

        System.arraycopy(carros, izquierda, izquierdaArray, 0, n1);
        System.arraycopy(carros, medio + 1, derechaArray, 0, n2);

        int i = 0, j = 0;
        int k = izquierda;
        while (i < n1 && j < n2) {
            if (izquierdaArray[i].getKilometraje() <= derechaArray[j].getKilometraje()) {
                carros[k++] = izquierdaArray[i++];
            } else {
                carros[k++] = derechaArray[j++];
            }
        }

        while (i < n1) {
            carros[k++] = izquierdaArray[i++];
        }

        while (j < n2) {
            carros[k++] = derechaArray[j++];
        }
    }
}

class Carro {
    private String marca;
    private String modelo;
    private String color;
    private double kilometraje;

    public Carro(String marca, String modelo, String color, double kilometraje) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.kilometraje = kilometraje;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    @Override
    public String toString() {
        return String.format("Marca: %s, Modelo: %s, Color: %s, Kilometraje: %.2f km", marca, modelo, color, kilometraje);
    }
}
