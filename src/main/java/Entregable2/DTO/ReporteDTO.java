package Entregable2.DTO;

public class ReporteDTO {
    private String nombreCarrera;
    private int inscriptos;
    private int egresados;
    private int anio;

    public ReporteDTO() {}

    public ReporteDTO(String nombreCarrera, int inscriptos, int egresados, int anio) {
        this.nombreCarrera = nombreCarrera;
        this.inscriptos = inscriptos;
        this.egresados = egresados;
        this.anio = anio;
    }
    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public int getInscriptos() {
        return inscriptos;
    }

    public int getEgresados() {
        return egresados;
    }

    public int getAnio() {
        return anio;
    }


    @Override
    public String toString() {
        return "ReporteDTO{" +
                "nombreCarrera='" + nombreCarrera + '\'' +
                ", inscriptos=" + inscriptos +
                ", egresados=" + egresados +
                ", anio=" + anio +
                '}';
    }
}
