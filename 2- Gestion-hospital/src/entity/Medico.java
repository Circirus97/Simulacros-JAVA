package entity;

public class Medico {

    private Integer id;

    private String nombre;

    private String apellido;

    private Integer id_especialidad;

    private Especialidad especialidad;

    public Medico(Integer id, String nombre, String apellido, Integer id_especialidad, Especialidad especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.id_especialidad = id_especialidad;
        this.especialidad = especialidad;
    }

    public Medico() {}

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

    public Integer getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(Integer id_especialidad) {
        this.id_especialidad = id_especialidad;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return
                "Medico: \n" +
                "id:" + id +
                "\nNombre:" + nombre +
                "\nApellido:" + apellido +
                "\nID Especialidad:" + id_especialidad;
    }
}
