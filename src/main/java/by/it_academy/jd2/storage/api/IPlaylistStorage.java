package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.dto.Song;
import java.util.List;
import java.util.Map;

public interface IPlaylistStorage {
    Map<String, List<Song>> getAllPlaylists();
    List<Song> getPlaylistByEmail(String email);
    void savePlaylist(String email, List<Song> playlist);
}