package entity;

public class Location extends Entity{
    private Long id_location;
    private String city;

    public Location(Long id_location, String city) {
        this.id_location = id_location;
        this.city = city;
    }

    public Location(String city) {
        this.city = city;
    }

    public Location() {
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId_location() {
        return id_location;
    }

    public String getCity() {
        return city;
    }

    public void setId_location(Long id_location) {
        this.id_location = id_location;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id_location=" + id_location +
                ", city='" + city + '\'' +
                '}';
    }
}
