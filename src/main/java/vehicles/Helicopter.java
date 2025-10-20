package vehicles;

/**
 * helicopter extends vehicle w/ unique attribute altitude
 * @param altitude
 */
public class Helicopter extends Vehicle {

    protected float altitude;

    public Helicopter(String name, float speed, float altitude) {
        super(name, speed);
        this.altitude = altitude;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    @Override
    public void go() {
        this.altitude += 100;
        this.speed = 200;
        System.out.println(this.name + " is flying at " + this.getSpeed() + " km/h at an altitude of " + altitude + " meters.");
    }

    @Override
    public void stop() {
        this.altitude = 0;
        this.speed = 0;
        System.out.println(this.name + " lands.");
    }

    @Override
    public String toString() {
        return "Helicopter [altitude= " + altitude + ", name= " + name + ", speed= " + speed + "]";
    }

    public void makeSound() {
        System.out.println("Whirrrrrrr");
    }

    public void makeSound(int volume) {
        if (volume < 0) {
            System.out.println("Whirr".toLowerCase());
        } else if (volume > 0) {
            System.out.println("Whirr".toUpperCase());
        } else {
            System.out.println("Somethings wrong but this heli still goes whirrr");
        }
    }

}
