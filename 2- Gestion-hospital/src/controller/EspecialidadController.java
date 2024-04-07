package controller;

import entity.Especialidad;
import model.EspecialidadModel;

import javax.swing.*;
import java.util.List;

public class EspecialidadController {

    EspecialidadModel especialidadModel;

    public EspecialidadController(EspecialidadModel especialidadModel) {
        this.especialidadModel = new EspecialidadModel();
    }

    public void delete(){

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "Lista de pacientes: \n" + especialidadModel.findAll() + "\nIngrese el ID de la especialidad a eliminar"));

        this.especialidadModel.delete(id);

    }

    public void update(){

        Integer id = Integer.valueOf(JOptionPane.showInputDialog(null, "Lista de especialidades:\n" + especialidadModel.findAll() + "\nIngrese el ID de la especialidad a actualizar"));

        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre de la especialidad: ");
        String descripcion = JOptionPane.showInputDialog(null, "Ingrese la descripcion de la especialidad: ");

        Especialidad especialidad = new Especialidad();

        especialidad.setNombre(nombre);
        especialidad.setDescripcion(descripcion);
        especialidad.setId(id);

        this.especialidadModel.update(especialidad);

    }

    public void findByFilter(){

        String[] options = {"ID" ,"Nombre"};

        String selectedFilter = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de filtro\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        String valueFilter = JOptionPane.showInputDialog(null, "Ingrese el dato solicitado para la consulta\n" + "(ID de la especialidad o  nombre de la especialidad)");

        List<Especialidad> especialidadList = this.especialidadModel.findByFilter(selectedFilter, valueFilter);
    }

    public void findAll(){

        List<Especialidad> especialidadList = this.especialidadModel.findAll();
        JOptionPane.showMessageDialog(null, "Lista de especialidades:\n" + especialidadList);

    }

    public void create(){

        Especialidad especialidad = new Especialidad();

        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre de la especialidad: ");
        String descripcion = JOptionPane.showInputDialog(null, "Ingrese la descripcion de la especialidad: ");


        especialidad.setNombre(nombre);
        especialidad.setDescripcion(descripcion);


        this.especialidadModel.create(especialidad);

    }


}
