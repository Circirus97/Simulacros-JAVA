package entity;

public class Especialidad {

    private Integer id;

    private String nombre;

    private String descripcion;

    public Especialidad(Integer id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Especialidad() {}

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return
                "Especialidad\n" +
                "id:" + id +
                "\n Nombre:" + nombre +
                "\n Descripcion:" + descripcion +
                "\n----------------------------------------------------------------------------------------------------------\n\n";
    }


}
