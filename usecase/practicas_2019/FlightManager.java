package usecase.practicas_2019;


import usecase.practicas_2019.Passenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightManager {
    private Map<FlightId, List<Passenger>> passengers;
    private Map<FlightId, Flight> flights;

    public FlightManager() {
        passengers = new HashMap<>();
        flights = new HashMap<>();
    }

    public Flight addFlight(String company, int flightCode, int year, int month, int day) {
        Flight flight = new Flight();
        flight.setCompany(company);
        flight.setFlightCode(flightCode);
        flight.setDate(year, month, day);

        if (flights.containsKey(flight.getId())) {
            throw new RuntimeException("The flight already exists.");
        }

        flights.put(flight.getId(), flight);
        return flight;
    }

    public Flight getFlight(String company, int flightCode, int year, int month, int day) {
        FlightId id = new FlightId(company, flightCode, year, month, day);
        if (!flights.containsKey(id)) {
            throw new RuntimeException("Flight not found.");
        }
        return flights.get(id);
    }

    public void updateFlight(String company, int flightCode, int year, int month, int day, Flight updatedFlightInfo) {
        FlightId id = new FlightId(company, flightCode, year, month, day);
        if (!flights.containsKey(id)) {
            throw new RuntimeException("The flight doesn't exists and can't be updated.");
        }
        if (flights.containsKey(updatedFlightInfo.getId()) && !flights.get(updatedFlightInfo.getId()).equals(flights.get(id))) {
            throw new RuntimeException("The new flight identifiers are already in use.");
        }
        flights.remove(id);
        flights.put(updatedFlightInfo.getId(), updatedFlightInfo);
    }

    public void addPassenger(String dni, String name, String surname, Flight flight) {
        if (!flights.containsKey(flight.getId())) {
            throw new RuntimeException("The flight doesn't exist.");
        }

        Passenger passenger = new Passenger();
        passenger.setDNI(dni);
        passenger.setName(name);
        passenger.setSurname(surname);

        List<Passenger> passengerList = passengers.get(flight.getId());
        if (passengerList == null) {
            passengerList = new ArrayList<>();
            passengers.put(flight.getId(), passengerList);
        }

        if (passengerList.size() <= flight.getCapacity()) {
            passengerList.add(passenger);
            passenger.addFlight(flight);
        } else {
            throw new RuntimeException("This flight doesn't have capacity for more passengers.");
        }
    }


    public Iterable<Passenger> getPassengers(String company, int flightCode, int year, int month, int day) {
        FlightId id = new FlightId(company, flightCode, year, month, day);
        if (!flights.containsKey(id)) {
            throw new RuntimeException("The flight doesn't exist.");
        }

        List<Passenger> passengerList = passengers.get(id);
        if (passengerList == null) {
            passengerList = new ArrayList<>();
        }
        return passengerList;
    }

    // O se recorre el mapa o se implementa otro mapa de listas de vuelos con las fechas como clave
    public Iterable<Flight> flightsByDate(int year, int month, int day) {
        List<Flight> list = new ArrayList<>();
        for (Flight f : flights.values()) {
            if (f.getYear() == year && f.getMonth() == month && f.getDay() == day) {
                list.add(f);
            }
        }
        return list;
    }

    public Iterable<Flight> getFlightsByPassenger(Passenger passenger) {
        if (passenger == null) {
            return null;
        } else {
            return passenger.getFlights();
        }
    }

    public Iterable<Flight> getFlightsByDestination(String destination, int year, int month, int day) {
        List<Flight> list = new ArrayList<>();
        for (Flight f : flights.values()) {
            if (f.getDestination().equals(destination) && f.getYear() == year && f.getMonth() == month && f.getDay() == day) {
                list.add(f);
            }
        }
        return list;
    }

}