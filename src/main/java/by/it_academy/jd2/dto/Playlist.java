package by.it_academy.jd2.dto;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private List<String> songs;
    private final String sessionID;

    public Playlist(String sessionID) {
        this.sessionID = sessionID;
        this.songs = new ArrayList<>();
    }

    public List<String> getSongs() {
        return songs;
    }

    public String getSessionID() {
        return sessionID;
    }

    public boolean addSong(String track) {
        return songs.add(track);
    }

    public boolean deleteSong(String track) {
        return songs.remove(track);
    }
}
