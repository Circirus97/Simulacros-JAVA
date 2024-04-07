package model;

import database.ConfigDB;
import database.MedicoCRUD;
import entity.Medico;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MedicoModel implements MedicoCRUD{

    @Override
    public Medico create(Medico medico) {

        Connection connection = ConfigDB.openConnection();

        try{

            String sql = "INSERT INTO Medicos(nombre, apellido, id_especialidad) VALUE (?, ?, ?);";

            PreparedStatement prepareCall = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            prepareCall.setString(1, medico.getNombre());
            prepareCall.setString(2, medico.getApellido());
            prepareCall.setInt(3, medico.getId_especialidad());

            prepareCall.execute();

            ResultSet result = prepareCall.getGeneratedKeys();

            while (result.next()){
                medico.setId(result.getInt(1));
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Medico agregado correctamente.\n" + medico);


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error agregando el medico\n " + e.getMessage());
        }

        medico.setEspecialidad(medico.getEspecialidad());
        ConfigDB.closeConnection();
        return medico;
    }

    @Override
    public List<Medico> findAll() {

        Connection connection = ConfigDB.openConnection();
        List<Medico> medicoList = new ArrayList<>();

        try {

            String sql = "SELECT * FROM Medicos;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            ResultSet objResult = prepareCall.executeQuery();


            while (objResult.next()) {

                Medico medico = new Medico();


                medico.setId(objResult.getInt("id"));
                medico.setNombre(objResult.getString("nombre"));
                medico.setApellido(objResult.getString("apellido"));
                medico.setId_especialidad(objResult.getInt("id_especialidad"));


                medicoList.add(medico);
            }

            prepareCall.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando la lista de medicos " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return medicoList;

    }

    @Override
    public List<Medico> findByFilter(String filter, String value) {

        String sql;

        List<Medico> medicoList = new ArrayList<>();

        try {

            if (Objects.equals(filter, "ID")) {
                sql = "SELECT * FROM Medicos WHERE id = ?;";

                medicoList = findByFilterId(sql, value);

            }
            if (Objects.equals(filter, "Nombre")) {

                sql = "SELECT * FROM Medicos WHERE nombre LIKE ?;";

                medicoList = findByName(sql, value);
            }
            if (Objects.equals(filter, "Apellido")) {

                sql = "SELECT * FROM Medicos WHERE apellido LIKE ?;";

                medicoList = findByLastName(sql, value);
            }
            if (Objects.equals(filter, "Especialidad")) {

                sql = "SELECT * FROM Medicos m JOIN Especialidades e ON m.id_especialidad = e.id WHERE e.nombre LIKE ?;";

                medicoList = findByIdEspecialidad(sql, value);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error aplicando filtro " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return medicoList;

    }

    public List<Medico> findByFilterId(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Medico> medicoList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Medico medico = new Medico();

                medico.setId(result.getInt("id"));
                medico.setNombre(result.getString("nombre"));
                medico.setApellido(result.getString("apellido"));
                medico.setId_especialidad(result.getInt("id_especialidad"));

                medicoList.add(medico);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, medicoList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return medicoList;

    }
    private List<Medico> findByName(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Medico> medicoList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Medico medico = new Medico();

                medico.setId(result.getInt("id"));
                medico.setNombre(result.getString("nombre"));
                medico.setApellido(result.getString("apellido"));
                medico.setId_especialidad(result.getInt("id_especialidad"));

                medicoList.add(medico);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, medicoList);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros " + e.getMessage());

        }

        return medicoList;

    }

    private List<Medico> findByLastName(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Medico> medicoList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Medico medico = new Medico();

                medico.setId(result.getInt("id"));
                medico.setNombre(result.getString("nombre"));
                medico.setApellido(result.getString("apellido"));
                medico.setId_especialidad(result.getInt("id_especialidad"));


                medicoList.add(medico);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, medicoList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros " + e.getMessage());

        }

        return medicoList;

    }

    public List<Medico> findByIdEspecialidad(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Medico> medicoList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Medico medico = new Medico();

                medico.setId(result.getInt("id"));
                medico.setNombre(result.getString("nombre"));
                medico.setApellido(result.getString("apellido"));
                medico.setId_especialidad(result.getInt("id_especialidad"));

                medicoList.add(medico);
            }

            prepareCall.close();

            JOptionPane.showMessageDialog(null, medicoList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return medicoList;

    }

    @Override
    public void update(Medico medico) {

        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "UPDATE Medicos SET nombre =?, apellido =? WHERE id =?;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            prepareCall.setString(1, medico.getNombre());
            prepareCall.setString(2, medico.getApellido());
            prepareCall.setInt(3, medico.getId());

            prepareCall.execute();

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Medico actualizado correctamente.\n" + medico);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error actualizando el medico\n " + e.getMessage());
        }

        ConfigDB.closeConnection();

    }

    @Override
    public void delete(Integer id) {

        Connection connection = ConfigDB.openConnection();

        try{
            String sqlCita = "DELETE FROM Citas WHERE id_medico = ?;";

            PreparedStatement prepareCallCita = connection.prepareStatement(sqlCita);
            prepareCallCita.setInt(1, id);
            prepareCallCita.execute();
            prepareCallCita.close();

            ////////////////////////////////////////////////////////////////////////////////////////

            String sql = "DELETE FROM Medicos WHERE id = ?;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, id);
            prepareCall.execute();
            prepareCall.close();


            JOptionPane.showMessageDialog(null, "Medico eliminado correctamente.\n");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error eliminando medico " + e.getMessage());
        }

        ConfigDB.closeConnection();



    }
}
