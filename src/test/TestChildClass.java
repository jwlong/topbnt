import com.topbnt.menu.mdl.BaseMessage;
import com.topbnt.menu.mdl.NewsMessage;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/14 0014.
 */
public class TestChildClass {
    @Test
    public void  test1 (){
        BaseMessage baseMessage = new BaseMessage();
        NewsMessage newsMessage = new NewsMessage();
        baseMessage.setCreateTime(new Date().getTime());
        System.out.println(newsMessage.getCreateTime()); // 这样是获取不到父类的私有成员变量的值的
    }
    @Test
    public void test2(){
        BaseMessage baseMessage = new BaseMessage();


        NewsMessage newsMessage = new NewsMessage();
        baseMessage = newsMessage;
        baseMessage.setMsgType("sd222f");
        newsMessage.setArticleCount(3);
        newsMessage.setFromUserName("sdfsdf");
        baseMessage.setCreateTime(new Date().getTime());


        System.out.println(newsMessage.getCreateTime()); // 这样是获取不到父类的私有成员变量的值的\
        System.out.println(newsMessage.getMsgType());
    }


}
