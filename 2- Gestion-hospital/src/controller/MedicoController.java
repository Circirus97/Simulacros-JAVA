package controller;

import entity.Especialidad;
import entity.Medico;
import model.EspecialidadModel;
import model.MedicoModel;
import model.PacienteModel;

import javax.swing.*;
import java.util.List;

public class MedicoController {

    MedicoModel medicoModel;
    EspecialidadModel especialidadModel;
    PacienteModel pacienteModel;

    public MedicoController(MedicoModel medicoModel, EspecialidadModel especialidadModel, PacienteModel pacienteModel){
        this.medicoModel = new MedicoModel();
        this.especialidadModel = new EspecialidadModel();
        this.pacienteModel = new PacienteModel();
    }

    public void delete(){

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "Lista de medicos: \n" + medicoModel.findAll() + "\nIngrese el ID del medico a eliminar"));

        this.medicoModel.delete(id);

    }

    public void update(){

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "Lista de medicos:\n" + medicoModel.findAll() + "\nIngrese el ID del medico a actualizar"));

        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del medico: ");
        String apellido = JOptionPane.showInputDialog(null, "Ingrese el nuevo apellido del medico: ");

        Medico medico = new Medico();

        medico.setNombre(nombre);
        medico.setApellido(apellido);
        medico.setId(id);

        this.medicoModel.update(medico);

        JOptionPane.showMessageDialog(null, "El medico: \n" + nombre + "\nSe actualizo correctamente");

    }

    public void findByFilters(){

        String[] options = {"ID" ,"Nombre", "Apellido", "Especialidad"};

        String selectedFilter = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de filtro\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        String valueFilter = JOptionPane.showInputDialog(null, "Ingrese el dato solicitado para la consulta\n" + "(ID del medico, nombre del medico, apellido del medico o ID especialidad)");

        List<Medico> medicoList = this.medicoModel.findByFilter(selectedFilter, valueFilter);


    }

    public void findAll(){

        List<Medico> medicoList = this.medicoModel.findAll();
        JOptionPane.showMessageDialog(null, "Lista de medicos:\n" + medicoList);
    }

    public void create(){

        Medico medico = new Medico();

        List<Especialidad> especialidadList = especialidadModel.findAll();

        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del medico: ");
        String apellido = JOptionPane.showInputDialog(null, "Ingrese el apellido del medico: ");

        Object[] options = especialidadList.stream().map(Especialidad::getNombre).toArray();
        String selectedFilter = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de especializacion\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        Especialidad especialidad =  especialidadList.stream().filter(especialidadFilter -> especialidadFilter.getNombre().equals(selectedFilter)).findFirst().get();



        medico.setNombre(nombre);
        medico.setApellido(apellido);
        medico.setId_especialidad(especialidad.getId());
        medico.setEspecialidad(especialidad);


        this.medicoModel.create(medico);

    }

}
