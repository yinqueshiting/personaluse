package com.example.code.entity;

import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "yz_goods")
@Data
public class Goods implements Serializable {
    @Id
    @Column(name = "goods_id")
    private String goodsId;
}
