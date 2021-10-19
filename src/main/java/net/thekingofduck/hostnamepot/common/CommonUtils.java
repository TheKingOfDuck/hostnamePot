package net.thekingofduck.hostnamepot.common;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Project: hostnamePot
 * @ClassName: CommonUtils
 * @Author: TheKingOfDuck
 * @Date:2021/10/19 1:43 下午
 * Github:https://github.com/TheKingOfDuck
 * When I wirting my code, only God and I know what it does. After a while, only God knows.
 */
public class CommonUtils {

    public static void info(String ip,String context) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now  = df.format(new Date());

        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        String day  = df2.format(new Date());

        System.out.println(context);
        BufferedWriter out = null;

        File dir = new File("logs");
        if (!dir.exists()){
            dir.mkdir();
        }

        String logname = String.format("%s/logs/hostnamePot_%s.log",System.getProperty("user.dir"),day);

        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(logname, true)));
            out.write(String.format("time: %s\r\nip: %s\r\ninfo: %s\r\n\n",now,ip,context));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
