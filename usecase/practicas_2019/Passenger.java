package usecase.practicas_2019;


import java.util.ArrayList;
import java.util.List;

public class Passenger {
    private String dni;
    private String name;
    private String surname;
    private List<Flight> flights;

    public Passenger() {
        flights = new ArrayList<>();
    }

    public String getDNI() {
        return dni;
    }

    public void setDNI(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }
}
