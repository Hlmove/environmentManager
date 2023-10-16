package com.briup.server.dbstore;


import com.briup.common.entity.Environment;
import com.briup.common.joint.DBStore;
import com.briup.common.utils.ConnectionUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: vanse(lc)
 * @Date: 2023/9/21-09-21-11:10
 * @Description：将采集到的数据入库
 */
public class DBStoreImpl implements DBStore {
    //    String driver = "com.mysql.cj.jdbc.Driver";
//    String url = "jdbc:mysql://localhost:3306/env?useSeverPrepStmts=true";
//    String username = "env";
//    String password = "env";
    Logger logger = Logger.getLogger(DBStoreImpl.class);

    public void insertData(List<Environment> list) {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        long start = System.currentTimeMillis();
        int last_day = -1;
        try {
//            // 1.注册驱动
//            Class.forName(driver);
//            // 2.获取连接
//            conn = DriverManager.getConnection(url, username, password);
            conn = ConnectionUtil.getConnetion();
            // 手动提交事务
            conn.setAutoCommit(false);
            // 3.获取sql执行器并准备sql stmt ps
            // sql根据天数不同而不同

            Calendar instance = Calendar.getInstance();
//            for (Environment environment : list) {
//
//            }
            for (int i = 0; i < list.size(); i++) {
                Environment environment = list.get(i);
                // 每个环境的日 -》 表  19  -> env_detail_19
                // 20 env_detail_19
                instance.setTime(environment.getGatherDate());
                int day = instance.get(Calendar.DAY_OF_MONTH);
                if (last_day == -1) { // 第一条数据 last_day : -1
                    // sql变化 last_day=-1 last_day 19 19 19
                    String sql = "insert into env_detail_" + day + " values(?,?,?,?,?,?,?,?,?,?)";
                    // ps变化 19 ps -> 19 批处理缓存区 100
                    // ps变化 20 ps -> 20 批处理缓存区 ps的
                    // 检测day变化的时机
                    // day变化 ps变化 上一次的Ps缓冲区提交
                    // last_day19  day20
                    // 新的ps从0开始计数，新的批处理
                    ps = conn.prepareStatement(sql);
                } else {  // 第二次 last_day: 19  day: 20
                    if (last_day != day) {
                        // 上一次的ps
                        ps.executeBatch(); // 上一个ps的缓存执行完 count: 180
                        // 新的ps
                        count = 0;
                        String sql = "insert into env_detail_" + day + " values(?,?,?,?,?,?,?,?,?,?)";
                        ps = conn.prepareStatement(sql);
                    }
                }
                ps.setString(1, environment.getName());
                ps.setString(2, environment.getSrcId());
                ps.setString(3, environment.getDesId());
                ps.setString(4, environment.getDevId());
                ps.setString(5, environment.getSensorAddress());
                ps.setInt(6, environment.getCount());
                ps.setString(7, environment.getCmd());
                ps.setInt(8, environment.getStatus());
                ps.setFloat(9, environment.getData());
                ps.setTimestamp(10, environment.getGatherDate());
                // 批处理
                ps.addBatch();
                // 每200条提交一次
                count++;
                if (count % 200 == 0) {
                    ps.executeBatch();
                }
                last_day = day;   // 数据1 19   数据2 20
            }
            ps.executeBatch();
            conn.commit();
            long end = System.currentTimeMillis();
            logger.debug("入库时间" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } finally {
            // 6.关闭资源
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}




