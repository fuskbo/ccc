package com.fb.travel;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;

public class demo01 {
    public static void main(String[] args) throws Exception {
        String encode = "国内";
        encode = URLEncoder.encode(encode);
        Document document = Jsoup.connect("http://www.jinmalvyou.com/search/index/view_type/1/keyword/" + encode + "/p/1.html").get();
//        Element element = document.select(".page .end").last();
//        String val = element.attr("href");
//        val = val.substring(val.lastIndexOf("/") + 1, val.lastIndexOf("."));
////        System.out.println(val);

        Element end = document.select(".end").last();
        String endhref = end.attr("href");
         endhref =endhref.substring(endhref.lastIndexOf("/")+1,endhref.indexOf("."));
        System.out.println(endhref);
        Integer page = Integer.valueOf(endhref);

        for (Integer i = 1; i < page; i++) {
            Document document02 = Jsoup.connect("http://www.jinmalvyou.com/search/index/view_type/1/keyword/" + encode + "/p/"+ i+".html").get();


            System.out.println("第"+i+"页");
            Elements elements = document02.select(".rl-b2 li");


        for (Element element1 : elements) {
//

            String src = element1.select(".pro-img img").attr("src");
            String imgname = src.substring(src.lastIndexOf("/") + 1);

            //模拟浏览器客户端
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //创建请求
            HttpGet httpGet = new HttpGet("http:" + src);
            //发送请求
            CloseableHttpResponse response = httpClient.execute(httpGet);
            //c将所有数据封装到entity
            HttpEntity entity = response.getEntity();

            InputStream is = entity.getContent();
            FileOutputStream os = new FileOutputStream("E:/爬虫/" + imgname);
            IOUtils.copy(is, os);
            is.close();
            os.close();
        }

        }

    }
}
