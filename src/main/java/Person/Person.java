package Person;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Person {
    private int id;
    private String name;
    private int age;
    private int numImaginaryFriends;
    private boolean isTimeTraveller;
    private double coffeeConsumptionRate;

    private List<String> superPowers = new ArrayList<>();
    private Map<Integer, String> favoriteMovies = new HashMap<>();

    public Person(int id, String name, int age, int numImaginaryFriends, boolean isTimeTraveller, double coffeeConsumptionRate, List<String> superPowers, Map<Integer, String> favoriteMovies) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.numImaginaryFriends = numImaginaryFriends;
        this.isTimeTraveller = isTimeTraveller;
        this.coffeeConsumptionRate = coffeeConsumptionRate;
        this.superPowers = superPowers;
        this.favoriteMovies = favoriteMovies;
        }

    public Person() {
        this.id = 0;
        this.name = "John Doe";
        this.age = 0;
        this.numImaginaryFriends = 0;
        this.isTimeTraveller = false;
        this.coffeeConsumptionRate = 0;

        this.superPowers.add("Invisibility");
        this.superPowers.add("Teleportation");
        this.superPowers.add("Super strength");
        this.superPowers.add("Telekenesis");

        this.favoriteMovies.put(1999, "The Matrix");
        this.favoriteMovies.put(2008, "The Dark Knight");
        this.favoriteMovies.put(2014, "Interstellar");
        this.favoriteMovies.put(2019, "Avengers: Endgame");
    }

    public Person(String name, int age) {
        this();
        this.name = name;
        this.age = age;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumImaginaryFriends() {
        return numImaginaryFriends;
    }

    public void setNumImaginaryFriends(int numImaginaryFriends) {
        this.numImaginaryFriends = numImaginaryFriends;
    }

    public boolean isTimeTraveller() {
        return isTimeTraveller;
    }

    public void setTimeTraveller(boolean isTimeTraveller) {
        this.isTimeTraveller = isTimeTraveller;
    }

    public double getCoffeeConsumptionRate() {
        return coffeeConsumptionRate;
    }

    public void setCoffeeConsumptionRate(double coffeeConsumptionRate) {
        this.coffeeConsumptionRate = coffeeConsumptionRate;
    }

    public List<String> getSuperPowers() {
        return superPowers;
    }

    public void setSuperPowers(List<String> superPowers) {
        this.superPowers = superPowers;
    }

    public Map<Integer, String> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void setFavoriteMovies(Map<Integer, String> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }


    public String compareCoffeeConsumption(Person other) {
        if (this.coffeeConsumptionRate > other.coffeeConsumptionRate) {
            return this.name + " drinks more coffee than " + other.name + ".";
        } else if (this.coffeeConsumptionRate < other.coffeeConsumptionRate) {
            return other.name + " drinks more coffee than " + this.name + ".";
        } else {
            return this.name + " and " + other.name + " drink the same amount of coffee.";
        }
    }

    public String estimateMovieWatchingTime(int years) {
        int totalHours = this.favoriteMovies.size() * years * 2;
        return name + " has spent approximately " + totalHours + " hours watching favorite movies over " + years + " years.";
    }

    public String estimateCoffeeConsumption(int y) {
        double totCups = coffeeConsumptionRate * 365 * y;
        double totLiters = totCups * 0.24;
        return name + " has consumed " + totLiters + " liters of coffee in " + y + " years.";
    }

    public String daysUntilNextBirthday(int mo, int bDay) {
        LocalDate today = LocalDate.now();
        LocalDate nextBday = LocalDate.of(today.getYear(), mo, bDay);
        if (today.isAfter(nextBday)) {
            nextBday = nextBday.plusYears(1);
        }
        long daysUntilNextBirthday = ChronoUnit.DAYS.between(today, nextBday);
        return name + "'s next Birthday is in " + daysUntilNextBirthday + " days.";
    }

    public String createSlogan() {
        if (!superPowers.isEmpty()) {
            return this.name + ": Powered by " + superPowers.get(0) + "!";
        }

        return this.name + ": No powers, just awesomeness!";
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Person has these properties: " + "id= " + id + ", name= " + name + ", age= " + age + ", numberOfImaginaryFriends= " + numImaginaryFriends + ", isTimeTraveller= " + isTimeTraveller + ", coffeeConsumptionRate= " + coffeeConsumptionRate + ", superPowers= " + superPowers + ", favoriteMovies = " + favoriteMovies + ".";
    }
    

    

}
