package com.atguigu.gmall.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrderController {

    @Reference
    UserService userService;


    @RequestMapping("/trade")
    @ResponseBody
    public String trade(HttpServletRequest request){
        String userId = request.getParameter("userId");

        List<UserAddress> userAddressList = userService.getUserAddressList(userId);
        String string = JSON.toJSONString(userAddressList);

        return string;

    }
}
