package usecase.practicas_2019;

import java.util.HashMap;
import java.util.Map;

public class Flight {
    private String company;
    private int flightCode;
    private int hours;
    private int minutes;
    private int year;
    private int month;
    private int day;
    private String origin;
    private String destination;
    private int capacity;
    private int delay;
    private Map<String, String> properties;

    public Flight(){
        properties = new HashMap<>();
    }

    public FlightId getId() {
        return new FlightId(company, flightCode, year, month, day);
    }

    public void setTime(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(int flightCode){
        this.flightCode = flightCode;
    }

    public void setDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setProperty(String attribute, String value) {
        properties.put(attribute, value);
    }

    public String getProperty(String attribute) {
        return properties.get(attribute);
    }

    public Iterable<String> getAllAttributes() {
        return properties.keySet();
    }
}
