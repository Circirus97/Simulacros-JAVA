package entity;

import java.time.LocalDate;

public class Paciente {

    private Integer id;

    private String nombre;

    private String apellido;

    private LocalDate fechaNacimiento;

    private String documentoIdentidad;

    public Paciente(Integer id, String nombre, String apellido, LocalDate fechaNacimiento, String documentoIdentidad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.documentoIdentidad = documentoIdentidad;
    }

    public Paciente() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    @Override
    public String toString() {
        return  "Paciente: \n" +
                "id:" + id +
                ", Nombre:" + nombre +
                ", Apellido:" + apellido +
                ", Fecha nacimiento:" + fechaNacimiento +
                ", Documento identidad:" + documentoIdentidad +
                "\n----------------------------------------------------------------------------------------------------------\n\n";
    }
}


