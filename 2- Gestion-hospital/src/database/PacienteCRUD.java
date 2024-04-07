package database;

import entity.Paciente;

import java.util.List;

public interface PacienteCRUD {

    Paciente create(Paciente paciente);

    List<Paciente> findByFilter(String filter, String value);

    void update(Paciente paciente);

    void delete(Integer id);

    List<Paciente> findAll();
}
