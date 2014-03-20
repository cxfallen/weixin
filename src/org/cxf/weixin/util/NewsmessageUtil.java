package org.cxf.weixin.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cxf.weixin.message.resp.Article;
import org.cxf.weixin.message.resp.NewsMessage;

public class NewsmessageUtil {
//	public static NewsMessage genNewsmessage(String fromUserName, String toUserName) {
//		NewsMessage finalNewsmessage = new NewsMessage();
//		finalNewsmessage.setToUserName(fromUserName);
//		finalNewsmessage.setFromUserName(toUserName);
//		finalNewsmessage.setCreateTime(new Date().getTime());
//		finalNewsmessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
//		
//		List<Article> articleList = new ArrayList<Article>();
//		Article article = new Article();
//		article.setDescription("description");
//		article.setTitle("南昌外国语学校简介");
//		article.setPicUrl("http://cxffirsttest.duapp.com/images/latest.gif");
//		article.setUrl("http://baike.baidu.com/view/901427.htm");
//		articleList.add(article);
//		finalNewsmessage.setArticleCount(articleList.size());
//		finalNewsmessage.setArticles(articleList);
//		
//		return finalNewsmessage;
//	}
}
