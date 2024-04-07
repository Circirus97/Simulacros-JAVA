package Controller;


import Entity.Flight;
import Entity.Passenger;
import Entity.Reservation;
import Entity.Seat;
import Model.FlightModel;
import Model.PassengerModel;
import Model.ReservationModel;
import Model.SeatModel;

import javax.swing.*;
import java.util.List;

public class ReservationController {

    ReservationModel reservationModel;

    PassengerModel passengerModel;

    FlightModel flightModel;

    SeatModel seatModel;

    public ReservationController(ReservationModel reservationModel, PassengerModel passengerModel, FlightModel flightModel, SeatModel seatModel) {
        this.reservationModel = reservationModel;
        this.passengerModel = passengerModel;
        this.flightModel = flightModel;
        this.seatModel = seatModel;
    }



    public void create() {

        Reservation reservation = new Reservation();

        List<Passenger> passengerList = passengerModel.findAll();
        List<Flight> flightList = flightModel.findAll();

        Object[] optionsPassenger = passengerList.stream().map(Passenger::getNamePassenger).toArray();
        String selectedFilterPassenger =
                (String) JOptionPane.showInputDialog(null, "Select passenger\n\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsPassenger, optionsPassenger[0]);

        Object[] optionsFlight = flightList.stream().map(Flight::getDestination).toArray();
        String selectedFilterFlight =
                (String) JOptionPane.showInputDialog(null, "Select destination\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsFlight, optionsFlight[0]);
        Flight flight = flightList.stream().filter(flightFilter -> flightFilter.getDestination().equals(selectedFilterFlight)).findFirst().get();

        Object[] optionsFlightDate = {flight.getDepartureDate()};
        String selectedFilterFlightDate =
                String.valueOf(JOptionPane.showInputDialog(null, "Select departure date\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsFlightDate, optionsFlightDate[0]));

        Object[] optionsFlightTime = {flight.getDepartureTime()};
        String selectedFilterFlightTime =
                String.valueOf(JOptionPane.showInputDialog(null, "Select departure time\n", "Filter", JOptionPane.QUESTION_MESSAGE, null, optionsFlightTime, optionsFlightTime[0]));

        Passenger passenger = passengerList.stream().filter(passengerFilter -> passengerFilter.getNamePassenger().equals(selectedFilterPassenger)).findFirst().get();

        List<Seat> seatList = seatModel.findAllByIdFlight(flight.getId());

        Object[] optionsSeat = seatList.stream().map(Seat::getSeatCode).toArray();
        Integer selectedFilterSeat =
                JOptionPane.showOptionDialog(null, "Select availability seat\n", "Filter", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionsSeat, optionsSeat[0]);

        Seat seat = seatList.get(selectedFilterSeat);


        reservation.setReservationDate(selectedFilterFlightDate);
        reservation.setReservationTime(selectedFilterFlightTime);
        reservation.setIdPassenger(passenger.getId());
        reservation.setIdFlight(flight.getId());
        reservation.setSeat(seat.getSeatCode());
        reservation.setIdSeat(seat.getId());


        this.reservationModel.create(reservation);

    }

}
