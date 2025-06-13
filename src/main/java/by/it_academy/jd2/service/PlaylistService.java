package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.Playlist;
import by.it_academy.jd2.service.api.IPlaylistService;
import by.it_academy.jd2.storage.PlaylistStorageRam;
import by.it_academy.jd2.storage.api.IPlaylistStorage;

import java.util.*;

public class PlaylistService implements IPlaylistService {

    private static final IPlaylistStorage storage = new PlaylistStorageRam();

    @Override
    public void add(Playlist playlist) {
        this.storage.add(playlist);
    }

    @Override
    public Playlist getPlaylist(String sessionID) {
        for (Playlist playlist : storage.getAll()) {
            if (Objects.equals(playlist.getSessionID(), sessionID)) {
                return playlist;
            }
        }
        return null;
    }
}
