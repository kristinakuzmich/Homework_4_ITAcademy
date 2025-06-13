package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.Playlist;
import by.it_academy.jd2.service.PlaylistService;
import by.it_academy.jd2.service.api.IPlaylistService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(urlPatterns = "/playlist")
public class PlaylistServlet extends HttpServlet {

    private final IPlaylistService service = new PlaylistService();

    private boolean isSessionExists(HttpServletRequest req) {
        if (service.getPlaylist(req.getSession().getId()) == null) {
            service.add(new Playlist(req.getSession().getId()));
            return true;
        }
        return false;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        isSessionExists(req);
        resp.sendRedirect(req.getContextPath() + "/result");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        isSessionExists(req);
        Enumeration<String> params = req.getParameterNames();
        while (params.hasMoreElements()) {
            String param = params.nextElement();
            if (param.equals("email")) {
                req.getSession().setAttribute("email", req.getParameter(param));
            }
            if (param.equals("addSong")) {
                service.getPlaylist(req.getSession().getId()).addSong(req.getParameter(param));
            }
        }
        resp.sendRedirect(req.getContextPath() + "/result");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        isSessionExists(req);
        Enumeration<String> params = req.getParameterNames();
        while (params.hasMoreElements()) {
            String param = params.nextElement();
            if (param.equals("email")) {
                req.getSession().setAttribute("email", req.getParameter(param));
            }
            if (param.equals("addSong")) {
                service.getPlaylist(req.getSession().getId()).addSong(req.getParameter(param));
            }
            if (param.equals("deleleSong")) {
                service.getPlaylist(req.getSession().getId()).deleteSong(req.getParameter(param));
            }
        }
        resp.sendRedirect(req.getContextPath() + "/result");
    }
}
