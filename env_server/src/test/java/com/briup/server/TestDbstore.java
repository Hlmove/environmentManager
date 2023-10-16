package com.briup.server;

import com.briup.common.entity.Environment;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * @Auther: vanse(lc)
 * @Date: 2023/9/21-09-21-17:12
 * @Description：com.briup.server
 */
public class TestDbstore {
    @Test
    public void test(){
        long l = 1516323596029l;
        Timestamp timestamp = new Timestamp(l);
        // 日期的天数
        Calendar instance = Calendar.getInstance();
        instance.setTime(timestamp);

        int i = instance.get(Calendar.DAY_OF_MONTH);
        System.out.println("i = " + i);
    }

//    @Test
//    public void test1(){
//        GatherImpl gather = new GatherImpl();
//        List<Environment> environments = gather.gatherData();
//        System.out.println("environments = " + environments);
//    }
}
