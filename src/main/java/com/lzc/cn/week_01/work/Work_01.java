package com.lzc.cn.week_01.work;

import com.lzc.cn.week_01.util.FileOperationUtil;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。
 */

/**
 * @author lc
 * @Date 2021/08/08
 */
public class Work_01 extends ClassLoader{

    private  String path;

    public Work_01(String path){
        this.path = path;
    }
    public Work_01(){
    }
    /**
     * 自定义类加载器去加载属于自己的类
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        Path path = Paths.get(this.path);
        byte[] bytes = FileOperationUtil.getByteFromFile(path);
        //利用255-byte[i] 解码；
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return defineClass(name, bytes, 0, bytes.length);
    }

    /**
     * 根据方法名读取方法
     *
     * @param path
     * @param methodName
     * @return
     */
    public void getOwnerObject(String path ,String methodName) {
        try {
            Class<Object> objectClass = (Class<Object>) findClass(path);
            Method method = objectClass.getMethod(methodName);
            //todo 这里写的时候写错了导致 object is not an instance of declaring class，invoke传入的是对象
            method.invoke(objectClass.newInstance());
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return ;
        }
    }
}
