package com.example.code.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "yz_goods_order")
@Setter
@Getter
@ToString
public class GoodsOrder implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "goods_id")
    private String goodsId;

    @Column(name = "count")
    private String count;

    @Column(name = "user_id")
    private String userId;
}
