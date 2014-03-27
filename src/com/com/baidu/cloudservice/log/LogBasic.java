package com.baidu.cloudservice.log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.cxf.weixin.Service.BaiduMusicService;
import org.cxf.weixin.Service.CoreService;

import java.io.IOException;

/**
 * Log示例，�?过该示例可熟悉BAE平台Log的使? */
public class LogBasic extends HttpServlet { 
 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        try {
			Logger logger = Logger.getLogger("java");
			//打印�?��Trace语句
			logger.trace("This is a TRACE log");
			//打印�?��Notice语句
			logger.info("This is a NOTICE log");
//			logger.info(BaiduTranslateService.message);
//			logger.info(HistoryTodayUtil.message);
			logger.info("Message:" + CoreService.message);
			logger.info("result:" + CoreService.result);
//			logger.info("title " + CoreService.title);
//			logger.info("author " + CoreService.author);
//			logger.info("statickeyword " + CoreService.statickeyword);
//			logger.info("music: " + BaiduMusicService.musicstatic);
//			logger.info("musicInCService:" + CoreService.musicInCServiceString);
			//打印�?��Debug语句
			logger.debug("This is a DEBUG log");
			//打印�?��Warning语句
			logger.warn("This is a WARNING log");
			//打印�?��Trace语句
			logger.fatal("This is a FATAL log");
			resp.getWriter().println("Log OK, Please Check Log Service For Detail Infomation");
        }catch(Exception e){
        	e.printStackTrace(resp.getWriter());
        }
	}

}
