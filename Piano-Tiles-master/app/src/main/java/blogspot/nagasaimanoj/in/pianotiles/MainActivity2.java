package blogspot.nagasaimanoj.in.pianotiles;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer mediaPlayer;
    private NoteManager noteManager;
    private TextView txtGameOver;
    private TextView txtScore;
    private int score;

    // Buttons for piano keys
    private Button btnC;
    private Button btnD;
    private Button btnE;
    private Button btnF;
    private Button btnG;
    private Button btnA;
    private Button btnB;

    // Buttons for adjusting animation speed
    private Button btnSpeed;
    private Button btnSlow;

    // Handler for delayed tasks
    private Handler gameOverHandler = new Handler();
    private static final int GAME_OVER_DELAY = 1500; // 3000 milliseconds (adjust as needed)

    // Flag to track if note buttons are enabled
    private boolean noteButtonsEnabled = true;

    // Button for starting a new game
    private Button btnPlay;

    // Variable to track animation duration
    private int animationDuration = 1000; // Default duration

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize buttons for each piano key
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        btnE = findViewById(R.id.btnE);
        btnF = findViewById(R.id.btnF);
        btnG = findViewById(R.id.btnG);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);

        // Set click listeners for each button
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnE.setOnClickListener(this);
        btnF.setOnClickListener(this);
        btnG.setOnClickListener(this);
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);

        // Initialize Speed button
        btnSpeed = findViewById(R.id.btnSpeed);
        btnSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set to fast animation
                animationDuration = 500;
            }
        });
        // Initialize Slow button
        btnSlow = findViewById(R.id.btnhaha);
        btnSlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set to slow animation
                animationDuration = 1500;
            }
        });

        // Initialize Slow button
        btnSlow = findViewById(R.id.btnSlow);
        btnSlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set to slow animation
                animationDuration = 2500;
            }
        });

        // Initialize MediaPlayer
        mediaPlayer = new MediaPlayer();

        // Initialize NoteManager (NoteManager class not provided)
        noteManager = new NoteManager();

        // Initialize TextView for Game Over message
        txtGameOver = findViewById(R.id.txtGameOver);

        // Initialize Button for Play
        btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(R.raw.alone);  // Play haha.mp3 sound
                startGame();
                hideGameOver();
                enableNoteButtons(); // Enable note buttons when playing again
            }
        });

        // Initialize TextView for displaying the score
        txtScore = findViewById(R.id.txtScore);
        score = 0;

        startGame(); // Start the game when the activity is created
    }

    private void startGame() {
        // Show the generated notes in the TextView
        String[] notes = {"C", "D", "E", "F", "G", "A", "B"};
        int numberOfNotes = 3; // Adjust the number of notes to display simultaneously

        for (int i = 0; i < numberOfNotes; i++) {
            noteManager.generateNote();

            int noteIndex = noteManager.getCurrentNoteIndex();

            if (noteIndex >= 0 && noteIndex < notes.length) {
                String currentNote = notes[noteIndex];

                // Show the note button corresponding to the displayed note
                Button currentButton = getButtonByNote(currentNote);
                if (currentButton != null) {
                    animateButton(currentButton);
                    currentButton.setVisibility(View.VISIBLE);
                }
            }
        }

        // Post a delayed callback to show "Game Over" if the buttons are not clicked in time
        gameOverHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showGameOver();
                disableNoteButtons(); // Disable note buttons on game over
            }
        }, GAME_OVER_DELAY);
    }

    private void hideAllButtons() {
        btnC.setVisibility(View.GONE);
        btnD.setVisibility(View.GONE);
        btnE.setVisibility(View.GONE);
        btnF.setVisibility(View.GONE);
        btnG.setVisibility(View.GONE);
        btnA.setVisibility(View.GONE);
        btnB.setVisibility(View.GONE);
    }

    private Button getButtonByNote(String note) {
        switch (note) {
            case "C":
                return btnC;
            case "D":
                return btnD;
            case "E":
                return btnE;
            case "F":
                return btnF;
            case "G":
                return btnG;
            case "A":
                return btnA;
            case "B":
                return btnB;
            default:
                return null; // Handle the case when the note is not recognized
        }
    }

    private void animateButton(final Button button) {
        // Get the initial Y position of the button
        float initialY = button.getY();

        // Get the height of the screen
        float screenHeight = getResources().getDisplayMetrics().heightPixels;

        // Calculate the distance to move the button to the bottom
        float distanceToBottom = screenHeight - initialY - button.getHeight();

        // Animate the button to move down to the bottom of the screen
        ObjectAnimator animator = ObjectAnimator.ofFloat(button, "translationY", 0f, distanceToBottom);
        animator.setDuration(animationDuration); // Adjust the duration as needed
        animator.start();

        // Reset the translation after the animation completes
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                button.setTranslationY(0f);
            }
        });
    }

    private void hideGameOver() {
        txtGameOver.setVisibility(View.GONE);
        score = 0; // Reset the score on game restart
        txtScore.setText("Score: " + score);
        // Remove any pending callbacks to avoid showing "Game Over" after restarting the game
        gameOverHandler.removeCallbacksAndMessages(null);
    }

    private void showGameOver() {
        txtGameOver.setVisibility(View.VISIBLE);
        txtScore.setText("Score: " + score);

        // Stop the media player when the game is over
        stopMediaPlayer();
    }

    private void enableNoteButtons() {
        noteButtonsEnabled = true;
    }

    private void disableNoteButtons() {
        noteButtonsEnabled = false;
    }

    @Override
    public void onClick(View view) {
        // Remove any pending callbacks to avoid multiple "Game Over" messages
        gameOverHandler.removeCallbacksAndMessages(null);

        if (!noteButtonsEnabled) {
            // Buttons are disabled, do nothing
            return;
        }

        int buttonIndex = getButtonIndex(view.getId());

        if (!noteManager.checkInput(buttonIndex)) {
            // Handle game over
            showGameOver();
            disableNoteButtons(); // Disable note buttons on game over

            // Post a delayed callback to hide the "Game Over" message after a certain time
            gameOverHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hideGameOver();
                    enableNoteButtons(); // Enable note buttons when starting a new game
                    startGame();
                }
            }, GAME_OVER_DELAY);
        } else {
            // Play the corresponding sound when a key is pressed

            // Increment the score on every right click
            score++;
            txtScore.setText("Score: " + score);
            // Change the note randomly after the right button is clicked
            startGame();
        }
    }

    private int getButtonIndex(int buttonId) {
        switch (buttonId) {
            case R.id.btnC:
                return 0;
            case R.id.btnD:
                return 1;
            case R.id.btnE:
                return 2;
            case R.id.btnF:
                return 3;
            case R.id.btnG:
                return 4;
            case R.id.btnA:
                return 5;
            case R.id.btnB:
                return 6;
            default:
                return -1; // Handle the case when the button is not recognized
        }
    }

    private void playSound(int soundResource) {
        try {
            // Load and play the sound
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this, soundResource);
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopMediaPlayer() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = new MediaPlayer(); // Reinitialize the MediaPlayer
        }
    }

    @Override
    protected void onDestroy() {
        // Release the MediaPlayer resources
        stopMediaPlayer();
        super.onDestroy();
    }
}
