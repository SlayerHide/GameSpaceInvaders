package A5;

public class Asesoramiento {
	String materia;
    String maestro;
    String horario;
    String salon;
    Asesoramiento(String materia, String maestro, String horario, String salon) {
        this.materia = materia;
        this.maestro = maestro;
        this.horario = horario;
        this.salon = salon;
    }
    @Override
    public String toString() {
        return "Materia: " + materia + ", Maestro: " + maestro + ", Horario: " + horario + " Salon: " + salon;
    }
}
