package monster;

public class Zilla extends Monster {
   
    private int angerLevel;

    public Zilla(String name, int health) {
        super(name, health);
        this.angerLevel = 0;
    }

    public int getAngerLevel() {
        return angerLevel;
    }

    public void setAngerLevel(int angerLevel) {
        this.angerLevel = angerLevel;
    }

    @Override
    public void attack(Monster target) {
        super.attack(target);
        this.angerLevel++;
        if (this.angerLevel >= 15) {
            fireBreath(target);
        }
    }

    public void fireBreath(Monster target) {
        //print in orange
        System.out.println(this.getName() + " uses Fire Breath on " + target.getName() + "!");
        int damage = 10;
        System.out.println("The Fire Breath does " + damage + " damage.");
        target.setHealth(target.getHealth() - damage);
        if (target.getHealth() <= 0) {
            target.setIsAlive(false);
        }
    }
}
