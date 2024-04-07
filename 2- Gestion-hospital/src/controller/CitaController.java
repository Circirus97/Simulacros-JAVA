package controller;

import entity.Cita;
import entity.Medico;
import entity.Paciente;
import model.CitaModel;
import model.MedicoModel;
import model.PacienteModel;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CitaController {
    MedicoModel medicoModel;
    CitaModel citaModel;
    PacienteModel pacienteModel;

    public CitaController(CitaModel citaModel, MedicoModel medicoModel, PacienteModel pacienteModel) {
        this.medicoModel = new MedicoModel();
        this.citaModel = new CitaModel();
        this.pacienteModel = new PacienteModel();
    }

    public void delete(){

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "Lista de citas: \n" + citaModel.findAll() + "\nIngrese el ID de la cita a eliminar"));

        this.citaModel.delete(id);

    }

    public void update(){

        List<Cita> citaList = citaModel.findAll();

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "Lista de Citas:\n" + citaList + "\nIngrese el ID de la cita a actualizar"));

        Cita citaFiltro = citaList.stream().filter(cita1 -> cita1.getId().equals(id)).findFirst().get();
        LocalDate fechaCita = LocalDate.parse(JOptionPane.showInputDialog(null, "Ingrese la fecha de la cita:(YYYY-MM-DD)", citaFiltro.getFechaCita()));
        LocalTime horaCita = LocalTime.parse(JOptionPane.showInputDialog(null, "Ingrese la hora de la cita: (HH:MM:SS)", citaFiltro.getHoraCita()));
        String motivo = JOptionPane.showInputDialog(null, "Ingrese el motivo de la cita: ", citaFiltro.getMotivo());

        Cita cita = new Cita();

        cita.setFechaCita(fechaCita);
        cita.setHoraCita(horaCita);
        cita.setMotivo(motivo);
        cita.setIdMedico(citaFiltro.getIdMedico());
        cita.setIdPaciente(citaFiltro.getIdPaciente());
        cita.setId(id);


        this.citaModel.update(cita);

    }

    public void findByFilters() {

        String[] options = {"ID", "Fecha", "Hora", "Medico", "Paciente"};

        String selectedFilter = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de filtro\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (selectedFilter == "Medico") {
            List<Medico> medicoList = medicoModel.findAll();
            Object[] optionsMedico = medicoList.stream().map(Medico::getNombre).toArray();
            String selectedFilterMedico = (String) JOptionPane.showInputDialog(null, "Seleccione el medico\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsMedico, optionsMedico[0]);

            Medico medico = medicoList.stream().filter(medicoFilter -> medicoFilter.getNombre().equals(selectedFilterMedico)).findFirst().get();

            List<Cita> citaList = this.citaModel.findByFilter(selectedFilter, String.valueOf(medico.getId()));
        }
        if (selectedFilter == "Paciente") {
            List<Paciente> pacienteList = pacienteModel.findAll();
            Object[] optionsPaciente = pacienteList.stream().map(Paciente::getNombre).toArray();
            String selectedFilterPaciente = (String) JOptionPane.showInputDialog(null, "Seleccione el paciente\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsPaciente, optionsPaciente[0]);

            Paciente paciente = pacienteList.stream().filter(medicoFilter -> medicoFilter.getNombre().equals(selectedFilterPaciente)).findFirst().get();

            List<Cita> citaList = this.citaModel.findByFilter(selectedFilter, String.valueOf(paciente.getId()));
        }


        String valueFilter = JOptionPane.showInputDialog(null, "Ingrese el dato solicitado para la consulta\n" + "(ID de la cita, fecha de la cita, hora de la cita, Medico, Paciente)");

        List<Cita> citaList = this.citaModel.findByFilter(selectedFilter, valueFilter);



    }

    public void findAll() {

        List<Cita> citaList = this.citaModel.findAll();
        JOptionPane.showMessageDialog(null, "Lista de citas:\n" + citaList);
    }

    public void create() {

        Cita cita = new Cita();

        List<Medico> medicoList = medicoModel.findAll();
        List<Paciente> pacienteList = pacienteModel.findAll();

        Object[] optionsPaciente = pacienteList.stream().map(Paciente::getNombre).toArray();
        String selectedFilterPaciente =
                (String) JOptionPane.showInputDialog(null, "Seleccione el paciente\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsPaciente, optionsPaciente[0]);

        Object[] optionsMedico = medicoList.stream().map(Medico::getNombre).toArray();
        String selectedFilterMedico =
                (String) JOptionPane.showInputDialog(null, "Seleccione el medico\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsMedico, optionsMedico[0]);

        LocalDate fechaCita = LocalDate.parse(JOptionPane.showInputDialog(null, "Ingrese la fecha de la cita:(YYYY-MM-DD)"));
        LocalTime horaCita = LocalTime.parse(JOptionPane.showInputDialog(null, "Ingrese la hora de la cita: (HH:MM:SS)"));
        String motivo = JOptionPane.showInputDialog(null, "Ingrese el motivo de la cita: ");

        Paciente paciente = pacienteList.stream().filter(pacienteFilter -> pacienteFilter.getNombre().equals(selectedFilterPaciente)).findFirst().get();
        Medico medico = medicoList.stream().filter(medicoFilter -> medicoFilter.getNombre().equals(selectedFilterMedico)).findFirst().get();

        cita.setFechaCita(fechaCita);
        cita.setHoraCita(horaCita);
        cita.setMotivo(motivo);
        cita.setIdPaciente(paciente.getId());
        cita.setIdMedico(medico.getId());


        this.citaModel.create(cita);

    }
}
