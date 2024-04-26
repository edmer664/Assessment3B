package QuizCalculator;

import java.io.*;

public class AnswerController {
    private String[] answers;
    private String filePath;

    public AnswerController(String path) {
        this.filePath = path;
        loadAnswers();
    }

    // Load answers from the file
    private void loadAnswers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line != null) {
                answers = line.split("\\|");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Modify answer at specified position
    public void modify(String value, int position) {
        if (position >= 0 && position < answers.length) {
            answers[position] = value;
        } else {
            System.out.println("Invalid position");
        }
    }

    // Save changes to the file
    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.join("|", answers));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get answers array
    public String[] get() {
        return answers;
    }

    // Convert answers array into a single string with "|" separator
    public String stringify() {
        return String.join("|", answers);
    }

}
