package model;

import database.ConfigDB;
import database.EspecialidadCRUD;
import entity.Especialidad;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EspecialidadModel implements EspecialidadCRUD {

    @Override
    public Especialidad create(Especialidad especialidad) {

        Connection connection = ConfigDB.openConnection();

        try{

            String sql = "INSERT INTO Especialidades(nombre, descripcion) VALUE (?, ?);";

            PreparedStatement prepareCall = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            prepareCall.setString(1, especialidad.getNombre());
            prepareCall.setString(2, especialidad.getDescripcion());

            prepareCall.execute();

            ResultSet result = prepareCall.getGeneratedKeys();

            while (result.next()){
                especialidad.setId(result.getInt(1));
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Especialidad agregada correctamente.\n" + especialidad);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error agregando la especialidad\n " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return especialidad;
    }

    @Override
    public List<Especialidad> findAll() {

        Connection connection = ConfigDB.openConnection();
        List<Especialidad> especialidadList = new ArrayList<>();

        try {

            String sql = "SELECT * FROM Especialidades;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            ResultSet objResult = prepareCall.executeQuery();


            while (objResult.next()) {

                Especialidad especialidad = new Especialidad();


                especialidad.setId(objResult.getInt("id"));
                especialidad.setDescripcion(objResult.getString("descripcion"));
                especialidad.setNombre(objResult.getString("nombre"));


                especialidadList.add(especialidad);
            }

            prepareCall.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando la lista de especialidades " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return especialidadList;

    }

    @Override
    public List<Especialidad> findByFilter(String filter, String value) {


        String sql;

        List<Especialidad> especialidadList = new ArrayList<>();

        try {

            if (Objects.equals(filter, "ID")) {
                sql = "SELECT * FROM Especialidades WHERE id = ?;";

                especialidadList = findByFilterId(sql, value);

            }
            if (Objects.equals(filter, "Nombre")) {

                sql = "SELECT * FROM Especialidades WHERE nombre LIKE ?;";

                especialidadList = findByName(sql, value);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error mostrando la lista de especialidades " + e.getMessage());
        }


        ConfigDB.closeConnection();
        return especialidadList;
    }

    public List<Especialidad> findByFilterId(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Especialidad> especialidadList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setInt(1, Integer.parseInt(value));
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Especialidad especialidad = new Especialidad();

                especialidad.setId(result.getInt("id"));
                especialidad.setNombre(result.getString("nombre"));
                especialidad.setDescripcion(result.getString("descripcion"));


                especialidadList.add(especialidad);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de pacientes:\n" + especialidadList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros  " + e.getMessage());
        }

        return especialidadList;

    }

    private List<Especialidad> findByName(String sql, String value) {

        Connection connection = ConfigDB.openConnection();
        List<Especialidad> especialidadList = new ArrayList<>();

        try {
            PreparedStatement prepareCall = connection.prepareStatement(sql);
            prepareCall.setString(1, "%" + value + "%");
            ResultSet result = prepareCall.executeQuery();

            while (result.next()) {

                Especialidad especialidad = new Especialidad();

                especialidad.setId(result.getInt("id"));
                especialidad.setNombre(result.getString("nombre"));
                especialidad.setDescripcion(result.getString("descripcion"));



                especialidadList.add(especialidad);
            }

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Lista de pacientes:\n" + especialidadList);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mostrando filtros " + e.getMessage());

        }

        return especialidadList;

    }

    @Override
    public void update(Especialidad especialidad) {

        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "UPDATE Especialidades SET nombre = ?, descripcion = ? WHERE id = ?;";

            PreparedStatement prepareCall = connection.prepareStatement(sql);

            prepareCall.setString(1, especialidad.getNombre());
            prepareCall.setString(2, especialidad.getDescripcion());
            prepareCall.setInt(3, especialidad.getId());


            prepareCall.execute();

            prepareCall.close();
            JOptionPane.showMessageDialog(null, "Especialidad actualizada correctamente.\n" + especialidad);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error actualizando la especialidad\n " + e.getMessage());
        }

        ConfigDB.closeConnection();
    }

    @Override
    public void delete(Integer id) {

        Connection connection = ConfigDB.openConnection();

        try{
            String sqlMedicos = "DELETE FROM Medicos WHERE id_especialidad  = ?;";

            PreparedStatement prepareCallMedicos = connection.prepareStatement(sqlMedicos);
            prepareCallMedicos.setInt(1, id);
            prepareCallMedicos.execute();
            prepareCallMedicos.close();

            ////////////////////////////////////////////////////////////////////////////////////////

            String sqlEspecialidad = "DELETE FROM Especialidades WHERE id = ?;";

            PreparedStatement prepareCallEspecialidad = connection.prepareStatement(sqlEspecialidad);
            prepareCallEspecialidad.setInt(1, id);
            prepareCallEspecialidad.execute();
            prepareCallEspecialidad.close();


            JOptionPane.showMessageDialog(null, "Especialidad eliminada correctamente.\n");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error eliminando especialidad " + e.getMessage());
        }

        ConfigDB.closeConnection();

    }


}
