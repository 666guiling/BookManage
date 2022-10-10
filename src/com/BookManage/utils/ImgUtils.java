package com.BookManage.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.util.UUID;

/*
@ClassName : ImgUtils
@Author : 不会吧
@Date: 2022/9/29 10:33
@Description : 
*/
public class ImgUtils {
    public static String getUrl(HttpServletRequest request, Part part){
        String relativeParentPath = "/asserts/image";
        String realPath = request.getServletContext().getRealPath(relativeParentPath);
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        String fileName = part.getSubmittedFileName();
        UUID uuid = UUID.randomUUID();
        fileName = uuid.toString()+"_"+ fileName;
        //目标文件的抽象对象
        File file1 = new File(realPath, fileName);
        try {
            //拷贝
            part.write(file1.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imgUrl = request.getScheme()+"://"+
                request.getServerName()+":"+
                request.getServerPort()+
                request.getContextPath()+
                relativeParentPath+"/"+fileName;
        //判断是否传入图片
        String[] str = imgUrl.split("_");
        String url = "";
        for (int i = 0; i < str.length; i++) {
            url = str[str.length-1];
        }
        if (url.equals("null")){
            return "null";
        }
        return imgUrl;
    }
}
