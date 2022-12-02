package com.example.casestudy3.service.login;

import com.example.casestudy3.DAO.AdminDAO;
import com.example.casestudy3.model.Singer;
import com.example.casestudy3.model.User;

import javax.servlet.http.HttpServletRequest;;

public class LoginService<E> {
    private static long userID;
    private AdminDAO adminDAO;

    public LoginService() {
        adminDAO = new AdminDAO();
    }
    public Long checkOnline(){
        return userID;
    }

    public int logIn(HttpServletRequest request){
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        if (password.equals("admin") && password.equals("admin")){
            return  1;
        }else {
            for (User user : adminDAO.findAllUser()) {
                if (account.equals(user.getAccount()) && password.equals(user.getPassword())) {
                    userID = user.getId();
                    return 2;
                }
            }
            for (Singer singer : adminDAO.findAllSinger()){
                if (account.equals(singer.getAccount()) && password.equals(singer.getPassword())){
                    userID = singer.getId();
                    return  3;
                }
            }
        }
        return 0;
    }


    public void registration(HttpServletRequest request){

    }
}
