package com.example.guessinggame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guessinggame.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private EditText txtGuess;
    private Button btnGuess;
    private TextView lblOutput;

    private int theNumber, tryCount;

    public void checkGuess() {
        String guessText = txtGuess.getText().toString();
        String message = "";
	    try {
            int guess = Integer.parseInt(guessText);
            if (guess < theNumber) {
                message = guess + " is too low. Try again.";
                tryCount++;
            }
            else if (guess > theNumber){
                message = guess + " is too high. Try again.";
                tryCount++;
            }
            else {
                message = guess + " is correct. You win! Let's play again! Number of tries: " + tryCount;
                newGame();
            }
	    } catch (Exception e) {
            message = "Enter a whole number between 1 and 100.";
	    } finally {
            lblOutput.setText(message);
            txtGuess.requestFocus();
            txtGuess.selectAll();
        }
    }

    public void newGame() {
        tryCount = 0;
        theNumber = (int) (Math.random() * 100 + 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        txtGuess = (EditText) findViewById(R.id.txtGuess);
        btnGuess = (Button) findViewById(R.id.btnGuess);
        lblOutput = (TextView) findViewById(R.id.lblOutput);

        newGame();

        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "bruh", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });
    }
}