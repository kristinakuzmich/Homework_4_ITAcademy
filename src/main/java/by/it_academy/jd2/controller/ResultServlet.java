package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.Song;
import by.it_academy.jd2.service.api.IPlaylistService;
import by.it_academy.jd2.service.PlaylistService;
import by.it_academy.jd2.storage.api.IPlaylistStorage;
import by.it_academy.jd2.storage.PlaylistStorageRam;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ResultServlet extends HttpServlet {

    private IPlaylistStorage storage;

    @Override
    public void init() throws ServletException {
        storage = new PlaylistStorageRam(); // Инициализация
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        IPlaylistService service = new PlaylistService(storage, session);

        String email = service.getUserEmail();
        if (email == null) {
            response.getWriter().write("Email not set. Please set your email first.");
            return;
        }
        List<Song> playlist = service.getUserPlaylist();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>User Playlist</title></head><body>");
        out.println("<h2>Плейлист пользователя: " + email + "</h2>");
        out.println("<ul>");
        for (Song song : playlist) {
            out.println("<li>" + song.getTitle() + " - " + song.getArtist() + "</li>");
        }
        out.println("</ul>");
        out.println("</body></html>");
    }
}