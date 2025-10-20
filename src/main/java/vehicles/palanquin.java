package vehicles;

/**
 * palanquin extends vehicle w/ unique attribute footMen
 * @param footMen
 */
public class palanquin extends Vehicle {

    private int footMen;

    public palanquin(String name, float speed, int footMen) {
        super(name, speed);
        this.footMen = footMen;
    }

    public int getFootMen() {
        return footMen;
    }

    public void setFootMen(int footMen) {
        this.footMen = footMen;
    }

    @Override
    public void go() {
        this.footMen += 1;
        if (this.footMen >= 2) {
            this.speed = this.footMen * 2;
        }

        System.out.println(this.name + " is moving at " + this.speed + " km/h with " + this.footMen + " footmen.");
    }

    @Override
    public void stop() {
        this.footMen = 0;
        this.speed = 0;
        System.out.println(this.name + " is lowered to the ground and the footmen are dismissed.");
    }

    @Override
    public String toString() {
        return "Palanquin [footmen= " + footMen + ", name=" + name + ", speed=" + speed + "]";
    }

    public void makeSound() {
        System.out.println("Hup hup");
    }
}


