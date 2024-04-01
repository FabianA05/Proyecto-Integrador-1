package colorearPixelArt;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class EscenaPixelArt extends Scene {
    private VBox principal, filas;
    private HBox[] cuadradosEnFila;
    private HBox colores;
    private Button btnGuardar;

    public EscenaPixelArt(Parent contenedor, double ancho, double alto) {
        super(contenedor, ancho, alto);
        principal = (VBox) contenedor;
        principal.setSpacing(30);
        filas = new VBox(3);
        cuadradosEnFila = new HBox[20];
        colores = new HBox(15);
        colores.setAlignment(Pos.TOP_CENTER);
        btnGuardar = new Button("Guardar imagen");
        montarEscena();
    }
    private void montarEscena() {
        principal.setAlignment(Pos.TOP_CENTER);

        for (int i=0; i<cuadradosEnFila.length; i++){
            cuadradosEnFila[i] = new HBox(3);
            cuadradosEnFila[i].setAlignment(Pos.TOP_CENTER);

            for (int j=0; j<20; j++){
                cuadradosEnFila[i].getChildren().add(new CuadradoColorear());
            }
            filas.getChildren().add(cuadradosEnFila[i]);
        }
        prepararColores();

        btnGuardar.setOnAction(e -> guardarImagen());

        principal.getChildren().addAll(filas, colores, btnGuardar);
    }

    private void guardarImagen() {
        quitarBlancos();

        WritableImage imagen = filas.snapshot(new SnapshotParameters(), null);
        File archivo = new File("C:\\Users\\Usuario\\Documents\\imagenPixel.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(imagen, null), "png", archivo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        restaurarBlancos();
    }
    private void restaurarBlancos() {
        for (int i = 0; i<cuadradosEnFila.length; i++){
            List fila = cuadradosEnFila[i].getChildren();
            for (int j = 0; j<fila.size(); j++){
                CuadradoColorear c = (CuadradoColorear) fila.get(j);
                if (!c.getColoreado()) fila.set(j, new CuadradoColorear());
            }
        }
    }

    private void quitarBlancos() {
        for (int i = 0; i<cuadradosEnFila.length; i++){
            List fila = cuadradosEnFila[i].getChildren();
            for (int j = 0; j<fila.size(); j++){
                CuadradoColorear c = (CuadradoColorear) fila.get(j);
                if (!c.getColoreado()) c.blanquear();
            }
        }
    }

    private void prepararColores() {
        String[] nombresColores = {"beige", "white", "gray", "black", "orange", "red", "crimson", "blue", "cyan", "darkblue", "green", "lightgreen","yellow", "purple", "mediumvioletred", "pink", "brown"};
        for (String s : nombresColores) {
            colores.getChildren().add(new CuadradoColorear(STR."-fx-background-color: \{s};"));
        }
    }
}
