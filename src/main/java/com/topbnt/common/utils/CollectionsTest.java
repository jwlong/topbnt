package com.topbnt.common.utils;



import java.util.HashMap;
import java.util.Map;

/**
 * Created by longjinwen on 29/08/2017.
 * 此类测试的是HashMap
 */
public class CollectionsTest {
    //听说HashMap不是线程安全的 ，我来试试
    public static void main(String[] args) throws InterruptedException {
        A  testA = new A();
        initMapData(testA);
        Thread t1= new Thread(new T1(testA));
        t1.start();
        Thread t2 = new Thread(new T1(testA));
        t2.start();
        Thread t3 = new Thread(new T1(testA));
        t3.start();
    }

    public static void initMapData(A a) throws InterruptedException {
        Map<String,Object> map =  new HashMap<String, Object>();
        for(int i= 1;i<=5;i++){
            map.put(String.valueOf(i),i);
            Thread.sleep(1000);
        }
        a.setMap(map);
    }
}
class T1 implements Runnable{
    private A a;
    public T1(A a){
        this.a = a;
    }



    public void run() {
        Map<String,Object> tmap = a.getMap();
        try {
            Thread.sleep(500);
            for(Map.Entry entry : tmap.entrySet()){
                    tmap.put(entry.getKey().toString(),"改一下");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class A {
    private int a;
    private int b;
    private Map<String,Object> map = new HashMap<String, Object>();
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
