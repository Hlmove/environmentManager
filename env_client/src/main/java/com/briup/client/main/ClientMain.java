package com.briup.client.main;

import com.briup.client.gather.GatherImpl;
import com.briup.client.send.SendImpl;
import com.briup.common.entity.Environment;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @Auther: vanse(lc)
 * @Date: 2023/9/21-09-21-14:21
 * @Description：com.briup.main
 */
public class ClientMain {
    public static void main(String[] args) {
        SendImpl client = new SendImpl();
        GatherImpl gather = new GatherImpl();
        List<Environment> environments = gather.gatherData();
        if(environments != null && environments.size() > 0){
            client.send(environments);
        }
    }
}
    