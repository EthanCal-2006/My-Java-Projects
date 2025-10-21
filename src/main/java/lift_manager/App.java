package lift_manager;

import java.util.Scanner;

public class App {
    static String [] ansicolors = {"\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m", "\u001B[36m", "\u001B[37m"};
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        PrintManager printManager = new PrintManager();
        LiftManager liftManager = new LiftManager();
        //List<Lift> liftList = liftManager.getLiftList();
        SplitManager splitManager = new SplitManager();
        //List<Split> splitList = splitManager.getSplitList();

        String input;
        int reps;
        double repsWeight;
        String exerciseName;

        printManager.printOpeningStatements();
        System.out.println(ansicolors[3]);
        printManager.printChoices();

        input = scnr.nextLine();
        int choice = Integer.parseInt(input);

        while(choice != 0)
        {
            switch (choice)
            {
                case 1: //show lifts
                    for (int i = 0; i < liftManager.getLiftList().size(); i ++) {
                        if (i % 2 == 0) { System.out.print(ansicolors[0]);}
                            else { System.out.print(ansicolors[1]); }
                        System.out.println("Lift " + (i + 1) + ":");
                        System.out.println(liftManager.getLiftList().get(i));
                    }
                break;

                case 2: //Add a new lift to the list
                    System.out.print(ansicolors[4]);
                    Lift newLift = new Lift();
                    System.out.println("You are adding a new lift to the list.");
                    
                    System.out.print("Enter name of exercise: ");
                    input = scnr.nextLine();
                    exerciseName = input;
                    newLift.setExerciseName(exerciseName);
                    
                    System.out.print("Enter weight used (lbs): ");
                    input = scnr.nextLine();
                    repsWeight = Double.parseDouble(input);
                    newLift.setRepsWeight(repsWeight);
                    
                    System.out.print("Enter number of reps performed: ");
                    input = scnr.nextLine();
                    reps = Integer.parseInt(input);
                    newLift.setReps(reps);

                    newLift.calculateOneRM(reps, repsWeight);

                    liftManager.addNewLift(newLift);
                    System.out.println("Lift added.");
                break;

                case 3: //remove a lift
                    System.out.print(ansicolors[6]);
                    System.out.println("Choose the number of a lift you would like to remove: ");
                    for (int i = 0; i < liftManager.getLiftList().size(); i ++) {
                        System.out.println("Lift " + (i + 1) + ":");
                        System.out.println(liftManager.getLiftList().get(i));
                    }
                    input = scnr.nextLine();
                    int removeChoiceIndex = Integer.parseInt(input);
                    liftManager.removeLift(removeChoiceIndex);
                    System.out.println("Lift removed.");
                break;

                case 4: //save to txt file
                    System.out.print(ansicolors[0]);
                    String fileName = "Lifts.txt";
                    System.out.println("Saving lifts to txt file...");
                    try { Thread.sleep(500); } catch (InterruptedException e) {e.printStackTrace();}
                    liftManager.writeLiftsToFile(liftManager.getLiftList(), fileName);
                    System.out.println("Lifts saved to file named " + fileName + ".");
                break;

                case 5: //load from txt file to liftList
                    System.out.print(ansicolors[2]);
                    fileName = "Lifts.txt";
                    System.out.println("Loading from txt file to liftList...");
                    try { Thread.sleep(500); } catch (InterruptedException e) {e.printStackTrace();}
                    liftManager.extractAndAppendToLiftList(fileName, liftManager.getLiftList());
                    System.out.println("Lifts loaded from file named " + fileName + ".");
                break;

                case 6: //create new split given coded array list
                    System.out.print(ansicolors[2]);
                    System.out.println("Creating new workout split...");
                    try { Thread.sleep(500); } catch (InterruptedException e) {e.printStackTrace();}
                    splitManager.createSplitList(liftManager.getLiftList());
                break;

                case 7: //save splitList to a txt file
                    System.out.print(ansicolors[0]);

                    System.out.println("Saving splits to external txt file...");
                    String desktopPath = System.getProperty("user.home") + "\\Downloads\\workout.txt";
                    try { Thread.sleep(500); } catch (InterruptedException e) {e.printStackTrace();}
                    splitManager.saveWorkoutToFile(desktopPath, splitManager.getSplitList());

                    System.out.println("Splits saved to file named " + desktopPath + ".");
                break;

                case 8:
                    System.out.println("Are you sure you want to clear your lift list? (y/n)");
                    String decision = scnr.nextLine();
                    if (decision.toLowerCase().equals("y")) {
                        liftManager.clearList();
                        System.out.println("List cleared.");
                        try { Thread.sleep(500); } catch (InterruptedException e) {e.printStackTrace();}
                    } else if (decision.toLowerCase().equals("n")) {
                        System.out.println("Returning to menu.");
                        try { Thread.sleep(500); } catch (InterruptedException e) {e.printStackTrace();}
                    } else {
                        System.out.println("Invalid input. Returning to menu.");
                        try { Thread.sleep(500); } catch (InterruptedException e) {e.printStackTrace();}
                    }
                break;

                case 0:
                break;

                default:
                    System.out.println("Invalid input. Try again.");
                break;

            }
            try { Thread.sleep(500); } catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(ansicolors[3]);
            printManager.printChoices();
            input = scnr.nextLine();
            choice = Integer.parseInt(input);
        }
        System.out.println("Thank you. Goodbye.");

        scnr.close();
    }
}
