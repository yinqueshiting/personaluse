package com.example.code.mapper.test;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TestMapper {
    List<Map> test();
}
