package lift_manager;

public class PrintManager {

    public void printOpeningStatements() {
        System.out.println("Welcome to the PR Calculator and set scheduler!");
        System.out.println("-----------------------------------------------");
    }

    public void printChoices() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Your choices:");
        System.out.println("1. Show current lifts");
        System.out.println("2. Add a new lift");
        System.out.println("3. Remove a lift");
        System.out.println("4. Save lifts to text file");
        System.out.println("5. Load lifts from text file");
        System.out.println("6. Create a split from loaded lifts");
        System.out.println("7. Save split to file");
        System.out.println("8. Clear lift list");
        System.out.println("0. Leave");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Enter number of your choice: ");
    }

}
