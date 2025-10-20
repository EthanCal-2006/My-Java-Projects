package monster;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Hello, World! Let's let two behemoths battle it out...");

        //Create 2 monsters.
        Zilla zilla = new Zilla("Big Zilla", 100);
        Kong kong = new Kong("King Kong", 100);

        String[] colors = {"\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m", "\u001B[36m", "\u001B[37m"};

        int rounds = 0;
        while (true) {
            //clears console
            System.out.print("\033[H\033[2J");

            System.out.println(colors[3]);
            rounds++;
            System.out.println("*** Round " + rounds + " Start ****");
            System.out.print(colors[0]);

            zilla.attack(kong);
            printHealthGraph(kong);

            if (!kong.isAlive()) {
                System.out.println("Zilla wins!");
                break;
            }

            System.out.print(colors[1]);
            kong.attack(zilla);

            if (kong.getHealth() < 50) {
                kong.setRampaging(true);
                kong.gorillaPunch(zilla);
            }

            printHealthGraph(zilla);
            if(!zilla.isAlive()) {
                System.out.println("Kong wins!");
                break;
            }

            //pause for 500ms
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printHealthGraph(Monster monster) {
        System.out.println(monster.getName() + " has " + monster.getHealth() + " health remaining.");
        for (int i = 0; i < monster.getHealth(); i++) {
            System.out.print("#");
        }
        System.out.println();
    }
}
