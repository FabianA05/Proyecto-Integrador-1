import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trivia extends JFrame {
    private List<Question> questions;
    private List<Question> shuffledQuestions;
    private int currentQuestionIndex;
    private int score;

    public Trivia() {
        setTitle("Trivia Cuidado Marino");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeQuestions();

        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        JPanel titlePanel = new JPanel(new GridBagLayout());
        panel.add(titlePanel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("<html><div style='text-align: center;'>Bienvenido al juego de preguntas y respuestas.<br/>¡Pon a prueba tu conocimiento acerca del ecosistema marino!</div></html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titlePanel.add(titleLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(buttonPanel, BorderLayout.SOUTH);

        JButton startButton = new JButton("Comenzar");
        startButton.setFont(new Font("Arial", Font.PLAIN, 16));
        buttonPanel.add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                startGame();
                showNextQuestion();
            }
        });
        setVisible(true);
    }

    private void initializeQuestions() {
        questions = new ArrayList<>();
        questions.add(new Question("¿Cuál es el animal más grande del mundo?", "A", "Ballena azul", "Delfín", "Tiburón", "Mantarraya"));
        questions.add(new Question("¿Qué porcentaje de la superficie de la Tierra está cubierta por océanos?", "B", "Aproximadamente 71%", "Aproximadamente 50%", "Aproximadamente 30%", "Aproximadamente 90%"));
        questions.add(new Question("¿Cuál es el mayor arrecife de coral del mundo?", "C", "Arrecife de Coral del Caribe", "Arrecife de Coral de Hawái", "Gran Barrera de Coral", "Arrecife de Coral de Palau"));
        questions.add(new Question("¿Cuál es el océano más grande del mundo?", "C", "Océano Atlántico", "Océano Índico", "Océano Pacífico", "Océano Ártico"));
        questions.add(new Question("¿Cuál es el nombre del proceso mediante el cual el agua del mar se convierte en vapor de agua?", "A", "Evaporación", "Condensación", "Precipitación", "Transpiración"));
        questions.add(new Question("¿Cuál es el mayor depredador marino?", "D", "Orca", "Manta Raya", "Tiburón Ballena", "Tiburón Blanco"));
        questions.add(new Question("¿Qué animal es conocido como el 'león del mar'?", "C", "Foca", "Nutria Marina", "León Marino", "Morsa"));
        questions.add(new Question("¿Cuál es el nombre del proceso mediante el cual el vapor de agua se convierte en gotas de agua o hielo?", "B", "Evaporación", "Condensación", "Precipitación", "Transpiración"));
        questions.add(new Question("¿Cuál es el depredador más grande del océano?", "D", "Tiburón Blanco", "Megalodón", "Orca", "Ballena Asesina"));
        questions.add(new Question("¿Qué animal marino es conocido como el 'ángel del mar'?", "A", "Manta Raya", "Tiburón Martillo", "Mero", "Delfín"));
        questions.add(new Question("¿Qué significa 'TSUNAMI' en japonés?", "D", "Gran Ola", "Gran Tsunami", "Ola de Montaña", "Ola del Puerto"));
        questions.add(new Question("¿Qué es el 'fitoplancton'?", "A", "Plantas Microscópicas", "Animales Microscópicos", "Bacterias", "Hongos"));
        questions.add(new Question("¿Qué especie de tortuga marina es la más grande?", "B", "Tortuga Baula", "Tortuga Laúd", "Tortuga Verde", "Tortuga Carey"));
        questions.add(new Question("¿Cuál es el pez más rápido del océano?", "A", "Atún", "Pez Espada", "Marlín", "Tiburón Mako"));
        questions.add(new Question("¿Cuál es el nombre del proceso mediante el cual el agua cae de las nubes a la Tierra?", "C", "Evaporación", "Condensación", "Precipitación", "Transpiración"));
        questions.add(new Question("¿Qué es el 'zooplancton'?", "B", "Plantas Microscópicas", "Animales Microscópicos", "Bacterias", "Hongos"));
        questions.add(new Question("¿Cuál es el pez más grande del océano?", "C", "Atún", "Pez Espada", "Tiburón Ballena", "Tiburón Blanco"));
        questions.add(new Question("¿Cuál es el océano más cálido?", "B", "Océano Atlántico", "Océano Índico", "Océano Pacífico", "Océano Ártico"));
        questions.add(new Question("¿Qué animal marino es conocido como el 'gigante amistoso'?", "D", "Ballena", "Tiburón Ballena", "Tiburón Martillo", "Manta Raya"));
        questions.add(new Question("¿Cuál es el mayor productor de oxígeno en la Tierra?", "A", "Fitoplancton", "Zooplancton", "Algas Marinas", "Coral"));
    }

    private void startGame() {
        shuffledQuestions = new ArrayList<>(questions);
        Collections.shuffle(shuffledQuestions);

        currentQuestionIndex = 0;
        score = 0;
    }

    private void showNextQuestion() {
        if (currentQuestionIndex < shuffledQuestions.size()) {
            Question currentQuestion = shuffledQuestions.get(currentQuestionIndex);
            StringBuilder message = new StringBuilder();
            message.append("<html>");
            message.append("<b>").append(currentQuestion.getQuestion()).append("</b><br><br>");
            message.append("A. ").append(currentQuestion.getOptionA()).append("<br>");
            message.append("B. ").append(currentQuestion.getOptionB()).append("<br>");
            message.append("C. ").append(currentQuestion.getOptionC()).append("<br>");
            message.append("D. ").append(currentQuestion.getOptionD()).append("<br><br>");
            message.append("Puntaje actual: ").append(score).append(" puntos<br>");
            message.append("</html>");

            String selectedOption = (String) JOptionPane.showInputDialog(null, message.toString(), "Pregunta " + (currentQuestionIndex + 1), JOptionPane.PLAIN_MESSAGE, null, null, null);
            if (selectedOption != null) {
                if (selectedOption.equalsIgnoreCase(currentQuestion.getCorrectAnswer())) {
                    score += 5;
                    JOptionPane.showMessageDialog(null, "¡Respuesta correcta! Ganaste 5 puntos.", "Respuesta correcta", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Respuesta incorrecta. La respuesta correcta es: " + currentQuestion.getCorrectAnswerText(), "Respuesta incorrecta", JOptionPane.ERROR_MESSAGE);
                }
                currentQuestionIndex++;
                showNextQuestion();
            }
        } else {
            int choice = JOptionPane.showOptionDialog(null, "¡Felicidades, has completado todas las preguntas!\nPuntaje total: " + score + " puntos.\n¿Deseas jugar de nuevo?", "Fin del juego", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Nuevo juego", "Salir"}, "Nuevo juego");
            if (choice == JOptionPane.YES_OPTION) {
                startGame();
                showNextQuestion();
            } else {
                JOptionPane.showMessageDialog(null, "¡Gracias por jugar! ¡Que tengas un feliz día!", "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        }
    }
}
