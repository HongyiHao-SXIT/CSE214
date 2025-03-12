package HW.HW1;

import java.util.Scanner;

class PlaylistOperations {
    public static void main(String[] args) throws NumberFormatException {
        Playlist playlist = new Playlist();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("A <Title> <Artist> <Minutes> <Seconds> <Position> - Add Song");
            System.out.println("G <Position> - Get Song");
            System.out.println("R <Position> - Remove Song");
            System.out.println("P - Print All Songs");
            System.out.println("B <Artist> - Print Songs By Artist");
            System.out.println("S - Size");
            System.out.println("Q - Quit");
            System.out.print("Enter your command: ");
            String input = scanner.nextLine().trim().toUpperCase();
            String[] parts = input.split(" ");

            switch (parts[0]) {
                case "A":
                    if (parts.length != 6) {
                        System.out.println("Invalid input for adding song. Please try again.");
                        continue;
                    }
                    try {
                        String title = parts[1];
                        String artist = parts[2];
                        int minutes = Integer.parseInt(parts[3]);
                        int seconds = Integer.parseInt(parts[4]);
                        int position = Integer.parseInt(parts[5]);

                        SongRecord song = new SongRecord();
                        song.setTitle(title);
                        song.setArtist(artist);
                        song.setMinutes(minutes);
                        song.setSeconds(seconds);

                        playlist.addSong(song, position);
                        System.out.printf("Added Song: Title=%s, Artist=%s, Length=%d:%02d at Position=%d\n",
                                title, artist, minutes, seconds, position);
                    } catch (IllegalArgumentException | FullPlaylistException e) {
                        System.out.println("Error adding song: " + e.getMessage());
                    }
                    break;
                case "G":
                    if (parts.length != 2) {
                        System.out.println("Invalid input for getting song. Please try again.");
                        continue;
                    }
                    try {
                        int position = Integer.parseInt(parts[1]);
                        SongRecord song = playlist.getSong(position);
                        System.out.printf("Song at Position %d: %s\n", position, song.toString());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error getting song: " + e.getMessage());
                    }
                    break;
                case "R":
                    if (parts.length != 2) {
                        System.out.println("Invalid input for removing song. Please try again.");
                        continue;
                    }
                    try {
                        int position = Integer.parseInt(parts[1]);
                        playlist.removeSong(position);
                        System.out.printf("Removed song at Position %d.\n", position);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error removing song: " + e.getMessage());
                    }
                    break;
                case "P":
                    playlist.printAllSongs();
                    break;
                case "B":
                    if (parts.length != 2) {
                        System.out.println("Invalid input for printing songs by artist. Please try again.");
                        continue;
                    }
                    String artist = parts[1];
                    Playlist artistPlaylist = Playlist.getSongsByArtist(playlist, artist);
                    if (artistPlaylist != null && artistPlaylist.size() > 0) {
                        artistPlaylist.printAllSongs();
                    } else {
                        System.out.printf("No songs found by artist %s.\n", artist);
                    }
                    break;
                case "S":
                    System.out.printf("The size of the playlist is %d.\n", playlist.size());
                    break;
                case "Q":
                    System.out.println("Quitting the program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }
}