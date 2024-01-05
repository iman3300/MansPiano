package blogspot.nagasaimanoj.in.pianotiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

        Button startButton2 = findViewById(R.id.playButton2);
                Button startButton3 = findViewById(R.id.playButton3);
import androidx.appcompat.app.AppCompatActivity;

public class faded extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);

        Button startButton = findViewById(R.id.playButton);
        Button startButton1 = findViewById(R.id.playButton1);
        Button startButton4 = findViewById(R.id.playButton7);
        Button startButton5 = findViewById(R.id.playButton8);
        Button startButton6 = findViewById(R.id.playButton9);
        Button startButton7 = findViewById(R.id.playButton10);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open a new intent or activity when Start button is clicked
                Intent startIntent = new Intent(faded.this, MainActivity.class);
                startActivity(startIntent);
            }
        });

        startButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open a new intent or activity when playButton1 is clicked
                Intent startIntent1 = new Intent(faded.this, MainActivity2.class); // Replace with the correct activity
                startActivity(startIntent1);
            }
        });
        startButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open a new intent or activity when playButton1 is clicked
                Intent startIntent1 = new Intent(faded.this, MainActivity3.class); // Replace with the correct activity
                startActivity(startIntent1);
            }
        });
        startButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open a new intent or activity when playButton1 is clicked
                Intent startIntent1 = new Intent(faded.this, MainActivity4.class); // Replace with the correct activity
                startActivity(startIntent1);
            }
        });
        startButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open a new intent or activity when playButton1 is clicked
                Intent startIntent1 = new Intent(faded.this, MainActivity5.class); // Replace with the correct activity
                startActivity(startIntent1);
            }
        });
        startButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open a new intent or activity when playButton1 is clicked
                Intent startIntent1 = new Intent(faded.this, MainActivity6.class); // Replace with the correct activity
                startActivity(startIntent1);
            }
        });
        startButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open a new intent or activity when playButton1 is clicked
                Intent startIntent1 = new Intent(faded.this, MainActivity7.class); // Replace with the correct activity
                startActivity(startIntent1);
            }
        });
        startButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open a new intent or activity when playButton1 is clicked
                Intent startIntent1 = new Intent(faded.this, MainActivity8.class); // Replace with the correct activity
                startActivity(startIntent1);
            }
        });

    }
}
