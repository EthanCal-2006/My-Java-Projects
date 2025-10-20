package vehicles;

/**
 * Main driver
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World! Welcome to the parking lot" );

        String asciiColors[] = {"\u001B[30m", "\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m", "\u001B[36m"};

        Vehicle[] vehicles = new Vehicle[5];

        vehicles[0] = new Car("Corvette", 0, 0);
        vehicles[1] = new Helicopter("Huey", 0, 0.0f);
        vehicles[2] = new Truck("Mack", 0, 1000.0f, 800.0f);
        vehicles[3] = new palanquin("Royal Procession", 0, 0);
        vehicles[4] = new Chinook("Apache", 0, 0.0f, 10);

        for(Vehicle vehicle : vehicles) {
            
            System.out.println("======================================");

            for (int i = 0; i < 3; i++) {
                System.out.print(asciiColors[i]);
                vehicle.go();

                if(vehicle instanceof Truck) {
                    Truck truck = (Truck) vehicle;
                    truck.setLoad(truck.getLoad() + 55.2f); //add 55.2 kg to the load
                }
            }

            System.out.println(asciiColors[4]);
            vehicle.makeSound();

            if(vehicle instanceof Helicopter) {
                if (vehicle instanceof Chinook) {
                    Chinook chinook = (Chinook) vehicle;
                    chinook.makeSound(3);
                }
                Helicopter helicopter = (Helicopter) vehicle;
                helicopter.makeSound(4);
            }

            System.out.print(vehicle);

            System.out.println(asciiColors[5]);

            while (vehicle.getSpeed() > 0) {
                vehicle.stop();
            }

            System.out.println(vehicle);
        }
    }
}
