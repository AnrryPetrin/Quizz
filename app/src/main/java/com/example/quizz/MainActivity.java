package com.example.quizz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Question list and current question index
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    // UI elements
    private TextView quizTitle, questionArea, questionText, feedbackText;
    private RadioGroup radioGroupOptions;
    private Button validateButton, restartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        quizTitle = findViewById(R.id.quizTitle);
        questionArea = findViewById(R.id.questionArea);
        questionText = findViewById(R.id.questionText);
        feedbackText = findViewById(R.id.feedbackText);
        radioGroupOptions = findViewById(R.id.radioGroupOptions);
        validateButton = findViewById(R.id.validateButton);
        restartButton = findViewById(R.id.restartButton);

        // Initialize question list
        questions = getQuestions();

        // Display the first question
        displayQuestion(currentQuestionIndex);

        // Validate answer button
        validateButton.setOnClickListener(v -> {
            if (radioGroupOptions.getCheckedRadioButtonId() == -1) {
                Toast.makeText(MainActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                return;
            }
            checkAnswer();
        });

        // Restart quiz button
        restartButton.setOnClickListener(v -> restartQuiz());
    }

    // Method to create the question list
    private List<Question> getQuestions() {
        List<Question> questionList = new ArrayList<>();

        // Add questions with options and correct answer
        questionList.add(new Question("General Knowledge", "What is the capital of France?",
                new String[] { "Paris", "Rome", "Berlin", "Madrid", "Lisbon" }, 0));
        questionList.add(new Question("Math", "What is 5 + 7?",
                new String[] { "10", "11", "12", "13", "14" }, 2));
        questionList.add(new Question("Science", "What is the chemical symbol for water?",
                new String[] { "CO2", "O2", "H2", "H2O", "HO" }, 3));

        return questionList;
    }

    // Method to display the current question
    private void displayQuestion(int questionIndex) {
        Question currentQuestion = questions.get(questionIndex);
        questionArea.setText("Area: " + currentQuestion.getArea());
        questionText.setText("Question: " + currentQuestion.getQuestion());


        // Display the answer options
        ((RadioButton) radioGroupOptions.getChildAt(0)).setText(currentQuestion.getOptions()[0]);
        ((RadioButton) radioGroupOptions.getChildAt(1)).setText(currentQuestion.getOptions()[1]);
        ((RadioButton) radioGroupOptions.getChildAt(2)).setText(currentQuestion.getOptions()[2]);
        ((RadioButton) radioGroupOptions.getChildAt(3)).setText(currentQuestion.getOptions()[3]);
        ((RadioButton) radioGroupOptions.getChildAt(4)).setText(currentQuestion.getOptions()[4]);

        // Clear previous selection and feedback
        radioGroupOptions.clearCheck();
    }

    // Method to check the selected answer
    private void checkAnswer() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        int selectedOptionIndex = radioGroupOptions
                .indexOfChild(findViewById(radioGroupOptions.getCheckedRadioButtonId()));

        if (selectedOptionIndex == currentQuestion.getCorrectAnswer()) {
            feedbackText.setText("Correct!");
            score++;
        } else {
            feedbackText.setText(
                    "Wrong! Correct answer: " + currentQuestion.getOptions()[currentQuestion.getCorrectAnswer()]);
        }

        // Move to the next question if available
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            displayQuestion(currentQuestionIndex);
        } else {
            showFinalScore();
        }
    }

    // Method to show the final score
    private void showFinalScore() {
        feedbackText.setText("Quiz completed! Your score: " + score + " out of " + questions.size());
        validateButton.setEnabled(false);

    }

    // Method to restart the quiz
    private void restartQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        validateButton.setEnabled(true);
        displayQuestion(currentQuestionIndex);
        feedbackText.setText("");

    }
}
