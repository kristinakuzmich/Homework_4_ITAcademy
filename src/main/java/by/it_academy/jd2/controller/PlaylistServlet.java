package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.Song;
import by.it_academy.jd2.service.PlaylistService;
import by.it_academy.jd2.service.api.IPlaylistService;
import by.it_academy.jd2.storage.api.IPlaylistStorage;
import by.it_academy.jd2.storage.PlaylistStorageRam;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class PlaylistServlet extends HttpServlet {

    private IPlaylistStorage storage;

    @Override
    public void init() throws ServletException {
        storage = new PlaylistStorageRam(); // Инициализация хранилища
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Определение операции
        String operation = request.getParameter("operation");
        HttpSession session = request.getSession();
        IPlaylistService service = new PlaylistService(storage, session);

        if ("setEmail".equals(operation)) {
            String email = request.getParameter("email");
            service.setUserEmail(email);
            response.getWriter().write("Email set: " + email);
        } else if ("addSong".equals(operation)) {
            String title = request.getParameter("title");
            String artist = request.getParameter("artist");
            if (title != null && artist != null) {
                service.addSongToUserPlaylist(new Song(title, artist));
                response.getWriter().write("Song added");
            }
        } else if ("removeSong".equals(operation)) {
            String title = request.getParameter("title");
            if (title != null) {
                service.removeSongFromUserPlaylist(title);
                response.getWriter().write("Song removed");
            }
        }
    }
}