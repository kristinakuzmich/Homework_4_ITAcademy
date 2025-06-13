package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.Playlist;

public interface IPlaylistService {
    void add(Playlist playlist);

    Playlist getPlaylist(String sessionId);
}
