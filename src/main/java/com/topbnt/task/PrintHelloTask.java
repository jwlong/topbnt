package com.topbnt.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.TimerTask;
import java.util.*;

/**
 * Created by longjinwen on 29/08/2017.
 */
public class PrintHelloTask extends TimerTask {
    private static final Log log = LogFactory.getLog(PrintHelloTask.class);
    private static PrintHelloTask instance = new PrintHelloTask();
    public static PrintHelloTask getInstance(){
        return instance;
    }
    public void run() {
        if(log.isDebugEnabled()){
            log.debug("start to print:=======>");
        }
        System.out.println("hello! now time:"+new Date());
    }
}
