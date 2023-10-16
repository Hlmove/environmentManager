package com.briup.server.receive;


import com.briup.common.Configuration;
import com.briup.common.ConfigurationAware;
import com.briup.common.PropertiesAware;
import com.briup.common.entity.Environment;
import com.briup.common.joint.DBStore;
import com.briup.common.joint.Receive;
import com.briup.server.ConfigurationImpl;
import com.briup.server.dbstore.DBStoreImpl;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Auther: vanse(lc)
 * @Date: 2023/9/21-09-21-14:05
 * @Description：com.briup.server
 */
@Log4j
public class ReceiveImpl implements Receive, PropertiesAware {
    //    Logger logger = Logger.getLogger(ReceiveImpl.class);
    Properties pro;
    public static final int port = 9999;

    public void receive() {
//        int port = pro.get("receive-port");
        ServerSocket serverSocket = null;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));
        try {
            serverSocket = new ServerSocket(port);
            log.info("服务器 " + port + " 启动");
            // 服务端 不关闭
            while (true) {
                // accept方法 导致阻塞
                Socket socket = serverSocket.accept();
                // 每个线程拥有1个socket
//                new Thread(new ReceiveThread(socket)).start();
                threadPoolExecutor.submit(new ReceiveThread(socket));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
                if (threadPoolExecutor != null) {
                    threadPoolExecutor.shutdown();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void init(Properties properties) throws Exception {
        pro = properties;
    }
}

// 每个线程拥有1个socket
@Log4j
class ReceiveThread implements Runnable, ConfigurationAware {
    private Socket socket;

    //    Logger logger = Logger.getLogger(ReceiveThread.class);
    public ReceiveThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        // 每个线程做的业务
        // 接收数据
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            List<Environment> list = (List<Environment>) ois.readObject();
            log.debug("服务端接收数据" + list.size());
            // 入库数据
//            DBStore dbStore1 = new ConfigurationImpl().getDbStore();
            DBStore dbStore = new DBStoreImpl();
            dbStore.insertData(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    Configuration con;

    @Override
    public void setConfiguration(Configuration configuration) throws Exception {
        con = configuration;
    }
}
