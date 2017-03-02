package com.yc.biz;

import java.io.IOException;
import java.util.List;

import com.yc.bean.Tag;

public interface TagBiz {
	/**
	 * 根据标签名查找标签
	 * @param tname
	 * @return
	 * @throws IOException 
	 */
     public Tag findTagByTname(String tname) throws IOException;
     
     /**
      * 查找所有标签
      * @return
     * @throws IOException 
      */
     public List<Tag> findAllTag() throws IOException;
}
