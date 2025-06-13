package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.dto.Playlist;

import java.util.List;

public interface IPlaylistStorage {
    void add(Playlist playlist);

    List<Playlist> getAll();
}
