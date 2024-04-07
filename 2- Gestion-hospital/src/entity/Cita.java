package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {

    private Integer id;

    private Integer idPaciente;

    private Integer idMedico;

    private LocalDate fechaCita;

    private LocalTime horaCita;

    private String motivo;

    public Cita() {}

    public Cita(Integer id, Integer idPaciente, Integer idMedico, LocalDate fechaCita, LocalTime horaCita, String motivo) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.motivo = motivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    public LocalTime getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(LocalTime horaCita) {
        this.horaCita = horaCita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "----------------------------------------------------------------------------------------------------------\n"+
                "Cita\n" +
                "id:" + id +
                "\n id Paciente=" + idPaciente +
                "\n id Medico=" + idMedico +
                "\n Fecha Cita=" + fechaCita +
                "\n Hora Cita=" + horaCita +
                "\n Motivo=" + motivo +
                "\n----------------------------------------------------------------------------------------------------------\n\n";
    }
}
