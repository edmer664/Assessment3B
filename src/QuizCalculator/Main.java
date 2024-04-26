package QuizCalculator;

import java.lang.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private String[] answers;
    private AnswerController answerController = new AnswerController("./src/answers.txt");
    Scanner sc = new Scanner(System.in);

    public void onStart() {
        loadAnswers();
        displayWelcome();
        showMenu();
        mainLoop();
    }

    public void onClose() {

        System.exit(0);
    }

    public void loopPrint(String str, int repition) {
        for (int i = 0; i < repition; i++) {
            System.out.print(str);
        }
        System.out.println();
    }

    public void displayWelcome() {
        loopPrint("*", 50);
        System.out.println("\tWelcome to BMSI SCI-QUIZ BEE");
        loopPrint("*", 50);
    }

    public void showMenu() {
        loopPrint("*", 50);
        System.out.println("1. INPUT STUDENT ANSWERS");
        System.out.println("2. VIEW INSTRUCTIONS");
        System.out.println("3. CONFIG ANSWERS");
        System.out.println("9. EXIT");
        loopPrint("*", 50);
    }

    public void loadAnswers() {

        this.answers = answerController.get();
    }

    public void mainLoop() {
        boolean shouldContinue = true;
        while (shouldContinue) {
            String selectedProcess = getChoice();
            switch (selectedProcess) {
                case "INPUT":
                    runCalculate();
                    break;
                case "DISPLAY_INSTRUCTIONS":
                    showInstructions();
                    break;
                case "CONFIG_ANSWERS":
                    configAnswers();
                    break;
                case "PROGRAM_EXIT":
                    shouldContinue = false;
                    break;
                case "INVALID":
                    System.out.println("INVALID");
                    break;
            }
            if (!selectedProcess.equals("PROGRAM_EXIT")) {
                showMenu();
            }


        }
    }

    public String getChoice() {
        int userInput;
        try {
            userInput = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("INVALID INPUT: " + e.toString());
            sc.nextLine();
            return "INVALID";
        }

        switch (userInput) {
            case 1:
                return "INPUT";
            case 2:
                return "DISPLAY_INSTRUCTIONS";
            case 9:
                return "PROGRAM_EXIT";
            case 3:
                return "CONFIG_ANSWERS";
            default:
                return "INVALID";
        }

    }

    public void runCalculate() {
        System.out.println("How Many Students?");
        int studentCount = sc.nextInt();
        sc.nextLine();
        ArrayList<Student> students = new ArrayList<>();

        for (int i = 0; i < studentCount; i++) {
            loopPrint("_", 20);
            System.out.println("NAME:");
            String studName = sc.nextLine();
            System.out.println("ANSWERS (ex. ans1|ans2|ans3)");
            String[] studAnswers = sc.nextLine().split("\\|");
            Student student = new Student(studName, studAnswers);
            student.computeScore(answers);
            System.out.println(student.getScore());
            students.add(student);
        }

        displayTopThree(students);
    }

    public void displayTopThree(ArrayList<Student> students) {
        ArrayList<Student> sortedStudents = insertionSortStudents(students);
        for (int i = 0; i < 3; i++) {
            loopPrint("+",10);
            System.out.println("TOP " + String.valueOf(i + 1));
            System.out.println("NAME:" + sortedStudents.get(i).getName());
            System.out.println("SCORE:" + sortedStudents.get(i).getScore());
            loopPrint("+",10);
        }
    }

    private static ArrayList<Student> insertionSortStudents(ArrayList<Student> students) {
        int n = students.size();
        for (int i = 1; i < n; ++i) {
            Student key = students.get(i);
            int j = i - 1;

            // Move elements of arr[0..i-1], that are greater than key, to one position ahead of their current position
            while (j >= 0 && students.get(j).getScore() < key.getScore()) {
                students.set(j + 1, students.get(j));
                j = j - 1;
            }
            students.set(j + 1, key);
        }
        return students;
    }


    public void configAnswers() {
        System.out.println("Insert New Value: ");
        String tempVal = sc.nextLine();
        System.out.println("Insert Round");
        int tempPos = sc.nextInt();
        sc.nextLine();
        answerController.modify(tempVal, tempPos);
        System.out.println("Values UPDATED");
        loadAnswers();
        System.out.println(answerController.stringify());

    }

    public void showInstructions() {
        loopPrint("*", 50);
        System.out.println("INSTRUCTIONS");
        System.out.println("1. Input the amount of the participating students");
        System.out.println("2. You will be prompted to input the name of the student");
        System.out.println("3. After Inserting the student's name you should input the answer \n\t corresponding to the round order\n\t[EASY|AVERAGE|DIFFICULT]");
        System.out.println("sample answer format: \"MITOCHONDRIA|WATER|GLUCOSE\"");
        System.out.println("4. Repeat until the last student");
        loopPrint("*", 50);
    }

    public void start() {
        onStart();
        onClose();
    }


    public static void main(String[] args) {
        Main app = new Main();
        app.start();

    }
}
