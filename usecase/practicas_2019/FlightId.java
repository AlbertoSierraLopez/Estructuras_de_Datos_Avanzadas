package usecase.practicas_2019;

public class FlightId implements Comparable<FlightId> {
    private String company;
    private int flightCode;
    private int year;
    private int month;
    private int day;

    public FlightId(String company, int flightCode, int year, int month, int day) {
        this.company = company;
        this.flightCode = flightCode;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return company + " " + flightCode+ " (" + year + "/" + month + "/" + day + ")";
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FlightId)) {
            return false;
        }
        FlightId f;
        try {
            f = (FlightId) obj;
        } catch (ClassCastException e) {
            return false;
        }
        return this.compareTo(f) == 0;
    }

    @Override
    public int compareTo(FlightId o) {
        int comparation = company.compareTo(o.company);
        if (comparation == 0) {
            comparation = Integer.compare(flightCode, o.flightCode);
        }
        if (comparation == 0) {
            comparation = Integer.compare(year, o.year);
        }
        if (comparation == 0) {
            comparation = Integer.compare(month, o.month);
        }
        if (comparation == 0) {
            comparation = Integer.compare(day, o.day);
        }
        return comparation;
    }
}
