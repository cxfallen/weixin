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
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	private int ArticleCount;
	private List<Article> articles;
}
