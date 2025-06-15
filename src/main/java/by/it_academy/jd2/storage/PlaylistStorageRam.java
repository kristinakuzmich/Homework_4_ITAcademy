package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.Song;
import by.it_academy.jd2.storage.api.IPlaylistStorage;

import java.util.*;

public class PlaylistStorageRam implements IPlaylistStorage {
    private final Map<String, List<Song>> playlists = new HashMap<>();

    @Override
    public synchronized Map<String, List<Song>> getAllPlaylists() {
        return new HashMap<>(playlists);
    }

    @Override
    public synchronized List<Song> getPlaylistByEmail(String email) {
        return playlists.getOrDefault(email, new ArrayList<>());
    }

    @Override
    public synchronized void savePlaylist(String email, List<Song> playlist) {
        playlists.put(email, playlist);
    }
}