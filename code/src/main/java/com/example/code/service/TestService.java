package com.example.code.service;

import com.example.code.entity.User;

import java.util.List;
import java.util.Map;

public interface TestService {
    //Map test();

    List<User> findUserListByMeetId(String meet_id);


}
