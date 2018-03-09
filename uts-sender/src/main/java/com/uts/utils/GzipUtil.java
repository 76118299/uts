package com.uts.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Administrator on 2018/3/6 0006.
 */
public class GzipUtil {

    /**
     * 压缩文件
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] gzip(byte[] data) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gos = new GZIPOutputStream(bos);
        gos.write(data);
        gos.finish();
        gos.close();
        return bos.toByteArray();
    }

    /**
     * 解压
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] unzip(byte[] data) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        GZIPInputStream gzip=new GZIPInputStream(bis);
        byte[] buf = new byte[1024];

        int num=-1;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((num=gzip.read(buf,0,buf.length))!=-1){
            bos.write(buf,0,num);
        }
        return  bos.toByteArray();
    }
}
