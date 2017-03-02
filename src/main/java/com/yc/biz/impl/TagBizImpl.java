package com.yc.biz.impl;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yc.bean.Tag;
import com.yc.biz.TagBiz;
import com.yc.util.MybatisDao;

public class TagBizImpl implements TagBiz{

	public Tag findTagByTname(String tname) throws IOException {
		Tag tag=new Tag();
		tag.setTname(tname);
		Tag t=null;
		SqlSession session = MybatisDao.getSession();
		try{
			t=session.selectOne("com.yc.mapper.TagMapper.findTagByName",tag);
			session.commit();   
		}finally{
			session.close();
		}
		return t;
	}
	public List<Tag> findAllTag() throws IOException {
		List<Tag> list=null;
		SqlSession session = MybatisDao.getSession();
		try{
			list=session.selectList("com.yc.mapper.TagMapper.tag");
		}finally{
			session.close();
		}
		return list;
	}

}
