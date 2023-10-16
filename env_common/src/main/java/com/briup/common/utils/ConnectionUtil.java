package com.briup.common.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Auther: vanse(lc)
 * @Date: 2023/9/22-09-22-16:03
 * @Description：返回连接
 */
public class ConnectionUtil {
    static DataSource dataSource;
    static {
        // 文件 -》 流
        InputStream in = null;
        try {
            in = ConnectionUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(in);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static Connection getConnetion() throws SQLException {
        return dataSource.getConnection();
    }

//    public static void main(String[] args) throws SQLException {
//        System.out.println(getConnetion());
//    }
}
