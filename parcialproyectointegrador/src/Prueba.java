
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Prueba extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContraseña;
    private JButton btnAceptar;
    private JButton btnListar;
    private JTable tabla;
    private DefaultTableModel modelo;
    private static final String ARCHIVO_USUARIOS = "usuarios.txt";
    private static final String ARCHIVO_NINOS = "datosNinos.txt";

    public Prueba() {
        setTitle("Registro y Listado de Niños");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(173, 216, 230)); // Fondo azul claro

        // Panel para registro
        JPanel panelRegistro = new JPanel();
        panelRegistro.setLayout(new BoxLayout(panelRegistro, BoxLayout.Y_AXIS));
        panelRegistro.setBackground(new Color(173, 216, 230)); // Fondo azul claro


        txtUsuario = new JTextField(20);
        panelRegistro.add(new JLabel("NOMBRE: "));
        panelRegistro.add(txtUsuario);

        txtContraseña = new JPasswordField(20);
        panelRegistro.add(new JLabel("EDAD:"));
        panelRegistro.add(txtContraseña);

        btnAceptar = new JButton("GUARDAR");
        panelRegistro.add(btnAceptar);

        add(panelRegistro, BorderLayout.NORTH);

        // Panel para listado
        JPanel panelListado = new JPanel();
        panelListado.setLayout(new BorderLayout());
        panelListado.setBackground(new Color(173, 216, 230)); // Fondo azul claro

        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);
        modelo.addColumn("Niño");
        modelo.addColumn("Edad");

        JScrollPane scrollPane = new JScrollPane(tabla);
        panelListado.add(scrollPane, BorderLayout.CENTER);

        add(panelListado, BorderLayout.CENTER);

        // Botón para listar
        btnListar = new JButton("Listar");
        panelListado.add(btnListar, BorderLayout.SOUTH);

        // Posicionamiento y visibilidad
        setLocationRelativeTo(null);
        setVisible(true);


        // Acción para el botón "Aceptar"
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String contraseña = new String(txtContraseña.getPassword());

                if (validarDatos(usuario, contraseña)) {
                    guardarUsuario(usuario, contraseña);


                }

                txtUsuario.setText("");
                txtContraseña.setText("");
            }
        });

        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout(FlowLayout.LEFT)); // Alinear el botón a la izquierda
        panelBoton.setBackground(new Color(173, 216, 230)); // Fondo azul claro

// Crear el botón
        JButton btnAbrirPagina = new JButton("ATRÁS");
        btnAbrirPagina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la página actual
                SwingUtilities.invokeLater(() -> {
                    PaginaInicio paginaInicio = new PaginaInicio();
                    paginaInicio.setVisible(true); // Abre la nueva página
                });
            }
        });

// Agregar el botón al panelBoton
        panelBoton.add(btnAbrirPagina);

// Agregar el panelBoton al contenedor principal de PaginaInicio
        add(panelBoton, BorderLayout.WEST);


        // Acción para el botón "Listar"
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpiar la tabla antes de listar los usuarios
                modelo.setRowCount(0);

                // Leer y mostrar los usuarios
                try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_NINOS))) {
                    String linea;
                    while ((linea = reader.readLine()) != null) {
                        String[] partes = linea.split(",");
                        if (partes.length == 2) {
                            String usuario = partes[0].trim();
                            String contraseña = partes[1].trim();
                            modelo.addRow(new Object[]{usuario, contraseña});
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Prueba.this, "Error al leer los datos del archivo.");
                }
            }
        });
    }

    private boolean verificarCredenciales(String usuario, String contraseña) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2 && partes[0].trim().equals(usuario) && partes[1].trim().equals(contraseña)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al leer los datos del archivo.");
        }
        return false;
    }

    private void escribirUsuario(String usuario, String contraseña) {
        try (FileWriter writer = new FileWriter(ARCHIVO_USUARIOS, true);
             BufferedWriter bw = new BufferedWriter(writer);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(usuario + "," + contraseña);
            JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al escribir en el archivo de usuarios.");
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PaginaInicio paginaInicio = new PaginaInicio();
            paginaInicio.setVisible(true);
        });
    }

    private static class PaginaInicio extends JFrame {
        public PaginaInicio() {
            setTitle("Página de Inicio");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getContentPane().setBackground(new Color(173, 216, 230)); // Fondo azul claro

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBackground(new Color(173, 216, 230)); // Fondo azul claro

            JLabel lblTitulo = new JLabel("Bienvenido");
            lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
            lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(lblTitulo);

            JButton btnIniciarSesion = new JButton("Iniciar Sesión");
            btnIniciarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnIniciarSesion.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /*Prueba prueba = new Prueba();
                    prueba.setVisible(true);
                    dispose(); // Cerrar la página de inicio después de abrir Prueba*/
                    String usuario = JOptionPane.showInputDialog("Ingrese su nombre de usuario:");
                    String contraseña = JOptionPane.showInputDialog("Ingrese su contraseña:");

                    if (!usuario.isEmpty() && !contraseña.isEmpty()) {
                        if (verificarLogin(usuario, contraseña)) {
                            JOptionPane.showMessageDialog(null, "Inicio de sesión completado.");

                            setLocationRelativeTo(null);

                            Prueba prueba = new Prueba();


                        } else {
                            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese usuario y contraseña.");
                    }

                }
            });
            panel.add(btnIniciarSesion);

            JButton btnRegistrarse = new JButton("Registrar");
            btnRegistrarse.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnRegistrarse.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String usuario = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
                    String contraseña = JOptionPane.showInputDialog("Ingrese la contraseña:");
                    Prueba prueba = new Prueba();
                    prueba.escribirUsuario(usuario, contraseña);
                }
            });
            panel.add(btnRegistrarse);


            add(panel);
            setLocationRelativeTo(null);
        }
    }

    private static class MostrarUsuarios extends JFrame {

    }

    private static boolean verificarLogin(String usuario, String contraseña) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2 && partes[0].trim().equals(usuario) && partes[1].trim().equals(contraseña)) {
                    return true; // Coincidencia encontrada
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer los datos del archivo.");
        }
        return false; // No se encontró coincidencia
    }

    public static void guardarUsuario(String usuario, String contraseña) {
        try (FileWriter fileWriter = new FileWriter(ARCHIVO_NINOS, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            printWriter.println(usuario + "," + contraseña);
            JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al escribir en el archivo de usuarios.");
        }
    }

    public static boolean validarDatos(String usuario, String contraseña) {
        // Verificar si los campos están vacíos
        if (usuario.isEmpty() || contraseña.isEmpty()) {
            System.out.println("Por favor, complete todos los campos.");
            return false;
        }

        // Verificar si la contraseña contiene solo números
        for (char c : contraseña.toCharArray()) {
            if (!Character.isDigit(c)) {
                System.out.println("La contraseña debe contener solo números.");
                return false;
            }
        }

        return true;
    }

}