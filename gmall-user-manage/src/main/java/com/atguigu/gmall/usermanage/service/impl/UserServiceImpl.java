package com.atguigu.gmall.usermanage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.bean.UserInfo;
import com.atguigu.gmall.service.UserService;
import com.atguigu.gmall.usermanage.mapper.UserAddressMapper;
import com.atguigu.gmall.usermanage.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserInfoMapper userMapper;

    @Autowired
    UserAddressMapper userAddressMapper;

    @Override
    public UserInfo getUserInfo(String id) {

        UserInfo userInfo = userMapper.selectByPrimaryKey(id);
        return userInfo;
    }

    @Override
    public List<UserInfo> getUserInfo(UserInfo userInfo) {


        Example example =new Example(UserInfo.class);
        example.createCriteria().andLike("nickName","%"+userInfo.getNickName()+"%");

        List<UserInfo> userInfoList = userMapper.selectByExample(example);
        return userInfoList;
    }

    @Override
    public void addUserInfo(UserInfo userInfo) {
        userMapper.insertSelective(userInfo);
    }



    public void updateUserInfo(UserInfo userInfo){
        userMapper.updateByPrimaryKeySelective(userInfo);


    }


    public void updateUserInfo(UserInfo userInfo,UserInfo userInfoQuery){
        Example example =new Example(UserInfo.class);
        example.createCriteria().andLike("name","%"+userInfoQuery.getName()+"%");

        userMapper.updateByExampleSelective(userInfo,example);


    }


    public void deleteUserInfo(String id){
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UserAddress> getUserAddressList(String userId) {

        UserAddress userAddressQuery =new UserAddress();
        userAddressQuery.setUserId(userId);
        List<UserAddress> userAddressList = userAddressMapper.select(userAddressQuery);

        return userAddressList;
    }

}
