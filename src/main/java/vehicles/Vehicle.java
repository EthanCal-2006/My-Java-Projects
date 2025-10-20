package vehicles;

/**
 * abstract class vehicle creates a template for concrete vehicle objects and declares methods stop() and go() for each subclass to define
 * @param name
 * @param speed
 */
public abstract class Vehicle {

    protected String name;
    protected float speed;

    public Vehicle(String name, float speed) {
        this.name = name;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public abstract void go();
    public abstract void stop();

    public void makeSound() {
        System.out.println("Vroom vroom");
    }


}
