package com.yc.biz;


import java.io.IOException;
import java.util.List;

import com.yc.bean.Favorite;

public interface FavoriteBiz {
	/**
	 * 添加地址，业务规则：添加网址，再切分标签（根据   , 分割），在循环去tag表中查看是否有这个标签，有，则增加数量。
	 * 没有，则插入一条新的标签到tag表
	 * 再将favorite信息添加到   favorite  表中
	 * @param fav
	 * @return
	 * @throws IOException 
	 */
     public boolean addFavorite( Favorite fav) throws IOException;
     
     /**
      * 根据标签名查找这个标签分类下的网址，标签名为null，则查询所有，标签名为""，则查询未分类，
      * 有标签名则按照标签名查询
      * @param tName
      * @return
      */
     public List<Favorite> findFavoriteByTagName(int tid);
}
