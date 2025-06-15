package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.Song;
import by.it_academy.jd2.service.api.IPlaylistService;
import by.it_academy.jd2.storage.api.IPlaylistStorage;
import java.util.List;
import jakarta.servlet.http.HttpSession;

public class PlaylistService implements IPlaylistService {
    private static final String SESSION_EMAIL_KEY = "userEmail";

    private final IPlaylistStorage storage;
    private final HttpSession session;

    public PlaylistService(IPlaylistStorage storage, HttpSession session) {
        this.storage = storage;
        this.session = session;
    }

    @Override
    public void setUserEmail(String email) {
        session.setAttribute(SESSION_EMAIL_KEY, email);
    }

    @Override
    public String getUserEmail() {
        Object emailObj = session.getAttribute(SESSION_EMAIL_KEY);
        return emailObj != null ? emailObj.toString() : null;
    }

    private String getCurrentEmail() {
        String email = getUserEmail();
        if (email == null) {
            throw new IllegalStateException("User email not set in session");
        }
        return email;
    }

    @Override
    public void addSongToUserPlaylist(Song song) {
        String email = getCurrentEmail();
        List<Song> playlist = storage.getPlaylistByEmail(email);
        playlist.add(song);
        storage.savePlaylist(email, playlist);
    }

    @Override
    public void removeSongFromUserPlaylist(String songTitle) {
        String email = getCurrentEmail();
        List<Song> playlist = storage.getPlaylistByEmail(email);
        playlist.removeIf(s -> s.getTitle().equalsIgnoreCase(songTitle));
        storage.savePlaylist(email, playlist);
    }

    @Override
    public List<Song> getUserPlaylist() {
        String email = getCurrentEmail();
        return storage.getPlaylistByEmail(email);
    }
}