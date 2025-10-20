package vehicles;

/**
 * car is an extension of vehicle w/ unique attribute engaged gear
 * @param engagedGear
 */
public class Car extends Vehicle{

    private int engagedGear;

    public Car(String name, float speed, int gear) {
        super(name, speed);
        this.engagedGear = gear;
    }

    public int getEngagedGear() {
        return engagedGear;
    }

    public void setEngagedGear(int engagedGear) {
        this.engagedGear = engagedGear;
    }

    @Override
    public void go() {
        this.engagedGear++;
        this.speed = engagedGear * 10;
        System.out.println(this.name + " is moving at " + this.speed + " km/h in gear " + this.engagedGear);
    }
    @Override
    public void stop() {
        this.engagedGear = 0;
        this.speed = 0;
        System.out.println(this.name + " rolls to a stop");
    }

    @Override
    public String toString() {
        return "Car [gear = " + engagedGear + ", name= " + name + ", speed= " + speed + "]";
    }
}
