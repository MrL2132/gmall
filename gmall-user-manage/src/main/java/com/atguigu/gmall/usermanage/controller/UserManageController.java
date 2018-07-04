package com.atguigu.gmall.usermanage.controller;

import com.atguigu.gmall.bean.UserInfo;
import com.atguigu.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserManageController {

    @Autowired
    UserService userService;

    @RequestMapping("/user/{id}")
    public UserInfo getUserInfo(@PathVariable("id") String id){
        UserInfo userInfo = userService.getUserInfo(id);
        return userInfo;
    }



    @RequestMapping("/users")
    public List<UserInfo> getUserInfo(HttpServletRequest request){
        String nickName = request.getParameter("nickName");
        UserInfo userInfo=new UserInfo();
        userInfo.setNickName(nickName);

        List<UserInfo> userInfoList = userService.getUserInfo(userInfo);
        return userInfoList;
    }


    @RequestMapping(value = "/adduser",method = RequestMethod.POST)
    public String saveUserInfo(UserInfo userInfo){
        userService.addUserInfo(userInfo);
        return "success";
    }

    @RequestMapping(value = "/updateuser",method = RequestMethod.POST)
    public String updateUserInfo(UserInfo userInfo){
        UserInfo userInfoQuery=new UserInfo();
        userInfoQuery.setName(userInfo.getName());
        userInfo.setName(null);
        //userService.updateUserInfo(userInfo,userInfoQuery);
        return "success";
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public String deleteUserInfo(UserInfo userInfo){
        userService.deleteUserInfo(userInfo.getId());
        return "success";
    }

}
