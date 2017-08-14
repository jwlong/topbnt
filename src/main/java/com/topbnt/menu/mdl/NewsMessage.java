package com.topbnt.menu.mdl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/13 0013.
 */
public class NewsMessage extends BaseMessage{
    public NewsMessage(){
        super();
    }
    private int ArticleCount ;
    private List<Article> Articles = new ArrayList<Article>();

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }
}
