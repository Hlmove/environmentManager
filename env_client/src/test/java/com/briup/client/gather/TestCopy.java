package com.briup.client.gather;

import org.junit.Test;

/**
 * @Auther: vanse(lc)
 * @Date: 2023/9/21-09-21-16:45
 * @Description：com.briup.client.gather
 */
public class TestCopy {
    @Test
    public void test() throws CloneNotSupportedException {
        Student s = new Student(1,"tom",18);
        Object clone = s.clone();
        System.out.println("s = " + s);
        System.out.println("clone = " + clone);

    }
}
// 浅拷贝（没有引用其他类型）  深拷贝
class Student implements Cloneable{ // 克隆标志
    int id;
    String name;
    int age;
//    User user; id username password
    public Student(){}
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

//    @Override
//    public String toString() {
//        return "Student{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", age=" + age +
//                '}';
//    }
}
