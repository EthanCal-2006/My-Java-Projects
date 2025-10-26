package html_generator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    static void main() {

        Scanner scanner = new Scanner(System.in);
        Scanner fileIn; //input file connection
        PrintWriter fileOut; //HTML file connection
        String filenameIn; //original file's name
        String filenameOut; //new HTML file's name
        int dotIndex; //position of . in filename
        String line = null; //a line from the input file

        System.out.println("Enter a name or path");
        filenameIn = scanner.nextLine();

        try {
            fileIn = new Scanner(new FileReader(filenameIn));
            dotIndex = filenameIn.lastIndexOf(".");
            if(dotIndex == -1) {
                filenameOut = filenameIn + ".html";
            }
            else {
                filenameOut = filenameIn.substring(0, dotIndex) + ".html";
            }
            fileOut = new PrintWriter(filenameOut);

            try {
                line = fileIn.nextLine();
            }
            catch (NoSuchElementException e) {
                System.out.println("Error: " + e.getMessage());
            }
            if (line == null) {
                System.out.println("This file is empty :(");
            }
            else {
                fileOut.println("<html>");
                fileOut.println("<head>");
                fileOut.println("</head>");
                fileOut.println("<body>");
                fileOut.println(line);

                while(fileIn.hasNextLine()) {

                    fileOut.println("<br>");
                    line = fileIn.nextLine();

                    if(line.isEmpty()) {
                        fileOut.println("<br>");
                    }
                    else {
                        fileOut.println(line);
                    }
                }
                fileOut.println("</body>");
                fileOut.println("</html>");

                System.out.println("HTML file is processed.");
            }
            fileIn.close();
            fileOut.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
