package QuizCalculator;

public class Student {

    public String getName() {
        return name;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getScore() {
        return score;
    }

    private final String name;
    private final String[] answers;
    private int score;


    public Student(String name, String[] answers) {
        this.name = name;
        this.answers = answers;
    }

    public int computeScore(String[] correctAnswers) {
        if (correctAnswers[0].equalsIgnoreCase(answers[0])) {
            score += 2;
        }
        if (correctAnswers[1].equalsIgnoreCase(answers[1])) {
            score += 5;
        }
        if (correctAnswers[2].equalsIgnoreCase(answers[2])) {
            score += 8;
        }
        return score;
    }


}
