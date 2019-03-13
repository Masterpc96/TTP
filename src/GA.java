import java.util.*;

public class GA {

    public Thief tournament(ArrayList<Thief> thieves) {
        ArrayList<Thief> players = new ArrayList<>();

        Random random = new Random();
        Comparator<Thief> comparator = new Comparator<Thief>() {
            @Override
            public int compare(Thief o1, Thief o2) {
                return (int) (o1.getDistance() - o2.getDistance());
            }
        };

        for (int i = 0; i < Main.TOUR_SIZE; i++) {
            players.add(thieves.get(random.nextInt(Main.POP_SIZE)));
        }

        Collections.sort(players, comparator);

        return players.get(0);
    }

    public Thief crossover(Thief mother, Thief father, int cut) {
        List<City> fatherList = father.getCities();
        List<City> motherList = mother.getCities();

        ArrayList<City> childList = new ArrayList<>();
        for (int i = 0; i < fatherList.size(); i++) {
            if (i < cut) {
                City city = fatherList.get(i);
                childList.add(city);
            } else {
                City city = motherList.get(i);
                if (!childList.contains(city)) {
                    childList.add(motherList.get(i));
                }
            }
        }

        if (childList.size() < fatherList.size()) {
            for (City city : motherList) {
                if (!childList.contains(city)) {
                    childList.add(city);
                }
            }
        }


        Thief thief = new Thief();
        thief.setCities(childList);
        thief.evaluate();

        return thief;
    }

    public Thief muttation(Thief thief) {
        int percent = thief.getCities().size();
        percent *= Main.GEN_PERCENT;
        percent /= 100;
        Random r = new Random();
        int first;
        int second;

        for (int i = 0; i < percent; i++) {
            first = r.nextInt(thief.getCities().size());
            second = r.nextInt(thief.getCities().size());

            City temp = thief.getCities().get(first);
            thief.getCities().set(first, thief.getCities().get(second));
            thief.getCities().set(second, temp);
        }

        return thief;
    }
}
