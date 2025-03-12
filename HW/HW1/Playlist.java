package HW.HW1;

class Playlist {
    private static final int MAX_SONGS = 50;
    private SongRecord[] songs;
    private int size;

    public Playlist() {
        songs = new SongRecord[MAX_SONGS];
        size = 0;
    }

    @Override
    public Object clone() {
        Playlist newPlaylist = new Playlist();
        for (int i = 0; i < size; i++) {
            if (songs[i] != null) {
                SongRecord newSong = new SongRecord();
                newSong.setTitle(songs[i].getTitle());
                newSong.setArtist(songs[i].getArtist());
                newSong.setMinutes(songs[i].getMinutes());
                newSong.setSeconds(songs[i].getSeconds());
                newPlaylist.addSong(newSong, i + 1);
            }
        }
        return newPlaylist;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Playlist other = (Playlist) obj;
        if (size != other.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!songs[i].getTitle().equals(other.songs[i].getTitle()) ||
                !songs[i].getArtist().equals(other.songs[i].getArtist()) ||
                songs[i].getMinutes() != other.songs[i].getMinutes() ||
                songs[i].getSeconds() != other.songs[i].getSeconds()) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        return size;
    }

    public void addSong(SongRecord song, int position) {
        if (position < 1 || position > size + 1) {
            throw new IllegalArgumentException("Position is not within the valid range");
        }
        if (size >= MAX_SONGS) {
            throw new FullPlaylistException("Playlist is full");
        }
        for (int i = size; i >= position; i--) {
            songs[i] = songs[i - 1];
        }
        songs[position - 1] = song;
        size++;
    }

    public void removeSong(int position) {
        if (position < 1 || position > size) {
            throw new IllegalArgumentException("Position is not within the valid range");
        }
        for (int i = position - 1; i < size - 1; i++) {
            songs[i] = songs[i + 1];
        }
        songs[size - 1] = null;
        size--;
    }

    public SongRecord getSong(int position) {
        if (position < 1 || position > size) {
            throw new IllegalArgumentException("Position is not within the valid range");
        }
        return songs[position - 1];
    }

    public void printAllSongs() {
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + songs[i].toString());
        }
    }

    public static Playlist getSongsByArtist(Playlist originalList, String artist) {
        if (originalList == null || artist == null) {
            return null;
        }
        Playlist newPlaylist = new Playlist();
        for (int i = 0; i < originalList.size; i++) {
            if (originalList.songs[i].getArtist().equals(artist)) {
                newPlaylist.addSong(originalList.songs[i], newPlaylist.size + 1);
            }
        }
        return newPlaylist;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append((i + 1) + ". " + songs[i].toString()).append("\n");
        }
        return sb.toString();
    }
}

class FullPlaylistException extends RuntimeException {
    public FullPlaylistException(String message) {
        super(message);
    }
}