package com.yc.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Favorite;
import com.yc.bean.Tag;
import com.yc.biz.FavoriteBiz;
import com.yc.biz.TagBiz;
import com.yc.biz.impl.FavoriteBizImpl;
import com.yc.biz.impl.TagBizImpl;


public class FavoriteServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
	FavoriteBiz favBiz=new FavoriteBizImpl();
	TagBiz tagBiz=new TagBizImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if( "toadd".equals(op)){
			addFavoriteOP(request,response);
		}else if( "toList".equals(op)){
			findAllFavOP(request,response);
		}else if( "toCloud".equals(op)){
			findAllTagCountOP(request,response);
		}
	}
	private void findAllTagCountOP(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Tag> list=tagBiz.findAllTag();
		System.out.println(list);
		super.outJson(list, response);
	}
	private void findAllFavOP(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int tid=Integer.parseInt(request.getParameter("tid"));
		System.out.println(tid);
		List<Favorite> list=favBiz.findFavoriteByTagName(tid);
		System.out.println(list);
		super.outJson(list, response);
	}
	private void addFavoriteOP(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Favorite fav=(Favorite) super.parseRequest(request, Favorite.class);
		System.out.println(fav);
		try {
			this.favBiz.addFavorite(fav);
		} catch (Exception e) {
			e.printStackTrace();
			super.outJson(0, response);
		}
		super.outJson(1, response);
	}

}
