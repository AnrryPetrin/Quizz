package com.example.quizz;

public class Question {
    private String area;
    private String question;
    private String[] options;
    private int correctAnswer;

    public Question(String area, String question, String[] options, int correctAnswer) {
        this.area = area;
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getArea() {
        return area;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
