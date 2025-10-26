package gpt_improved_lift_manager;

public class PrintManager {
    public void printOpeningStatements() {
        System.out.println("Welcome to the PR Calculator and Set Scheduler!");
        System.out.println("-----------------------------------------------");
    }

    public void printChoices() {
        System.out.println("""
            =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            Your choices:
            1. Show current lifts
            2. Add a new lift
            3. Remove a lift
            4. Save lifts to text file
            5. Load lifts from text file
            6. Create a split from loaded lifts
            7. Save split to file
            8. Clear lift list
            0. Exit
            =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
            Enter number of your choice:""");
    }
}
