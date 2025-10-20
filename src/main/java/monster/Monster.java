package monster;

import java.util.Random;

public abstract class Monster {
    private String name;
    private int health;
    private boolean isAlive;

    private Random random = new Random();

    public Monster(String name, int health) {
        this.name = name;
        this.health = health;
        this.isAlive = true;
    }
    
    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    // 
    
    public void attack(Monster target) {
        System.out.println(this.getName() + " attacks " + target.getName());

        int damage = random.nextInt(10);
        System.out.println("The attack does " + damage + " damage.");
        target.setHealth(target.getHealth() - damage);

        System.out.println(target.getName() + " has " + target.getHealth() + " health remaining. ");
        if (target.getHealth() <= 0) {
            target.setIsAlive(false);
        }

    }

}
