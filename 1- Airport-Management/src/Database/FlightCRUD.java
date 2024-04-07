package Database;

import Entity.Flight;

import java.util.List;

public interface FlightCRUD {


    Flight create(Flight flight);

    List<Flight> findAll();

    List<Flight> findByFilter(String filter, String value);

    void update(Flight flight);

    void delete(Integer id);

}

