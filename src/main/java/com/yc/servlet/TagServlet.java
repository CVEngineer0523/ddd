package com.yc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Tag;
import com.yc.biz.TagBiz;
import com.yc.biz.impl.TagBizImpl;


public class TagServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
	TagBiz tagBiz=new TagBizImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if( "findAllTag".equals(op)){
			findAllTagOP(request,response);
		}
	}
	//显示所有标签
	private void findAllTagOP(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Tag> list= this.tagBiz.findAllTag();
		super.outJson(list, response);
	}
//	//发布影片信息
//	private void insetfilminfoOP(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		Filminfo fi=(Filminfo) super.parseRequest(request, Filminfo.class);
//		try {
//			this.filminfoBiz.addFilmInfo(fi);
//		} catch (Exception e) {
//			e.printStackTrace();
//			super.outJson(0, response);
//		}
//		super.outJson(1, response);
//		
//	}
}
