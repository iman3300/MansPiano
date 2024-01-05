package blogspot.nagasaimanoj.in.pianotiles;

public class GameSettings {
    private static boolean musicEnabled = true;
    private static boolean vibrationEnabled = true;
    private static int volumeLevel = 50;

    public static boolean isMusicEnabled() {
        return musicEnabled;
    }

    public static void setMusicEnabled(boolean musicEnabled) {
        GameSettings.musicEnabled = musicEnabled;
    }

    public static boolean isVibrationEnabled() {
        return vibrationEnabled;
    }

    public static void setVibrationEnabled(boolean vibrationEnabled) {
        GameSettings.vibrationEnabled = vibrationEnabled;
    }

    public static int getVolumeLevel() {
        return volumeLevel;
    }

    public static void setVolumeLevel(int volumeLevel) {
        GameSettings.volumeLevel = volumeLevel;
    }
}
