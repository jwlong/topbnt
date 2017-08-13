package com.topbnt.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/8/13 0013.
 */
public class IOUtils {
    public static void close(InputStream in){
        if(in != null){
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(InputStream in, OutputStream out){
        try{
            if(in !=null ){
                in.close();
            }
            if(out != null){
                out.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
