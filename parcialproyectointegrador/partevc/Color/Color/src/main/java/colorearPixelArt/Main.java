package colorearPixelArt;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage escenario) throws Exception {
        EscenaPixelArt escena = new EscenaPixelArt(new VBox(), 800, 800);

        escenario.setTitle("PixelArt                                                                          SACA TU IMAGINACIÓN Y ¡DISFRUTA!");
        escenario.setScene(escena);
        escenario.show();
    }
}
