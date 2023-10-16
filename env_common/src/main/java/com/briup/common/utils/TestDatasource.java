package com.briup.common.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Auther: vanse(lc)
 * @Date: 2023/9/25-09-25-16:07
 * @Description：com.briup.common.utils
 */
public class TestDatasource {
    public static void main(String[] args) throws Exception {
        // 数据源 username password url driver
        // 通过Datasource创建具体连接对象 物理数据源  -> Shoe
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
////        dataSource.setUrl("jdbc:mysql://localhost:3306/env");
//        dataSource.setUrl("jdbc:mysql:///env");
//        dataSource.setUsername("env");
//        dataSource.setPassword("env");
//        Connection connection = dataSource.getConnection();
//        System.out.println("connection = " + connection);
//        DriverManager.getConnection(url,username,password);


        // 利用工厂模式创建数据源  -> NikeFactory
        InputStream in = TestDatasource.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties p = new Properties();
        p.load(in);
        DataSource dataSource = DruidDataSourceFactory.createDataSource(p);

        Connection connection = dataSource.getConnection();
        System.out.println("connection = " + connection);

//        Shoes shoes = new Shoes();
        Shoes shoes = NikeFactory.getShoes();
    }

}

// 工厂模式
class NikeFactory {
    public static Shoes getShoes() {
        return new Shoes();
    }
}
class Shoes {
    String name;
}



