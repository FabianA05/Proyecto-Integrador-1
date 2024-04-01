import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ForoGUI extends JFrame {
    private Foro foro;
    private JTextField campoNombre;
    private JTextArea areaContenido;
    private JTextArea areaRespuestas;
    private JList<Publicacion> listaPublicaciones;

    public ForoGUI() {
        this.foro = new Foro();
        this.setTitle("Foro");
        this.setSize(600, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel panelEntrada = new JPanel(new GridLayout(2, 2));
        JLabel etiquetaNombre = new JLabel("Tu Nombre:");
        campoNombre = new JTextField();
        JLabel etiquetaContenido = new JLabel("Contenido del Post:");
        areaContenido = new JTextArea();
        panelEntrada.add(etiquetaNombre);
        panelEntrada.add(campoNombre);
        panelEntrada.add(etiquetaContenido);
        panelEntrada.add(areaContenido);
        this.add(panelEntrada, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(1, 4));
        JButton botonAgregarPost = new JButton("Agregar Post");
        JButton botonResponder = new JButton("Responder a Post");
        JButton botonVerTodos = new JButton("Ver Todos los Posts");
        JButton botonSalir = new JButton("Salir");
        panelBotones.add(botonAgregarPost);
        panelBotones.add(botonResponder);
        panelBotones.add(botonVerTodos);
        panelBotones.add(botonSalir);
        this.add(panelBotones, BorderLayout.CENTER);

        JPanel panelRespuestas = new JPanel(new BorderLayout());
        JLabel etiquetaRespuestas = new JLabel("Respuestas:");
        areaRespuestas = new JTextArea();
        areaRespuestas.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaRespuestas);
        panelRespuestas.add(etiquetaRespuestas, BorderLayout.NORTH);
        panelRespuestas.add(scrollPane, BorderLayout.CENTER);
        this.add(panelRespuestas, BorderLayout.SOUTH);

        inicializarListaPublicaciones();

        botonAgregarPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText();
                String contenido = areaContenido.getText();
                if (!nombre.isEmpty() && !contenido.isEmpty()) {
                    agregarPublicacion(nombre, contenido);
                } else {
                    JOptionPane.showMessageDialog(ForoGUI.this, "Por favor, ingresa tu nombre y el contenido del post.");
                }
            }
        });

        botonResponder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                responderAPublicacion();
            }
        });

        botonVerTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verTodasPublicaciones();
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void inicializarListaPublicaciones() {
        listaPublicaciones = new JList<>();
        JScrollPane scrollPane = new JScrollPane(listaPublicaciones);
        scrollPane.setPreferredSize(new Dimension(200, 200));
        add(scrollPane, BorderLayout.WEST);
        listaPublicaciones.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarRespuestasSeleccionadas();
            }
        });
    }

    private void mostrarRespuestasSeleccionadas() {
        Publicacion seleccionada = listaPublicaciones.getSelectedValue();
        if (seleccionada != null) {
            StringBuilder builder = new StringBuilder();
            for (String respuesta : seleccionada.getRespuestas()) {
                builder.append("- ").append(respuesta).append("\n");
            }
            areaRespuestas.setText(builder.toString());
        }
    }

    private void agregarPublicacion(String autor, String contenido) {
        foro.agregarPublicacion(autor, contenido);
        actualizarListaPublicaciones();
        limpiarCampos();
        JOptionPane.showMessageDialog(this, "Publicación agregada exitosamente.");
    }

    private void actualizarListaPublicaciones() {
        listaPublicaciones.setListData(foro.getPublicaciones().toArray(new Publicacion[0]));
    }

    private void responderAPublicacion() {
        Publicacion seleccionada = listaPublicaciones.getSelectedValue();
        if (seleccionada != null) {
            String respuesta = JOptionPane.showInputDialog(this, "Ingresa tu respuesta:");
            if (respuesta != null && !respuesta.isEmpty()) {
                seleccionada.agregarRespuesta(respuesta);
                mostrarRespuestasSeleccionadas();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una publicación para responder.");
        }
    }

    private void verTodasPublicaciones() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < foro.getPublicaciones().size(); i++) {
            Publicacion publicacion = foro.getPublicaciones().get(i);
            builder.append("Publicación ").append(i + 1).append(" - Autor: ").append(publicacion.getAutor())
                    .append("\nContenido: ").append(publicacion.getContenido()).append("\n\nRespuestas:\n");
            ArrayList<String> respuestas = publicacion.getRespuestas();
            for (String respuesta : respuestas) {
                builder.append("- ").append(respuesta).append("\n");
            }
            builder.append("\n-----------------\n");
        }
        areaRespuestas.setText(builder.toString());
    }

    private void limpiarCampos() {
        campoNombre.setText("");
        areaContenido.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ForoGUI foro = new ForoGUI();
                foro.setVisible(true);
            }
        });
    }
}