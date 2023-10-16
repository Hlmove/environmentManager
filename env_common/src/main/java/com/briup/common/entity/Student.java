package com.briup.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//
@Data  // set/get tosttring hashcode euqals
@NoArgsConstructor // 无参构造器
@AllArgsConstructor // 全参构造器
public class Student implements Serializable { // 克隆标志
    private static final long serialVersionUID = 1L;
    int id;
    String name;
    int age;
}
