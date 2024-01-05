package blogspot.nagasaimanoj.in.pianotiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoteManager {

    private List<Integer> noteSequence;
    private int currentIndex;

    public NoteManager() {
        noteSequence = new ArrayList<>();
        currentIndex = 0;
    }

    public void generateNote() {
        Random random = new Random();
        int note = random.nextInt(7); // Adjust this based on the number of piano keys

        noteSequence.add(note);
    }

    public boolean checkInput(int input) {
        if (currentIndex < noteSequence.size() && noteSequence.get(currentIndex) == input) {
            currentIndex++;
            return true;
        } else {
            // Game over logic, reset the game
            resetGame();
            return false;
        }
    }

    public int getCurrentNoteIndex() {
        if (currentIndex < noteSequence.size()) {
            return noteSequence.get(currentIndex);
        } else {
            return -1; // Handle the case when the sequence is finished
        }
    }

    private void resetGame() {
        noteSequence.clear();
        currentIndex = 0;
    }
}
