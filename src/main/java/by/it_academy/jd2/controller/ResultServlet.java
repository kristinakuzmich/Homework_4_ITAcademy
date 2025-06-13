package by.it_academy.jd2.controller;

import by.it_academy.jd2.service.PlaylistService;
import by.it_academy.jd2.service.api.IPlaylistService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/result")
public class ResultServlet extends HttpServlet {

    private final IPlaylistService service = new PlaylistService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        var writer = resp.getWriter();
        writer.write("<html><body>");
        if (service.getPlaylist(req.getSession().getId()) == null) {
            writer.write("<h1>No Person Found</h1>");
            return;
        }
        writer.write("Email:" + req.getSession().getAttribute("email") + "<br><br>");
        writer.write("<h1>Ваш плейлист</h1><ul>");
        for (String track : service.getPlaylist(req.getSession().getId()).getSongs()) {
            writer.write("<li>" + track + "</li>");
        }
        writer.write("</ul></body></html>");
    }
}
