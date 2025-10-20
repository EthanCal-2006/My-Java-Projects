package vehicles;

/**
 * truck extends vehicle w/ unique attributes loadCapacity and load
 * @param loadCapacity
 * @param load
 */
public class Truck extends Vehicle{

    private float loadCapacity;
    private float load;

    public Truck(String name, float speed, float loadCapacity, float load) {
        super(name, speed);
        this.loadCapacity = loadCapacity;
        this.load = load;
    }

    public float getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(float loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public float getLoad() {
        return load;
    }

    public void setLoad(float load) {
        this.load = load;
    }

    @Override
    public void go() {
        this.speed += 10 * load / loadCapacity;
        System.out.println(this.name + " is moving at " + this.speed + " km/h with a load of " + this.load + " kg, which is " + (this.load/this.loadCapacity) * 100 + "% of the load capacity.");
    }

    @Override public void stop() {
        this.speed -= 5 * load / loadCapacity;
        if (this.speed < 0) {
            this.speed = 0;
        }
        System.out.println(this.name + " tries to stop moving. The current speed is " + this.speed + "km/h.");
    }

    @Override
    public String toString() {
        return "Truck [load= " + load + ", loadCapacity= " + loadCapacity + ", name= " + name + ", speed=" + speed + "]";
    }

}
