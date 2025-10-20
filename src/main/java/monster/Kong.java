package monster;

public class Kong extends Monster {

    private boolean isRampaging;

    public Kong(String name, int health) {
        super(name, health);
        this.isRampaging = false;
    }

    public boolean isRampaging() {
        return isRampaging;
    }

    public void setRampaging(boolean isRampaging) {
        this.isRampaging = isRampaging;
        System.out.println(this.getName() + " is now rampaging!");
    }

    public void gorillaPunch(Monster target) {
        System.out.println(this.getName() + " uses Gorilla Punch on " + target.getName() + "!");
        int damage = 5;
        System.out.println("The Gorilla Puch does " + damage + " damage.");
        target.setHealth(target.getHealth() - damage);
    }

}
