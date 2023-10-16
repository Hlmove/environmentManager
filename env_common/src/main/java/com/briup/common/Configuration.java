package com.briup.common;


import com.briup.common.joint.*;

/**
 * Configuration接口提供了配置模块的规范
 * 配置模块需要对其他模块进行创建并初始化赋值，
 * 在初始化赋值时配置信息从配置模块中读取
 * 配置模块 还提供了获取其他模块对象的方法
 */
public interface Configuration {

    /**
     * 获取服务器端的实例
     *
     * @return 服务器端的实例
     */
    Receive getServer() throws Exception;

    /**
     * 获取客户端的实例
     *
     * @return 获取客户端的实例
     */
    Send getClient() throws Exception;

    /**
     * 获取入库模块的实例
     *
     * @return 入库模块的实例
     */
    DBStore getDbStore() throws Exception;

    /**
     * 获取采集模块的实例
     *
     * @return 采集模块的实例
     */
    Gather getGather() throws Exception;

    /**
     * 获取备份模块的实例
     *
     * @return 备份模块的实例
     */
    Backup getBackup() throws Exception;
}
