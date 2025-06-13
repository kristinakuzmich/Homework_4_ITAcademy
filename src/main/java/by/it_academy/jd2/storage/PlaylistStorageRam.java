package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.Playlist;
import by.it_academy.jd2.storage.api.IPlaylistStorage;

import java.util.ArrayList;
import java.util.List;

public class PlaylistStorageRam implements IPlaylistStorage {
    private final List<Playlist> playlists = new ArrayList<>();

    @Override
    public void add(Playlist playlist) {
        this.playlists.add(playlist);
    }

    @Override
    public List<Playlist> getAll() {
        return this.playlists;
    }
}
