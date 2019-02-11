package com.example.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startEvaluation(View view) {
        String[] answers = evaluateGui();
        int result = evaluateQuiz(answers);
        toastResult(result);
    }

    public void toastResult(int result) {
        String message = result + " out of 7. ";

        if (result == 0) {
            message += "Poor luck.";
        } else if (result == 2) {
            message += "You could do better.";
        } else if (result == 4) {
            message += "Quite nice.";
        } else if (result == 5) {
            message += "Really nice.";
        } else if (result == 6) {
            message += "Great!";
        } else if (result == 7) {
            message += "Absolutely awesome!";
        }

        Toast reportResult = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT);
        reportResult.show();
    }

    public String[] evaluateGui() {
        String[] ret = new String[7];
        EditText editTextQuestion1 = findViewById(R.id.question_1);

        CheckBox checkBoxQuestion2Protium = findViewById(R.id.question_2_protium);
        CheckBox checkBoxQuestion2Deuterium = findViewById(R.id.question_2_Deuterium);
        CheckBox checkBoxQuestion2Tritium = findViewById(R.id.question_2_Tritium);
        CheckBox checkBoxQuestion2None = findViewById(R.id.question_2_None);

        Boolean answerQuestion2 = false;

        if (checkBoxQuestion2Protium.isChecked() == true && checkBoxQuestion2Deuterium.isChecked() == true && checkBoxQuestion2Tritium.isChecked() == true && checkBoxQuestion2None.isChecked() == false) {
            answerQuestion2 = true;
        }

        EditText editTextQuestion4 = findViewById(R.id.question_4);
        EditText editTextQuestion6 = findViewById(R.id.question_6);
        EditText editTextQuestion7 = findViewById(R.id.question_7);


        ret[0] = editTextQuestion1.getText().toString().toLowerCase();
        ret[1] = Boolean.toString(answerQuestion2);
        ret[2] = evaluateRadioGroup(R.id.radio_group_question_3).toLowerCase();
        ret[3] = editTextQuestion4.getText().toString().toLowerCase();
        ret[4] = evaluateRadioGroup(R.id.radio_group_question_5).toLowerCase();
        ret[5] = editTextQuestion6.getText().toString().toLowerCase();
        ret[6] = editTextQuestion7.getText().toString().toLowerCase();
        return ret;
    }

    public int evaluateQuiz(String[] answers) {
        int result = 0;
        String[] correctAnswers = {"ottawa", "true", "true", "util", "br", "a","pink city"};

        for (int i = 0; i < correctAnswers.length; i++) {
            if (answers[i].equals(correctAnswers[i])) {
                result++;
            }
        }

        return result;
    }

    private String evaluateRadioGroup(int id) {
        RadioGroup radioGroupQuestion;
        RadioButton radioButtonQuestion;

        radioGroupQuestion = findViewById(id);

        int radioButtonId = radioGroupQuestion.getCheckedRadioButtonId();
        radioButtonQuestion = findViewById(radioButtonId);

        if (radioButtonQuestion == null) {
            return "";
        }

        return (String)radioButtonQuestion.getText();
    }

    public void reset(View view) {
        EditText editText = findViewById(R.id.question_1);
        editText.setText("");

        CheckBox checkBox = findViewById(R.id.question_2_protium);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_Deuterium);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_Tritium);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_None);
        checkBox.setChecked(false);

        RadioGroup radioGroup = findViewById(R.id.radio_group_question_3);
        radioGroup.clearCheck();

        EditText editText4 = findViewById(R.id.question_4);
        editText4.setText("");

        radioGroup = findViewById(R.id.radio_group_question_5);
        radioGroup.clearCheck();

        EditText editText6 = findViewById(R.id.question_6);
        editText6.setText("");

        EditText editText7 = findViewById(R.id.question_7);
        editText7.setText("");

        TextView editText8 = (TextView)findViewById(R.id.score);
        editText8.setText("0");

        TextView editText9 = (TextView)findViewById(R.id.text1);
        editText9.setText("");

    }
}

