package com.example.code.controller;

import com.example.code.queue.FirstSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author zhuzhe
 * @date 2018/5/25 16:00
 * @email 1529949535@qq.com
 */
@RestController
public class SendController {

    @Autowired
    private FirstSender firstSender;

    @PostMapping("/send")
    public String send(String message){
        String uuid = UUID.randomUUID().toString();
        firstSender.send(uuid,message);
        return uuid;
    }
}
