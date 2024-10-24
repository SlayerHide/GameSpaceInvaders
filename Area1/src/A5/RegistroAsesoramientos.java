package A5;

import java.util.ArrayList;
import java.util.List;

public class RegistroAsesoramientos {
	List<Asesoramiento> asesoramientos;
   
	RegistroAsesoramientos() {
        asesoramientos = new ArrayList<>();
    }
    void agregarAsesoramiento(String materia, String maestro, String horario, String salon) {
        if (materia == null || maestro == null || horario == null || salon == null ||
            materia.isEmpty() || maestro.isEmpty() || horario.isEmpty() || salon.isEmpty()) {
            System.out.println("Error: Los campos no pueden estar vacíos.");
            return;
        }
        asesoramientos.add(new Asesoramiento(materia, maestro, horario,salon));
    }
    List<Asesoramiento> buscarPorMateria(String materia) {
        List<Asesoramiento> resultado = new ArrayList<>();
        for (Asesoramiento asesoramiento : asesoramientos) {
            if (asesoramiento.materia.equalsIgnoreCase(materia)) {
                resultado.add(asesoramiento);
            }
        }
        return resultado;
    }
    List<Asesoramiento> buscarPorMaestro(String maestro) {
        List<Asesoramiento> resultado = new ArrayList<>();
        for (Asesoramiento asesoramiento : asesoramientos) {
            if (asesoramiento.maestro.equalsIgnoreCase(maestro)) {
                resultado.add(asesoramiento);
            }
        }
        return resultado;
    }
   List<Asesoramiento> buscarPorHorario(String horario) {
        List<Asesoramiento> resultado = new ArrayList<>();
        for (Asesoramiento asesoramiento : asesoramientos) {
            if (asesoramiento.horario.equalsIgnoreCase(horario)) {
                resultado.add(asesoramiento);
            }
        }
        return resultado;
    }
    List<Asesoramiento> buscarPorSalon(String salon) {
        List<Asesoramiento> resultado = new ArrayList<>();
        for (Asesoramiento asesoramiento : asesoramientos) {
            if (asesoramiento.salon.equalsIgnoreCase(salon)) {
                resultado.add(asesoramiento);
            }
        }
        return resultado;
    }
    void eliminarAsesoramiento(String materia, String maestro, String horario, String salon) {
        asesoramientos.removeIf(a -> a.materia.equalsIgnoreCase(materia) &&
                                     a.maestro.equalsIgnoreCase(maestro) &&
                                     a.horario.equalsIgnoreCase(horario) &&
                                     a.salon.equalsIgnoreCase(salon));
    }
    void mostrarAsesoramientos() {
        if (asesoramientos.isEmpty()) {
            System.out.println("No hay asesoramientos registrados.");
        } else {
            asesoramientos.forEach(System.out::println);
        }
}
}

