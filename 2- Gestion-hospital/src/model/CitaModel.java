package model;

import database.CitaCRUD;
import database.ConfigDB;
import entity.Cita;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CitaModel implements CitaCRUD {

    @Override
    public Cita create(Cita cita) {

        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "INSERT INTO Citas(id_paciente, id_medico, fecha_cita, hora_cita, motivo) VALUE (?, ?, ?, ?, ?);";

            PreparedStatement prepareCall = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            prepareCall.setInt(1, cita.getIdPaciente());
            prepareCall.setInt(2, cita.getIdMedico());
            prepareCall.setDate(3, Date.valueOf(cita.getFechaCita()));
            prepareCall.setTime(4, Time.valueOf(cita.getHoraCita()));
            prepareCall.setString(5, cita.getMotivo());

            prepareCall.execute();

            ResultSet result = prepareCall.getGeneratedKeys();

            while (result.next()) {
                cita.setId(result.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Cita agregado correctamente.\n" + cita);

            prepareCall.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error agregando la cita\n " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return cita;

    }

    @Override
    public List<Cita> findAll() {

        Connection connection = ConfigDB.openConnection();
        List<Cita> citaList = new ArrayList<>();

        try {

            String sql = "SELECT * FROM Citas;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            ResultSet result = prepareCall.executeQuery();


            while (result.next()) {

                Cita cita = new Cita();

                cita.setId(result.getInt("id"));
                cita.setFechaCita(result.getDate("fecha_cita").toLocalDate());
                cita.setHoraCita(result.getTime("hora_cita").toLocalTime());
                cita.setIdPaciente(result.getInt("id_paciente"));
                cita.setIdMedico(result.getInt("id_medico"));
                cita.setMotivo(result.getString("motivo"));


                citaList.add(cita);
            }

            prepareCall.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando la lista de citas " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return citaList;

    }

    @Override
    public List<Cita> findByFilter(String filter, String value) {

        String sql;

        List<Cita> citaList = new ArrayList<>();

        try {

            if (Objects.equals(filter, "ID")) {
                sql = "SELECT * FROM Citas WHERE id = ?;";

                citaList = findByFilterId(sql, value);

            }
            if (Objects.equals(filter, "Fecha")) {

                sql = "SELECT * FROM Citas WHERE fecha_cita = ?;";

                citaList = findByDate(sql, value);
            }
            if (Objects.equals(filter, "Hora")) {

                sql = "SELECT * FROM Citas WHERE hora_cita = ?;";

                citaList = findByTime(sql, value);
            }
            if (Objects.equals(filter, "Medico")) {

                sql = "SELECT * FROM Citas c JOIN Medicos m ON c.id_medico = m.id WHERE m.id = ?;";

                citaList = findByMedico(sql, value);
            }
            if (Objects.equals(filter, "Paciente")) {

                sql = "SELECT * FROM Citas c JOIN Pacientes p ON c.id_paciente = p.id WHERE p.id = ?;";

                citaList = findByPaciente(sql, value);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error aplicando filtro " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return citaList;
    }

    public List<Cita> findByFilterId(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Cita> citaList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Cita cita = new Cita();

                cita.setId(result.getInt("id"));
                cita.setIdPaciente(result.getInt("id_paciente"));
                cita.setIdMedico(result.getInt("id_medico"));
                cita.setFechaCita(result.getDate("fecha_cita").toLocalDate());
                cita.setHoraCita(result.getTime("hora_cita").toLocalTime());
                cita.setMotivo(result.getString("motivo"));

                citaList.add(cita);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, citaList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return citaList;

    }

    public List<Cita> findByDate(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Cita> citaList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setDate(1, Date.valueOf(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Cita cita = new Cita();

                cita.setId(result.getInt("id"));
                cita.setIdPaciente(result.getInt("id_paciente"));
                cita.setIdMedico(result.getInt("id_medico"));
                cita.setFechaCita(result.getDate("fecha_cita").toLocalDate());
                cita.setHoraCita(result.getTime("hora_cita").toLocalTime());
                cita.setMotivo(result.getString("motivo"));

                citaList.add(cita);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, citaList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return citaList;

    }

    public List<Cita> findByTime(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Cita> citaList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setTime(1, Time.valueOf(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Cita cita = new Cita();

                cita.setId(result.getInt("id"));
                cita.setIdPaciente(result.getInt("id_paciente"));
                cita.setIdMedico(result.getInt("id_medico"));
                cita.setFechaCita(result.getDate("fecha_cita").toLocalDate());
                cita.setHoraCita(result.getTime("hora_cita").toLocalTime());
                cita.setMotivo(result.getString("motivo"));

                citaList.add(cita);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, citaList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return citaList;

    }

    public List<Cita> findByMedico(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Cita> citaList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Cita cita = new Cita();

                cita.setId(result.getInt("id"));
                cita.setIdPaciente(result.getInt("id_paciente"));
                cita.setIdMedico(result.getInt("id_medico"));
                cita.setFechaCita(result.getDate("fecha_cita").toLocalDate());
                cita.setHoraCita(result.getTime("hora_cita").toLocalTime());
                cita.setMotivo(result.getString("motivo"));

                citaList.add(cita);
            }

            prepareCall.close();

            JOptionPane.showMessageDialog(null, citaList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return citaList;

    }

    public List<Cita> findByPaciente(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Cita> citaList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Cita cita = new Cita();

                cita.setId(result.getInt("id"));
                cita.setIdPaciente(result.getInt("id_paciente"));
                cita.setIdMedico(result.getInt("id_medico"));
                cita.setFechaCita(result.getDate("fecha_cita").toLocalDate());
                cita.setHoraCita(result.getTime("hora_cita").toLocalTime());
                cita.setMotivo(result.getString("motivo"));

                citaList.add(cita);
            }

            prepareCall.close();

            JOptionPane.showMessageDialog(null, citaList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return citaList;

    }

    @Override
    public void update(Cita cita) {

        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "UPDATE Citas SET fecha_cita =?, hora_cita =?, motivo = ? WHERE id =?;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);


            prepareCall.setDate(1, Date.valueOf(cita.getFechaCita()));
            prepareCall.setTime(2, Time.valueOf(cita.getHoraCita()));
            prepareCall.setString(3, cita.getMotivo());
            prepareCall.setInt(4, cita.getId());


            prepareCall.execute();

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Cita actualizado correctamente.\n" + cita);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error actualizando la cita\n " + e.getMessage());
        }

        ConfigDB.closeConnection();
    }

    @Override
    public void delete(Integer id) {

        Connection connection = ConfigDB.openConnection();

        try{
            String sqlCita = "DELETE FROM Citas WHERE id = ?;";

            PreparedStatement prepareCallCita = connection.prepareStatement(sqlCita);
            prepareCallCita.setInt(1, id);
            prepareCallCita.execute();
            prepareCallCita.close();

            JOptionPane.showMessageDialog(null, "Cita eliminada correctamente.\n");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error eliminando cita " + e.getMessage());
        }

        ConfigDB.closeConnection();

    }
}
