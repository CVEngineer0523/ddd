package com.yc.biz.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.yc.bean.Favorite;
import com.yc.bean.Tag;
import com.yc.biz.FavoriteBiz;
import com.yc.biz.TagBiz;
import com.yc.util.MybatisDao;

public class FavoriteBizImpl implements FavoriteBiz{

	TagBiz tagBiz=new TagBizImpl();

	public boolean addFavorite(Favorite fav) throws IOException {
		
		/*if(fav.getFtags()!=null){//标签名不为空时
			String[] tagName=fav.getFtags().split(",");
			try {
				SqlSession session = MybatisDao.getSession();
				for( int i=0;i<tagName.length;i++){
					Tag tag=tagBiz.findTagByTname(tagName[i]);
					if(tag!=null){
						tag.setTcount(tag.getTcount()+1);
						session.update("com.yc.mapper.TagMapper.updateTagTcount",tag);
					}else{
						Tag tag1=new Tag();
						tag1.setTname(tagName[i]);
						session.insert("com.yc.mapper.TagMapper.insertTag",tag1);
					}
				}
				
					session.insert("com.yc.mapper.FavoriteMapper.insertFavorite",fav);
					session.commit();
				
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(  e);
			}
		}else{//标签名为空时
			try {
				SqlSession session = MybatisDao.getSession();
				Tag tag2=tagBiz.findTagByTname("未分类");
				tag2.setTid(1);
				tag2.setTcount(tag2.getTcount()+1);
				session.update("com.yc.mapper.TagMapper.updateTagTcount",tag2);
				fav.setFtags("未分类");
				session.insert("com.yc.mapper.FavoriteMapper.insertFavorite",fav);
				session.commit();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(  e);
			}
		}

		return true;*/
		
		boolean flag=false;
		SqlSession s=MybatisDao.getSession();
		
		try {
			String tags=fav.getFtags();
			if(tags==null||"".equals(tags)){
				fav.setFtags("未分类");
			}else{
				//正则表达式:  英文和中文
				String regex=",|，|\\s+";
				String[] tag=tags.split(regex);
				
				//查出tag表中所有的标签,存到一个map中
				List<Tag> lt=s.selectList("com.yc.mapper.TagMapper.tag");
				Map<String,String> map=new HashMap<String,String>();
				for(int i=0,len=lt.size();i<len;i++){  //将tagname存到map中
					map.put(lt.get(i).getTname(), null);
				}
				
				//循环当前的网址中所有tag标签，查看这些标签在map中是否存在
				for(int i=0,len=tag.length;i<len;i++){
					boolean exist=map.containsKey(tag[i]);
					
					if(exist){//存在则更新数量
						Tag tag1=tagBiz.findTagByTname(tag[i]);
						tag1.setTcount(tag1.getTcount()+1);
						s.update("com.yc.mapper.TagMapper.updateTagTcount",tag1);
					}else{//不存在则插入值
//						Tag tag2=new Tag();
//						tag2.setTname(tag[i]);
//						System.out.println(tag[i]);
//						System.out.println(tag2.getTname());
						s.insert("com.yc.mapper.TagMapper.insertTag",tag[i]);
					}
				}
			}
			s.insert("com.yc.mapper.FavoriteMapper.insertFavorite",fav);
			flag=true;
			s.commit();
		} catch (Exception e) {
			s.rollback();
			e.printStackTrace();
			throw e;
		}
		return flag;
	}

	public List<Favorite> findFavoriteByTagName(int tid) {
		List<Favorite> list=null;
		Favorite fav=new Favorite();
		try{
			SqlSession session = MybatisDao.getSession();
			if(tid!=-1){
				Tag tag=new Tag();
				tag.setTid(tid);
				Tag tag2=session.selectOne("com.yc.mapper.TagMapper.findTagByTid", tag);
				fav.setFtags("%"+tag2.getTname()+"%");
			}
			list=session.selectList("com.yc.mapper.FavoriteMapper.selectAllFavorite",fav);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException( e);
		}
		return list;
	}


}
