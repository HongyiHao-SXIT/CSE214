package HW1;

class SongRecord {
    private String title;
    private String artist;
    private int minutes;
    private int seconds;

    public SongRecord() {
        this.title = "";
        this.artist = "";
        this.minutes = 0;
        this.seconds = 0;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setMinutes(int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Minutes cannot be negative");
        }
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        if (seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException("Seconds must be between 0 and 59");
        }
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%d:%02d)", title, artist, minutes, seconds);
    }
}
