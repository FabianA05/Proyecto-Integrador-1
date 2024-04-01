import java.util.ArrayList;
import java.util.List;

public class RegistroActividades {
    private List<String> actividades;

    public RegistroActividades() {
        actividades = new ArrayList<>();
    }

    public void registrarActividad(String usuario, String actividad) {
        String registro = usuario + ": " + actividad;
        actividades.add(registro);
        System.out.println("Actividad registrada: " + registro);
    }

    public List<String> obtenerActividadesRecientes(String usuario, int cantidad) {
        List<String> actividadesUsuario = new ArrayList<>();
        for (int i = actividades.size() - 1; i >= 0; i--) {
            String actividad = actividades.get(i);
            if (actividad.startsWith(usuario)) {
                actividadesUsuario.add(actividad);
                cantidad--;
            }
            if (cantidad <= 0) {
                break;
            }
        }
        return actividadesUsuario;
    }

    public static void main(String[] args) {
        RegistroActividades registro = new RegistroActividades();
        registro.registrarActividad("Usuario1", "Inició sesión");
        registro.registrarActividad("Usuario2", "Completó el juego de crucigrama");
        registro.registrarActividad("Usuario1", "Publicó en el foro");

        List<String> actividadesRecientes = registro.obtenerActividadesRecientes("Usuario1", 2);
        System.out.println("Actividades recientes de Usuario1:");
        for (String actividad : actividadesRecientes) {
            System.out.println(actividad);
        }
    }
}

