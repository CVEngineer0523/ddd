package cloud;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yc.bean.Favorite;
import com.yc.bean.Tag;
import com.yc.util.MybatisDao;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
extends TestCase
{
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest( String testName )
	{
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		return new TestSuite( AppTest.class );
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp1() throws IOException
	{
		SqlSession session = MybatisDao.getSession();
		try{
			List<Tag> list=session.selectList("com.yc.mapper.TagMapper.tag");
			System.out.println(list);
		}finally{
			session.close();
		}
	}
	
	public void testApp2() throws IOException
	{
		SqlSession session = MybatisDao.getSession();
		try{
			Tag tag=new Tag();
			tag.setTname("凤凰");
			session.insert("com.yc.mapper.TagMapper.insertTag",tag);
			session.commit();
		}finally{
			session.close();
		}
	}
	public void testApp3() throws IOException
	{
		SqlSession session = MybatisDao.getSession();
		try{
			Tag tag=new Tag();
			tag.setTcount(1);
			tag.setTid(1);
			session.update("com.yc.mapper.TagMapper.updateTagTcount",tag);
			session.commit();
		}finally{
			session.close();
		}
	}
	public void testApp4() throws IOException
	{
		SqlSession session = MybatisDao.getSession();
		Favorite fav=new Favorite();
		fav.setFtags("未分类");
		try{
			List<Favorite> list=session.selectList("com.yc.mapper.FavoriteMapper.selectAllFavorite",fav);
			for(Favorite f:list){
				System.out.println(f);
			}
		}finally{
			session.close();
		}
	}
	public void testApp5() throws IOException
	{
		SqlSession session = MybatisDao.getSession();
		Favorite fav=new Favorite();
		fav.setFtags("未分类");
		try{
			List<Favorite> list=session.selectList("com.yc.mapper.FavoriteMapper.selectAllFavorite",fav);
			for(Favorite f:list){
				System.out.println(f);
			}
		}finally{
			session.close();
		}
	}

}
