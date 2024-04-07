package controller;

import entity.Paciente;
import model.PacienteModel;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class PacienteController {

    PacienteModel pacienteModel;

    public PacienteController(PacienteModel pacienteModel) {
        this.pacienteModel = new PacienteModel();
    }

    public void delete(){

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "Lista de pacientes: \n" + pacienteModel.findAll() + "\nIngrese el ID del paciente a eliminar"));

        this.pacienteModel.delete(id);

    }

    public void update(){

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "Lista de pacientes:\n" + pacienteModel.findAll() + "\nIngrese el ID del paciente a actualizar"));

        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del paciente: ");
        String apellido = JOptionPane.showInputDialog(null, "Ingrese el nuevo apellido del paciente: ");
        LocalDate fechaNacimiento = LocalDate.parse(JOptionPane.showInputDialog(null, "Ingrese la nueva fecha de nacimiento del paciente: (YYYY-MM-DD)"));
        String documentoIdentidad = JOptionPane.showInputDialog(null, "Ingrese el nuevo documento de identidad del paciente: (Tipo+numero)");

        Paciente paciente = new Paciente();

        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setFechaNacimiento(fechaNacimiento);
        paciente.setDocumentoIdentidad(documentoIdentidad);
        paciente.setId(id);

        this.pacienteModel.update(paciente);

        JOptionPane.showMessageDialog(null, "El paciente: \n" + nombre + "\nSe actualizo correctamente");

    }

    public void findByFilters(){

        String[] options = {"ID" ,"Nombre", "Apellido"};

        String selectedFilter = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de filtro\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        String valueFilter = JOptionPane.showInputDialog(null, "Ingrese el dato solicitado para la consulta\n" + "(ID del paciente, nombre del paciente, apellido del paciente)");

        List<Paciente> listPaciente = this.pacienteModel.findByFilter(selectedFilter, valueFilter);
    }

    public void findAll(){

        List<Paciente> listPaciente = this.pacienteModel.findAll();
        JOptionPane.showMessageDialog(null, "Lista de pacientes:\n" + listPaciente);
    }

    public void create(){

        Paciente paciente = new Paciente();

        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del paciente: ");
        String apellido = JOptionPane.showInputDialog(null, "Ingrese el apellido del paciente: ");
        LocalDate fechaNacimiento = LocalDate.parse(JOptionPane.showInputDialog(null, "Ingrese la fecha de nacimiento del paciente: (YYYY-MM-DD)"));
        String documentoIdentidad = JOptionPane.showInputDialog(null, "Ingrese el documento de identidad del paciente: (Tipo+numero)");

        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setFechaNacimiento(fechaNacimiento);
        paciente.setDocumentoIdentidad(documentoIdentidad);

         this.pacienteModel.create(paciente);

    }





}

