package com.ego.manage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

/**
 * @program: ego-parent
 * @author: ShyBlue
 * @create: 2020-04-03 15:25
 **/
public class Demo {
    /**
     * 新增5
     * @throws SolrServerException
     * @throws IOException
     */
    public void testInsert() throws SolrServerException, IOException{
        CloudSolrClient client = new CloudSolrClient("192.168.249.131:2181,192.168.249.131:2182,192.168.249.131:2183");
        client.setDefaultCollection("collection1");
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", "005");
//		doc.addField("bjsxt", "java和大数据培训");
        doc.addField("bjsxt1", "大数据4");

        client.add(doc);
        client.commit();
    }

    public void testDelete() throws SolrServerException, IOException{
        CloudSolrClient client = new CloudSolrClient("192.168.249.131:2181,192.168.249.131:2182,192.168.249.131:2183");
        client.setDefaultCollection("collection1");
        client.deleteById("001");
        client.commit();
    }

    public void testQuery() throws SolrServerException, IOException{
        CloudSolrClient client = new CloudSolrClient("192.168.249.131:2181,192.168.249.131:2182,192.168.249.131:2183");
        client.setDefaultCollection("collection1");

        //可视化界面左侧条件
        SolrQuery params = new SolrQuery();
        //设置q
        params.setQuery("bjsxtall:*");
        //设置分页
        //从第几条开始查询,从0开始
        params.setStart(0);
        //查询几个
        params.setRows(10);


        //启动高亮
        params.setHighlight(true);
        //设置高亮列
        params.addHighlightField("bjsxt");
        //设置前缀
        params.setHighlightSimplePre("<span style='color:red;'>");
        //设置后缀
        params.setHighlightSimplePost("</span>");

        //相当于点击查询按钮, 本质,向solr web服务器发送请求,并接收响应. query对象里面包含了返回json数据
        QueryResponse response = client.query(params);

        Map<String, Map<String, List<String>>> hh = response.getHighlighting();


        //取出docs{}
        SolrDocumentList solrList = response.getResults();

        for (SolrDocument doc : solrList) {
            System.out.println(doc.getFieldValue("id"));
            System.out.println("未高亮:"+doc.getFieldValue("bjsxt"));
            Map<String, List<String>> map = hh.get(doc.getFieldValue("id"));
            System.out.println(map);
            //list可能为null
            List<String> list = map.get("bjsxt");
            System.out.println(list);
            if(list!=null&&list.size()>0){
                System.out.println("高亮:"+list.get(0));
            }else{
                System.out.println("没有高亮内容");
            }
            System.out.println(doc.getFieldValue("bjsxt1"));
        }

    }

}