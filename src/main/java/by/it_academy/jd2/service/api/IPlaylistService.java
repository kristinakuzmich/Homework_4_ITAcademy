package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.Song;
import java.util.List;

public interface IPlaylistService {
    void setUserEmail(String email);
    String getUserEmail();

    void addSongToUserPlaylist(Song song);
    void removeSongFromUserPlaylist(String songTitle);
    List<Song> getUserPlaylist();
}