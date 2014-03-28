package org.cxf.weixin.message.resp;

import java.util.List;

public class NewsMessage extends BaseMessage{

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
	
	private int ArticleCount;
	private List<Article> Articles;
}
