package Person;

public class App 
{
    public static void main( String[] args )
    {
        Person person = new Person("Shad", 50);
        person.getSuperPowers().add("Flying");
        person.getSuperPowers().add("Laser Vision");

        System.out.println(person);

        Person person2 = new Person("Max", 25);
        person2.getSuperPowers().add("Super Strength");
        person2.getSuperPowers().add("Super Speed");
        person2.getSuperPowers().add("Super Intelligence");

        System.out.println(person2);

        person.setCoffeeConsumptionRate(9);

        String compareCoffee = person.compareCoffeeConsumption(person2);
        System.out.println(compareCoffee);
        
        System.out.println(person.estimateCoffeeConsumption(5));
        System.out.println(person.daysUntilNextBirthday(12, 25));
        System.out.println(person.createSlogan());

    }
}
