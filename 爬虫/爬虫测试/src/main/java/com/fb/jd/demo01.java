package com.fb.jd;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class demo01 {

    public static void main(String[] args) throws Exception {
        Document document = Jsoup.connect("https://www.jd.com/?cu=true&utm_source" +
                "=www.hao123.com&utm_medium=tuiguang&utm_campaign=t_1000003625" +
                "").get();
        System.out.println(document.select("navitems ul li").attr("clstag"));


    }

}
