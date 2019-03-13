import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Loader {

    private BufferedReader reader;

    public static String problemName;
    public static String knapsackDataType;
    public static int dimension;
    public static int numberOfItem;
    public static int knapsackCapacity;
    public static double minSpeed;
    public static double maxSpeed;
    public static double rentingRatio;
    public static String edgeWeightType;

    private ArrayList<City> cities;

    public ArrayList<City> load(String path) {
        String line;
        try {
            reader = new BufferedReader(new FileReader(path));
            String splitted[];


            // reading settings
            for (int i = 0; i < 9; i++) {
                line = reader.readLine();
                splitted = line.split(":");
                switch (i) {
                    case 0:
                        problemName = splitted[1].trim();
                        break;
                    case 1:
                        knapsackDataType = splitted[1].trim();
                        break;
                    case 2:
                        dimension = Integer.parseInt(splitted[1].trim());
                        break;
                    case 3:
                        numberOfItem = Integer.parseInt(splitted[1].trim());
                        break;
                    case 4:
                        knapsackCapacity = Integer.parseInt(splitted[1].trim());
                        break;
                    case 5:
                        minSpeed = Double.parseDouble(splitted[1].trim());
                        break;
                    case 6:
                        maxSpeed = Double.parseDouble(splitted[1].trim());
                        break;
                    case 7:
                        rentingRatio = Double.parseDouble(splitted[1].trim());
                        break;
                    case 8:
                        edgeWeightType = splitted[1].trim();
                        break;
                }
            }

            reader.readLine();  // header
            cities = new ArrayList<>(dimension);
            String splitPlace[];
            // reading nodes cords
            for (int i = 0; i < dimension; i++) {
                line = reader.readLine();
                splitPlace = line.split("\t");
                cities.add(new City(Integer.parseInt(splitPlace[0].trim()),
                        Double.parseDouble(splitPlace[1].trim()),
                        Double.parseDouble(splitPlace[2].trim())));
            }

            reader.readLine();  // header
            String splitItems[];
            int assigned;
            // assign items to nodes
            for (int i = 0; i < numberOfItem; i++) {
                line = reader.readLine();
                splitItems = line.split("\t");
                assigned = Integer.parseInt(splitItems[3].trim()) - 1;
                cities.get(assigned).addItem(new Item(Integer.parseInt(splitItems[0].trim()),
                        Integer.parseInt(splitItems[1].trim()),
                        Integer.parseInt(splitItems[2].trim())));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
