package com.example.code.dto;

import lombok.Data;

@Data
public class OrderInputDTO {

    private String userId;
    private String goodsId;
    private String count;
}
