package com.briup.server.main;

import com.briup.common.Configuration;
import com.briup.common.entity.Environment;
import com.briup.common.joint.Receive;
import com.briup.server.ConfigurationImpl;
import com.briup.server.dbstore.DBStoreImpl;
import com.briup.server.receive.ReceiveImpl;

import java.io.InputStream;
import java.util.List;

/**
 * @Auther: vanse(lc)
 * @Date: 2023/9/21-09-21-14:21
 * @Description：com.briup.main
 */
public class ServerMain {
    public static void main(String[] args) {
        ConfigurationImpl configuration = new ConfigurationImpl();
//        Receive server1 = configuration.getServer();
        Receive server = new ReceiveImpl();
        server.receive();

    }
}
