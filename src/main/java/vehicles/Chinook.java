package vehicles;

/**
 * extends class helicopter w same attributes but adds new attribute numSoldiers
 * @param numSoldiers
 */
public class Chinook extends Helicopter{
    private int numSoldiers;

    public Chinook(String name, float speed, float altitude, int soldiers) {
        super(name, speed, altitude);
        this.numSoldiers = soldiers;
    }

    public void setNumSoldiers(int soldiers) {
        this.numSoldiers = soldiers;
    }

    public int getNumSoldiers() {
        return this.numSoldiers;
    }

    @Override
    public void go() {
        this.altitude += 50;
        this.speed = 100;
        System.out.println(this.name + " is flying at " + this.getSpeed() + " km/h while hovering at an altitude of " + this.altitude + " meters.");
    }

    @Override
    public void stop() {
        this.altitude = 0;
        this.speed = 0;
        System.out.println(this.name + " lands. All " + this.numSoldiers + " soldiers are ready for battle.");
        this.numSoldiers = 0;
    }

    public void makeSound (int volume) {
        if (volume < 4) {
            System.out.println("Lieutenant says, 'Godspeed soldiers.'");
        } else {
            System.out.println("WHIRRRR");
        }
    }

    @Override
    public String toString() {
        return "Helicopter [altitude= " + altitude + ", name= " + name + ", speed= " + speed + ", numSoldiers= " + numSoldiers + "]";
    }

}
