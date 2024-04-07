package database;

import entity.Medico;

import java.util.List;

public interface MedicoCRUD {

    Medico create(Medico medico);

    List<Medico> findAll();

    List<Medico> findByFilter(String filter, String value);

    void update(Medico medico);

    void delete(Integer id);
}
