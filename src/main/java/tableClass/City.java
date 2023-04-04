package tableClass;

public class City {

    private final int cityId;
    private final String city;

    public City(int cityId, String city) {
        this.cityId = cityId;
        this.city = city;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Город проживания " + city + ".";
    }
}
