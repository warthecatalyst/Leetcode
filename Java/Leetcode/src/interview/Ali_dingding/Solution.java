package interview.Ali_dingding;

import java.net.URL;
import java.util.*;

class UrlObject{
    String protocol;
    String domain;
    String path;
    Map<String,String> queries;
    String hash;

    UrlObject(){
        queries = new HashMap<>();
    }

    @Override
    public String toString() {
        return "UrlObject{" +
                "protocol='" + protocol + '\'' +
                ", domain='" + domain + '\'' +
                ", path='" + path + '\'' +
                ", queries=" + queries.toString() +
                ", Hash='" + hash + '\'' +
                '}';
    }
}

public class Solution {
    public static void main(String[] args) {
        UrlObject result = parseURL("http://www.alibole.com/test/list?id=123456&sort=discount#title");
        System.out.println(result);
    }

    // parseUrl(“http://www.alibole.com/test/list?id=123456&sort=discount#title”)
    private static UrlObject parseURL(String URL){
        UrlObject urlObject = new UrlObject();
        String[] parseKeys = new String[]{"Protocol","Domain","Path","Query","Hash"};
        int idx = URL.indexOf("://");
        urlObject.protocol = URL.substring(0,idx);
        idx += 3;
        int nextIdx = URL.indexOf('/',idx);
        urlObject.domain = URL.substring(idx,nextIdx);
        idx = nextIdx+1;
        nextIdx = URL.indexOf('?',idx);
        urlObject.path = URL.substring(idx,nextIdx);
        idx = nextIdx+1;
        nextIdx = URL.indexOf('#',idx);
        String queryStr = URL.substring(idx,nextIdx);
        String[] queries = queryStr.split("&");
        for(String query:queries){
            String[] queryInfo = query.split("=");
            urlObject.queries.put(queryInfo[0],queryInfo[1]);
        }
        urlObject.hash = URL.substring(nextIdx+1);
        return urlObject;
    }
}
