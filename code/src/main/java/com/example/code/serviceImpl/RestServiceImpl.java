package com.example.code.serviceImpl;


import com.example.code.entity.User;
import com.example.code.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestServiceImpl implements TestService {
   /* @Autowired
    private TestMapper testMapper;
    @Override
    public Map test() {
        List<Map> list = testMapper.test();
        Map resMap = new HashMap();
        resMap.put("user_list",list);
        return resMap;
    }*/

    @Override
    public List<User> findUserListByMeetId(String meet_id) {
        return null;
    }


}
