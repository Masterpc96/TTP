import javafx.scene.paint.Stop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {

    public static int TOUR_SIZE = 5;

    public static int POP_SIZE = 100;

    public static int GEN_PERCENT = 5;

    public static int STOP = 1000;

    public static void main(String[] args) {
        ArrayList<City> cities;
        Loader loader = new Loader();

        Thief thief;

        ArrayList<Thief> population = new ArrayList<>();

        ArrayList<Thief> newPopulation = new ArrayList<>();

        ArrayList<City> temp;

        GA ga = new GA();

        Random random = new Random();

        ArrayList<Double> distances = new ArrayList<>();

        int cut;

        cities = loader.load("data/easy_0.ttp");
        System.out.print("");

        // generate start population
        for (int i = 0; i < POP_SIZE; i++) {

            Collections.shuffle(cities);

            temp = new ArrayList<>(cities);

            thief = new Thief();

            thief.setCities(temp);

            thief.evaluate();

            population.add(thief);
        }

        for (int i = 0; i < STOP; i++) {
            // generate childs
            distances.clear();

            for (int j = 0; j < POP_SIZE / 2; j++) {
                Thief mother = ga.tournament(new ArrayList<>(population));
                Thief father = ga.tournament(new ArrayList<>(population));

                while (mother == father) {
                    father = ga.tournament(population);
                }

                cut = random.nextInt(mother.getCities().size());

                Thief child1 = ga.crossover(mother, father, cut);
                Thief child2 = ga.crossover(father, mother, cut);

                ga.muttation(child1);
                ga.muttation(child2);

                child1.evaluate();
                child2.evaluate();

                newPopulation.add(child1);
                newPopulation.add(child2);

                distances.add(child1.getDistance());
                distances.add(child2.getDistance());
            }
            population = new ArrayList<>(newPopulation);
            System.out.println(Collections.min(distances));
        }


        System.out.print("");


    }
}
