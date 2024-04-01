import java.util.ArrayList;

class Publicacion {
    private String autor;
    private String contenido;
    private ArrayList<String> respuestas;

    public Publicacion(String autor, String contenido) {
        this.autor = autor;
        this.contenido = contenido;
        this.respuestas = new ArrayList<>();
    }

    public String getAutor() {
        return autor;
    }

    public String getContenido() {
        return contenido;
    }

    public ArrayList<String> getRespuestas() {
        return respuestas;
    }

    public void agregarRespuesta(String respuesta) {
        respuestas.add(respuesta);
    }

    @Override
    public String toString() {
        return autor + ": " + contenido;
    }
}