package database;

import entity.Cita;

import java.util.List;

public interface CitaCRUD {

    Cita create (Cita cita);

    List<Cita> findAll();

    List<Cita> findByFilter(String filter, String value);

    void update(Cita cita);

    void delete(Integer id);

}
