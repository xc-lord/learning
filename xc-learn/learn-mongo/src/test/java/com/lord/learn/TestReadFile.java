package com.lord.learn;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by xj_xiaocheng on 2014/12/31.
 */
public class TestReadFile {

    @Test
    public void readFileLine() {
        String fileName = "D:/temp/orderAppraise.txt";
        try {
            InputStream input = new FileInputStream(new File(fileName));
            List<String> list = IOUtils.readLines(input);
            for (String s : list) {
                System.out.println("->" + s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
