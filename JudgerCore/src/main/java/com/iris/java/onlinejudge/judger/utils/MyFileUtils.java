package com.iris.java.onlinejudge.judger.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class MyFileUtils {


    /**
     * 给定文件路径，给定输入内容，创建一个新文件
     *（输入路径名，创建一个新文件；如果父目录不存在，则先创建目录，再创建文件）
     * https://blog.csdn.net/zjsdrs/article/details/80761613
     * @param filePath
     * @param fileContent
     */
    public void createNewFileRecursive(String filePath,String fileContent) throws Exception {

        File file = new File(filePath);


        // 先验证父目录是否存在
        setFatherDirectory(file.getParentFile());

        // 写入文件
        //（apache commons io库，让文件读写更便捷）
        FileOutputStream outputStream = new FileOutputStream(new File(filePath));
        IOUtils.write(fileContent, outputStream);
        IOUtils.closeQuietly(outputStream);

    }


    /**
     * 读取任意文件的内容（输出 String 字符串）
     * @param filePath
     * @return
     * @throws Exception
     */
    public String readFileContent(String filePath) throws Exception {

        FileInputStream inputStream = new FileInputStream(filePath);

        String fileContent = IOUtils.toString(inputStream);

        inputStream.close();

        return fileContent;
    }

    /**
     * 创建目录 && 设置权限
     * @param parentFileDir
     */
    public void setFatherDirectory(File parentFileDir){

        if(!parentFileDir.exists()){
            boolean flag = parentFileDir.mkdirs();
            if(!flag){
                // TODO: 改成抛异常
                System.out.println("Failed to create directory: " +  parentFileDir.getPath());
            }
        }
        setWorkDirectoryPermission(parentFileDir);
    }


    /**
     * TODO: 设置目录权限
     * @param workDirectory
     */
    public void setWorkDirectoryPermission(File workDirectory){
        ;
    }
}
