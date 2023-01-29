package pl.put.poznan.pdrive.GUI;

import pl.put.poznan.pdrive.entity.User;
import pl.put.poznan.pdrive.service.UserService;

public class CurrValues {
    UserService userService;
    String curr_userName;

    public void setUserName(String name){
        curr_userName = name;
    }
}
