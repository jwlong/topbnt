package com.topbnt.common.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.topbnt.menu.mdl.Article;
import com.topbnt.menu.mdl.ImageMessage;
import com.topbnt.menu.mdl.NewsMessage;
import com.topbnt.menu.mdl.TextMessage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/13 0013.
 */
public class MessageUtil {

    /**
     * make XML file convert to Map collections
     * @param request http request
     * @return
     */
    public static Map<String,String> xmlToMap(HttpServletRequest request){
        Map<String,String> map = new HashMap<String, String>();
        InputStream inputStream = null;
        try {
            inputStream =  request.getInputStream();
            //需要dom4j进行解析
            SAXReader reader = new SAXReader();
            Document document =  reader.read(inputStream);
            Element root = document.getRootElement();
            List<Element> elementList = root.elements();
            for(Element e : elementList){
                map.put(e.getName(),e.getText());
                System.out.println("Element Name:=====>"+e.getName()+" ========>value:"+e.getText());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            IOUtils.close(inputStream);
        }
        return  map;
    }

    /**
     * 将文本消息对象转换成XML
     * @param textMessage
     * @return
     */
   public static String textMessageToXMl(TextMessage textMessage){
       xstream.alias("xml",textMessage.getClass());
        return xstream.toXML(textMessage);
    }
//   public static String textMessageToXMl(TextMessage textMessage){
//       StringBuffer sb = new StringBuffer();
//       //这里的ToUser 要与FromUser 进行对调
//       sb.append("<xml><ToUserName><![CDATA[");
//       sb.append(textMessage.getToUserName());
//       sb.append("]]></ToUserName><FromUserName><![CDATA[");
//       sb.append(textMessage.getFromUserName());
//       sb.append("]]></FromUserName><CreateTime>");
//       sb.append(textMessage.getCreateTime());
//       sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");
//       sb.append(textMessage.getContent());
//       sb.append("]]></Content><MsgId>");
//       sb.append(textMessage.getMsgId());
//       sb.append("</MsgId></xml>");
//       return sb.toString();
//   }
    public static String textMessageToJSON(TextMessage textMessage){
        return "";
    }

    /**
     * 图文消息 转换成xml
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXML(NewsMessage newsMessage){
        xstream.alias("xml",newsMessage.getClass());
        xstream.alias("item",new Article().getClass());
        return xstream.toXML(newsMessage);
    }
    public static String imageMessageToXml(ImageMessage imageMessage){
        xstream.alias("xml",imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }
    /**
     * 对象到xml的处理
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

}
