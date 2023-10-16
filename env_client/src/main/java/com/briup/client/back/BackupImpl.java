package com.briup.client.back;

import com.briup.common.joint.Backup;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Auther: vanse(lc)
 * @Date: 2023/9/27-09-27-09:08
 * @Description：com.briup.client.back
 */
public class BackupImpl implements Backup {
    /**
     * 读备份文件中的数据
     * @param filePath 备份文件的路径
     * @param del      读取完备份文件后是否要删除此备份文件，true为删除，false为不删除。
     *                 在调用时推荐使用本接口中定义的静态常量LOAD_REMOVE、LOAD_UNREMOVE
     * @return
     * @throws Exception
     */
    @Override
    public Object load(String filePath, boolean del) throws Exception {
//        Files Paths nio提供的操作文件工具类
//        Path path = Paths.get(filePath);
//        System.out.println("path = " + path);
//        System.out.println(Files.exists(path));

        // 如果文件不存在 不读数据
        File file = new File(filePath);
        if(!file.exists()){
            return null;
        }

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        Object load = ois.readObject();

        if(del){
            // 读完删除文件
            file.delete();
        }

        ois.close();
        return load;
    }

    /**
     * 将已读的数据存储备份
     * @param filePath 备份文件的路径 文件
     * @param obj      将要写入备份文件的对象
     * @param append   在写入时追加还是覆盖，true为追加，false为覆盖,在调用时推荐使用接口中定义的静态常量STORE_APPEND、STORE_OVERRIDE
     * @throws Exception
     */
    @Override
    public void store(String filePath, Object obj, boolean append) throws Exception {
        // 1.创建对象输出流 100 101  list list2
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath,append));
        // 2.将对象（集合|文件长度）写到文件中
        oos.writeObject(obj);
        oos.flush();
        oos.close();
    }
}
