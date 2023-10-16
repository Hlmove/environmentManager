package com.briup.server;

import com.briup.common.Configuration;
import com.briup.common.ConfigurationAware;
import com.briup.common.PropertiesAware;
import com.briup.common.joint.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Auther: vanse(lc)
 * @Date: 2023/9/27-09-27-15:15
 * @Description：com.briup.server
 */
public class ConfigurationImpl implements Configuration {
    // 文件只会加载一次 对象应该是单例

    static Map<String,Object> map = new HashMap<>();
    static Properties properties = new Properties();
    /**
     * 解析server下的config.xml文件 把对象获取
     * @return
     * @throws Exception
     */
    static  {
        // 1.加载config文件
        // 2.把每个节点的名字和class属性
//            map.put("server",Class.forName("class").newInstance());
        // 3.获取每个节点的子节点（属性）
//            properties.put("属性名字","属性值");
        // 4.遍历map中的所有值（所有对象）
        for (Object obj : map.values()) { // 接收 入库对象
            if(obj instanceof ConfigurationAware){
                ConfigurationAware configurationAware = (ConfigurationAware) obj;
                configurationAware.setConfiguration(configuration);
            }
            if(obj instanceof PropertiesAware){
                PropertiesAware propertiesAware = (PropertiesAware) obj;
                propertiesAware.init(properties);
            }
        }
    }
    @Override
    public Receive getServer() throws Exception {
        return (Receive) map.get("server");
    }

    @Override
    public Send getClient() throws Exception {
        return null;
    }

    @Override
    public DBStore getDbStore() throws Exception {
        return (DBStore) map.get("dbStore");
    }

    @Override
    public Gather getGather() throws Exception {
        return null;
    }

    @Override
    public Backup getBackup() throws Exception {
        return null;
    }
}
