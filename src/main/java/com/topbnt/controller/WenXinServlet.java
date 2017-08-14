package com.topbnt.controller;

import com.topbnt.common.utils.CheckUtil;
import com.topbnt.common.utils.Contants;
import com.topbnt.common.utils.MessageUtil;
import com.topbnt.mdl.Article;
import com.topbnt.mdl.ImageMessage;
import com.topbnt.mdl.NewsMessage;
import com.topbnt.mdl.TextMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/13 0013.
 */
public class WenXinServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收微信服务器以Get请求发送的4个参数
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");
        PrintWriter out = resp.getWriter();
        if(CheckUtil.checkSignature(signature,timestamp,nonce)){
            out.print(echostr);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // doGet(req, resp);
        req.setCharacterEncoding("utf-8");
        // add the return charset
        resp.setContentType("text/xml;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String message = "";
        try {
            Map<String,String> map = MessageUtil.xmlToMap(req);
            String fromUserName = map.get("FromUserName");
            String toUserName = map.get("ToUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            String msgId = map.get("MsgId");
            String picUrl = map.get("PicUrl");
            String mediaId = map.get("MediaId");


            //text
//            if(Contants.RESP_TEXT_TYPE.equals(msgType)){
//                text.setContent("you send :"+content);
//                System.out.println(message);
//                message = MessageUtil.textMessageToXMl(text);
//            }//picture
             // 判断 1，2 ，3，4种情况
            if("1".equals(content)){
                System.out.println("图片消息======>");
                NewsMessage newsMessage = new NewsMessage();
                newsMessage.setToUserName(fromUserName);
                newsMessage.setFromUserName(toUserName);
                newsMessage.setCreateTime(new Date().getTime());
                newsMessage.setMsgId(msgId);
                newsMessage.setMsgType(Contants.RESP_NEWS_TYPE);
                //将向用户推送一条图文信息
                Article article = new Article();
                article.setDescription("这个是图文信息");
                article.setPicUrl("http://www.iteye.com/upload/logo/user/603624/2dc5ec35-073c-35e7-9b88-274d6b39d560.jpg");
                article.setTitle("图文消息1");
                article.setUrl("www.topbnt.com");
                List<Article> list = new ArrayList<Article>();
                list.add(article);
                newsMessage.setArticleCount(list.size());
                newsMessage.setArticles(list);
                message = MessageUtil.newsMessageToXML(newsMessage);
                //text.setPicUrl(picUrl);
                //text.setMediaId(mediaId);

            }else if(Contants.RESP_LOCATION_TYPE.equals(msgType)){//location
                String location_x = map.get("Location_X");
                String location_y = map.get("Location_Y");
                String scale = map.get("Scale");
                String label = map.get("Label");
                TextMessage text = new TextMessage();
                text.setToUserName(fromUserName); //此时From User 与 To User的角色会对调
                text.setFromUserName(toUserName);
                text.setCreateTime(new Date().getTime());
                text.setMsgType(Contants.RESP_TEXT_TYPE);
                text.setMsgId((msgId));
                text.setContent("地址："+label+"\n坐标 x:"+location_x+"\n y:"+location_y);
                message = MessageUtil.textMessageToXMl(text);
            }else if(Contants.RESP_IMAGE_TYPE.equals(msgType)){
                message = buildNewsMessage(fromUserName,toUserName,msgId,picUrl,mediaId);
            } else{
                TextMessage text = new TextMessage();
                text.setToUserName(fromUserName); //此时From User 与 To User的角色会对调
                text.setFromUserName(toUserName);
                text.setCreateTime(new Date().getTime());
                text.setMsgType(Contants.RESP_TEXT_TYPE);
                text.setMsgId((msgId));
                text.setContent("哈哈，本人比较懒，你输入的内容我不想理啦!暂只支持文字，表情，图片，地理位置,试试1吧\n,对了你刚才输入的是:"+content);
                message = MessageUtil.textMessageToXMl(text);
            }
            //
            System.out.println(message);
            out.print(message);
        }catch (Exception e){
            message = "服务器异常:"+e.getMessage();
            e.printStackTrace();
        }finally {
            out.close();
        }

    }

    public String buildNewsMessage(String fromUserName,String toUserName,String msgId,String picUrl,String mediaId){
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgId(msgId);
        newsMessage.setMsgType(Contants.RESP_NEWS_TYPE);
        //将向用户推送一条图文信息
        Article article = new Article();
        article.setDescription("这个是图文信息");
        article.setPicUrl(picUrl);
        article.setTitle("图文消息"+mediaId);
        article.setUrl("www.topbnt.com");
        List<Article> list = new ArrayList<Article>();
        list.add(article);
        newsMessage.setArticleCount(list.size());
        newsMessage.setArticles(list);
        return   MessageUtil.newsMessageToXML(newsMessage);
    }
    public String buildImageMessage(){
        ImageMessage imageMessage = new ImageMessage();
        return null;
    }
}
