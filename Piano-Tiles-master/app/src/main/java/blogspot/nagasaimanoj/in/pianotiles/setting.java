// setting.java
package blogspot.nagasaimanoj.in.pianotiles;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class setting extends AppCompatActivity {

    private CheckBox musicCheckBox;
    private SeekBar volumeSeekBar;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting1);

        // Initialize UI elements
        musicCheckBox = findViewById(R.id.musicCheckBox);
        volumeSeekBar = findViewById(R.id.volumeSeekBar);
        backButton = findViewById(R.id.backButton);

        // Load previous settings
        musicCheckBox.setChecked(GameSettings.isMusicEnabled());
        volumeSeekBar.setProgress(GameSettings.getVolumeLevel());

        // Handle music checkbox state change
        musicCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            GameSettings.setMusicEnabled(isChecked);
            // Add logic to handle music state change
            adjustVolume(volumeSeekBar.getProgress()); // Adjust volume when music state changes
        });

        // Handle volume seekbar change
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                GameSettings.setVolumeLevel(progress);
                // Add logic to handle volume change
                adjustVolume(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Called when the user starts touching the seekbar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Called when the user stops touching the seekbar
            }
        });

        // Handle back button click
        backButton.setOnClickListener(view -> {
            // Open a new intent or activity when the back button is clicked
            Intent startIntent = new Intent(setting.this, start.class);
            startActivity(startIntent);
        });
    }

    private void adjustVolume(int volume) {
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioManager != null) {
            int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

            if (musicCheckBox.isChecked()) {
                int adjustedVolume = (int) ((float) volume / 100 * maxVolume);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, adjustedVolume, 0);
            } else {
                // If musicCheckBox is not checked, set volume to 0
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
            }
        }
    }
}
