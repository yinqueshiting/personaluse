package com.example.code.service;

import com.example.code.entity.HhUser;

import java.util.List;
import java.util.Map;

public interface TestService {
    //Map test();

    List<HhUser> findUserListByMeetId(String meet_id);


}
