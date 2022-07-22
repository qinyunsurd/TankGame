package com.surd.dp.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 责任链模式
 */
public class ChainMain {
    public static void main(String[] args) {
        HtmlFilter htmlFilter = new HtmlFilter();
        ServiceFilter serviceFilter = new ServiceFilter();
        FilterChain chain = new FilterChain();
        chain.add(htmlFilter).add(serviceFilter);
        Request req = new Request();
        Response res = new Response();
        chain.doFilter(req,res);
    }
}

interface Filter{
    void doFilter(Request request,Response response,FilterChain chain);
}

class FilterChain {
    List<Filter> filters = new ArrayList<>();
    int index=0;
    public FilterChain add(Filter f){
        filters.add(f);
        return this;
    }

    public void doFilter(Request request,Response response){
        if (index == filters.size()) return ;
        Filter filter = filters.get(index);
        index++;
         filter.doFilter(request,response,this);
    }
}

class HtmlFilter implements Filter{

    @Override
    public void doFilter(Request request, Response response,FilterChain chain) {
        request.str = "req1111";
        System.out.println(request.str);
        chain.doFilter(request,response);
        response.str = "res1111";
        System.out.println(response.str);
    }
}

class ServiceFilter implements Filter{

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.str = "req2222";
        System.out.println(request.str);
        chain.doFilter(request,response);
        response.str = "res2222";
        System.out.println(response.str);
    }
}

class Request{
    String str;
}
class Response{
    String str;
}
