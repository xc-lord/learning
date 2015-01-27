package com.lord.learn.socket;

import java.io.BufferedReader;

/**
 * Created by xj_xiaocheng on 2015/1/21.
 */
public class TalkBackgroundThread implements Runnable {

    private String name = "";
    private BufferedReader is;

    public TalkBackgroundThread(String name, BufferedReader is) {
        this.name = name;
        this.is = is;
    }

    @Override
    public void run() {
        try {
            while (true) {
                //在系统标准输出上打印读入的字符串
                String line = is.readLine();
                if(line != null) {
                    System.err.println(name + "说:" + line);
                }

                if("bye".equals(line))
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
