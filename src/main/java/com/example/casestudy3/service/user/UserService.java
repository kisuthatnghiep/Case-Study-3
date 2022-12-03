package com.example.casestudy3.service.user;

import com.example.casestudy3.DAO.AdminDAO;
import com.example.casestudy3.DAO.UserDAO;
import com.example.casestudy3.controller.LogInServlet;
import com.example.casestudy3.controller.UserServlet;
import com.example.casestudy3.model.Playlist;
import com.example.casestudy3.model.Singer;
import com.example.casestudy3.model.Song;
import com.example.casestudy3.model.User;
import com.example.casestudy3.service.login.LoginService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;

public class UserService {
    private UserServlet userServlet;
    private UserDAO userDAO;
    private AdminDAO adminDAO;
    private LoginService loginService;

    public UserService() {
        userServlet = new UserServlet();
        userDAO = new UserDAO();
        adminDAO = new AdminDAO();
        loginService = new LoginService();
    }
    public boolean createPlayList(HttpServletRequest request){
        String name = request.getParameter("name");
        String avatar = request.getParameter("avatarSRC");
        Date date = Date.valueOf(LocalDate.now());
        return userDAO.createPlayList(new Playlist(name, avatar,date,loginService.checkOnline()));
    }
    public User detailUser(HttpServletRequest request){
        return adminDAO.findByIdUser(loginService.checkOnline());
    }
    public boolean buySong(HttpServletRequest request)  {
        try {
            long songId = Long.parseLong(request.getParameter("id"));
            long playListId = Long.parseLong(request.getParameter("playList"));
            Song song = adminDAO.findByIdSong(songId);
            Singer singer = adminDAO.findByIdSinger(song.getSingerId());
            Long userId = loginService.checkOnline();
            double wallet = adminDAO.findByIdUser(userId).getWallet() - song.getPrice();
            double income = singer.getIncome() + song.getPrice();
            userDAO.buySong(wallet,income,playListId,userId, singer.getId(), songId);
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
