import java.util.ArrayList;

public class City {
    private int index;
    private double x;
    private double y;

    ArrayList<Item> items;

    public City(int index, double x, double y) {
        this.index = index;
        this.x = x;
        this.y = y;
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
