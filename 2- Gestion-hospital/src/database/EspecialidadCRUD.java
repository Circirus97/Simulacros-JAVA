package database;

import entity.Especialidad;

import java.util.List;

public interface EspecialidadCRUD {

    Especialidad create(Especialidad especialidad);

    List<Especialidad> findAll();

    List<Especialidad> findByFilter(String filter, String value);

    void update(Especialidad especialidad);

    void delete(Integer id);
}
