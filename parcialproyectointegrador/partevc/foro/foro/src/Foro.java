import java.util.ArrayList;

class Foro {
    private ArrayList<Publicacion> publicaciones;

    public Foro() {
        this.publicaciones = new ArrayList<>();
    }

    public void agregarPublicacion(String autor, String contenido) {
        Publicacion publicacion = new Publicacion(autor, contenido);
        publicaciones.add(publicacion);
    }

    public ArrayList<Publicacion> getPublicaciones() {
        return publicaciones;
    }
}