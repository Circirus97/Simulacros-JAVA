import controller.CitaController;
import controller.EspecialidadController;
import controller.MedicoController;
import controller.PacienteController;
import database.ConfigDB;
import model.CitaModel;
import model.EspecialidadModel;
import model.MedicoModel;
import model.PacienteModel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        ConfigDB.openConnection();

        PacienteModel pacienteModel = new PacienteModel();
        PacienteController pacienteController = new PacienteController(pacienteModel);

        MedicoModel medicoModel = new MedicoModel();


        EspecialidadModel especialidadModel = new EspecialidadModel();
        EspecialidadController especialidadController = new EspecialidadController(especialidadModel);
        MedicoController medicoController = new MedicoController(medicoModel, especialidadModel, pacienteModel);

        CitaModel citaModel = new CitaModel();
        CitaController citaController = new CitaController(citaModel, medicoModel, pacienteModel);


        String option;

        do {
            option = JOptionPane.showInputDialog(""" 
                    Bienvenido al sistema de administracion medica.
                                        
                    Seleccione una opcion
                                        
                    1. Administrador de pacientes.
                    2. Administrador de medicos.
                    3. Administrador de especialidades.
                    4. Administrador de citas.
                    5. Salir.
                    """);

            
            switch (option) {
                case "1":
                    do {
                        option = JOptionPane.showInputDialog("""
                                Administrador de pacientes
                                                                
                                Seleccione una opcion:   
                                1. Agregar un nuevo paciente.
                                2. Filtrar pacientes.
                                3. Actualizar informacion del paciente.
                                4. Eliminar paciente.
                                5. Mostrar todos los pacientes
                                6. Salir.
                                """);
                        switch (option) {
                            case "1":
                                pacienteController.create();
                                break;
                            case "2":
                                pacienteController.findByFilters();
                                break;
                            case "3":
                                pacienteController.update();
                                break;
                            case "4":
                                pacienteController.delete();
                                break;
                            case "5":
                                pacienteController.findAll();
                                break;
                        }
                    } while (!option.equals("6"));
                    break;
                case "2":
                    do {
                        option = JOptionPane.showInputDialog("""
                                Administrador de Medicos
                                                                
                                Seleccione una opcion:       

                                    1. Agregar un nuevo medico.
                                    2. Filtrar Medico.
                                    3. Actualizar informacion del medico.
                                    4. Eliminar Medico.
                                    5. Mostrar todos lo medicos.
                                    6. Salir.
                                """);
                        switch (option) {
                            case "1":
                                medicoController.create();
                                break;
                            case "2":
                                medicoController.findByFilters();
                                break;
                            case "3":
                                medicoController.update();
                                break;
                            case "4":
                                medicoController.delete();
                                break;
                            case "5":
                                medicoController.findAll();
                                break;
                        }
                    } while (!option.equals("6"));
                    break;
                case "3":
                    do {
                        option = JOptionPane.showInputDialog("""
                                Administrador de Especialidades
                                                                
                                Seleccione una opcion:       
                                    1. Agregar una nueva especialidad.
                                    2. Filtrar especialidades.
                                    3. Actualizar informacion de la especialidad.
                                    4. Eliminar especialidad.
                                    5. Mostrar todas las especialidades.
                                    6. Salir.
                                """);
                        switch (option) {
                            case "1":
                                especialidadController.create();
                                break;
                            case "2":
                                especialidadController.findByFilter();
                                break;
                            case "3":
                                especialidadController.update();
                                break;
                            case "4":
                                especialidadController.delete();
                                break;
                            case "5":
                                especialidadController.findAll();
                                break;
                        }
                    } while (!option.equals("6"));
                    break;
                case "4":
                    do {
                        option = JOptionPane.showInputDialog("""
                                Administrador de Citas
                                                                
                                Seleccione una opcion
                                                                
                                    1. Agregar una nueva cita.
                                    2. Filtrar citas.
                                    3. Actualizar informacion de la cita.
                                    4. Eliminar cita.
                                    5. Mostrar todas las citas.
                                    6. Salir.
                                """);
                        switch (option) {
                            case "1":
                                citaController.create();
                                break;
                            case "2":
                                citaController.findByFilters();
                                break;
                            case "3":
                                citaController.update();
                                break;
                            case "4":
                                citaController.delete();
                                break;
                            case "5":
                                citaController.findAll();
                                break;
                        }
                    } while (!option.equals("6"));
                    break;
            }
        } while (!option.equals("5"));

    }
}