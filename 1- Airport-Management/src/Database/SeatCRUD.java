package Database;

import Entity.Seat;

import java.util.List;

public interface SeatCRUD {


    Seat create(Seat seat);

    List<Seat> findAll();

    List<Seat> findByFilter(String filter, String value);

    void update(Seat seat);

    void delete(Integer id);

}

