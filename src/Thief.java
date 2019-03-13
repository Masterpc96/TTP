import java.util.ArrayList;

public class Thief {
    private double distance = 0;
    ArrayList<City> cities;

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public void evaluate() {
        City start;
        City stop;

        for (int i = 0; i < cities.size() - 1; i++) {
            start = cities.get(i);
            stop = cities.get(i + 1);
            distance += calculateDistance(start, stop);
        }
    }

    public double getDistance() {
        return distance;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    private double calculateDistance(City start, City stop) {
        return Math.sqrt(Math.pow((stop.getX() - start.getX()), 2) + Math.pow((stop.getY() - start.getY()), 2));
    }
}
