package web_service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 1000 ; i++) {
        try {
            HttpURLConnection httpConn = (HttpURLConnection) new URL("http://localhost:9090/entity").openConnection();

            byte[] buffer = ("" +
                    "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<Product>\n" +
                    "   <Type>Bag</Type>\n" +
                    "   <Brand>DOLCE & GABBANA</Brand>\n" +
                    "   <Comment>Bag<Comment>\n" +
                    "</Product>").getBytes();
            httpConn.setRequestProperty("Content-lenght", String.valueOf(buffer.length));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");

            try {
                httpConn.setRequestMethod("POST");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            OutputStream out = null;

            try {
                out = httpConn.getOutputStream();
                out.write(buffer);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String result = "123";

            try {
                InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), Charset.forName("UTF-8"));
                BufferedReader in = new BufferedReader(isr);
                String responce = "";

                while ((responce = in.readLine()) != null) {
                    result += responce;
                }
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
}
