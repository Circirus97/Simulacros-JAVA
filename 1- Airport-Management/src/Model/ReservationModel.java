package Model;

import Database.ConfigDB;
import Database.ReservationCRUD;
import Entity.Reservation;
import Entity.Seat;

import javax.swing.*;
import java.sql.*;
import java.util.List;

public class ReservationModel implements ReservationCRUD {


    SeatModel seatModel = new SeatModel();


    @Override
    public Reservation create(Reservation reservation) {
        Connection connection = ConfigDB.openConnection();

        try {

            String sql = "INSERT INTO reservations(id_passengers, id_flight, reservation_date, reservation_time, seat) VALUE ( ?, ?, ?, ?, ?);";

            PreparedStatement prepareCall = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            prepareCall.setInt(1, reservation.getIdPassenger());
            prepareCall.setInt(2, reservation.getIdFlight());
            prepareCall.setDate(3, Date.valueOf(reservation.getReservationDate()));
            prepareCall.setTime(4, Time.valueOf(reservation.getReservationTime().concat(":00")));
            prepareCall.setString(5, reservation.getSeat());

            prepareCall.execute();

            ResultSet result = prepareCall.getGeneratedKeys();

            while (result.next()) {
                reservation.setId(result.getInt(1));
            }

            Seat seat = new Seat();
            seat.setId(reservation.getIdSeat());
            seat.setAvailability(Boolean.FALSE);

            seatModel.update(seat);

            JOptionPane.showMessageDialog(null, "Reserve created correctly\n" + reservation);

            prepareCall.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error creating reservation\n " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return reservation;
    }

    @Override
    public List<Reservation> findAll() {
        return null;
    }

    @Override
    public List<Reservation> findByFilter(String filter, String value) {
        return null;
    }

    @Override
    public void update(Reservation reservation) {

    }

    @Override
    public void delete(Integer id) {

    }
}
