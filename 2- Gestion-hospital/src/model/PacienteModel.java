package model;

import database.ConfigDB;
import database.PacienteCRUD;
import entity.Paciente;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PacienteModel implements PacienteCRUD {


    @Override
    public Paciente create(Paciente paciente) {

        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "INSERT INTO Pacientes(nombre, apellido, fecha_nacimiento, documento_identidad) VALUES (?, ?, ?, ?);";

            PreparedStatement prepareCall = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            prepareCall.setString(1, paciente.getNombre());
            prepareCall.setString(2, paciente.getApellido());
            prepareCall.setDate(3, Date.valueOf((LocalDate) paciente.getFechaNacimiento()));
            prepareCall.setString(4, paciente.getDocumentoIdentidad());

            prepareCall.execute();

            ResultSet result = prepareCall.getGeneratedKeys();
            while (result.next()) {
                paciente.setId(result.getInt(1));
            }


            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Paciente agregado correctamente.\n" + paciente);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error agregando el paciente\n " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return paciente;
    }

    @Override
    public List<Paciente> findAll() {

        Connection connection = ConfigDB.openConnection();
        List<Paciente> pacienteList = new ArrayList<>();

        try {

            String sql = "SELECT * FROM Pacientes;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            ResultSet objResult = prepareCall.executeQuery();


            while (objResult.next()) {

                Paciente paciente = new Paciente();


                paciente.setId(objResult.getInt("id"));
                paciente.setNombre(objResult.getString("nombre"));
                paciente.setApellido(objResult.getString("apellido"));
                paciente.setDocumentoIdentidad(objResult.getString("documento_identidad"));
                paciente.setFechaNacimiento(objResult.getDate("fecha_nacimiento").toLocalDate());

                pacienteList.add(paciente);
            }

            prepareCall.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando la lista de paciente " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return pacienteList;

    }

    @Override
    public List<Paciente> findByFilter(String filter, String value) {

        Connection connection = ConfigDB.openConnection();

        String sql;

        List<Paciente> pacienteList = new ArrayList<>();

        try {

            if (Objects.equals(filter, "ID")) {
                sql = "SELECT * FROM Pacientes WHERE id = ?;";

                pacienteList = findByFilterId(sql, value);

            }
            if (Objects.equals(filter, "Nombre")) {

                sql = "SELECT * FROM Pacientes WHERE nombre LIKE ?;";

                pacienteList = findByName(sql, value);
            }
            if (Objects.equals(filter, "Apellido")) {

                sql = "SELECT * FROM Pacientes WHERE apellido LIKE ?;";

                pacienteList = findByLastName(sql, value);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error aplicando filtro " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return pacienteList;
    }

    public List<Paciente> findByFilterId(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Paciente> pacienteList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Paciente paciente = new Paciente();

                paciente.setId(objResult.getInt("id"));
                paciente.setNombre(objResult.getString("nombre"));
                paciente.setApellido(objResult.getString("apellido"));
                paciente.setFechaNacimiento(objResult.getDate("fecha_nacimiento").toLocalDate());
                paciente.setDocumentoIdentidad(objResult.getString("documento_identidad"));

                pacienteList.add(paciente);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de pacientes:\n" + pacienteList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return pacienteList;

    }

    private List<Paciente> findByName(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Paciente> pacienteList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Paciente paciente = new Paciente();

                paciente.setId(objResult.getInt("id"));
                paciente.setNombre(objResult.getString("nombre"));
                paciente.setApellido(objResult.getString("apellido"));
                paciente.setFechaNacimiento(objResult.getDate("fecha_nacimiento").toLocalDate());
                paciente.setDocumentoIdentidad(objResult.getString("documento_identidad"));

                pacienteList.add(paciente);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de pacientes:\n" + pacienteList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros " + e.getMessage());

        }

        return pacienteList;

    }

    private List<Paciente> findByLastName(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Paciente> pacienteList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet objResult = prepareCall.executeQuery();

            while (objResult.next()) {

                Paciente paciente = new Paciente();

                paciente.setId(objResult.getInt("id"));
                paciente.setNombre(objResult.getString("nombre"));
                paciente.setApellido(objResult.getString("apellido"));
                paciente.setFechaNacimiento(objResult.getDate("fecha_nacimiento").toLocalDate());
                paciente.setDocumentoIdentidad(objResult.getString("documento_identidad"));

                pacienteList.add(paciente);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de pacientes:\n" + pacienteList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros " + e.getMessage());

        }

        return pacienteList;

    }

    @Override
    public void update(Paciente paciente) {

        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "UPDATE Pacientes SET nombre =?, apellido =?, fecha_nacimiento =?, documento_identidad =? WHERE id =?;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            prepareCall.setString(1, paciente.getNombre());
            prepareCall.setString(2, paciente.getApellido());
            prepareCall.setDate(3, Date.valueOf(paciente.getFechaNacimiento()));
            prepareCall.setString(4, paciente.getDocumentoIdentidad());
            prepareCall.setInt(5, paciente.getId());

            prepareCall.execute();

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Paciente actualizado correctamente.\n" + paciente);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error actualizando el paciente\n " + e.getMessage());
        }

        ConfigDB.closeConnection();

    }

    @Override
    public void delete(Integer id) {

        Connection connection = ConfigDB.openConnection();

        try{
            String sqlCita = "DELETE FROM Citas WHERE id_paciente = ?;";

            PreparedStatement prepareCallCita = connection.prepareStatement(sqlCita);
            prepareCallCita.setInt(1, id);
            prepareCallCita.execute();
            prepareCallCita.close();

            ////////////////////////////////////////////////////////////////////////////////////////

            String sqlPaciente = "DELETE FROM Pacientes WHERE id = ?;";

            PreparedStatement prepareCallPaciente = connection.prepareStatement(sqlPaciente);
            prepareCallPaciente.setInt(1, id);
            prepareCallPaciente.execute();
            prepareCallPaciente.close();


            JOptionPane.showMessageDialog(null, "Paciente eliminado correctamente.\n");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error eliminando paciente " + e.getMessage());
        }

        ConfigDB.closeConnection();

    }

}
