package com.yc.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;



public class BasicServlet <T> extends HttpServlet {
	private static final long serialVersionUID = 3162254463613246330L;
	protected String op;
	protected void outJson(Object obj,HttpServletResponse response) throws IOException{
		Gson gson=new Gson();
		String jsonString =gson.toJson(obj);
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(jsonString);
		out.close();
	}
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		arg0.setCharacterEncoding("utf-8");
		op=arg0.getParameter("op");
		super.service(arg0, arg1);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request,response);
	}
	//将request中的值封装起来
	protected  T parseRequest(HttpServletRequest request, Class<T> c){
		Map<String,String[]> map=request.getParameterMap();//获取所有参数
		Set<String> methodNames=getMethodName(map.keySet());
		Method [] ms=c.getMethods();
		T t=null;
		try {
			t=c.newInstance();
			System.out.println(t+"测试");
			for(Method method:ms){//循环取出每一行数据，得到每一行的列名和数据
					for(String mn:methodNames){
						if(method.getName().equals(mn)){
							String keyname=mn.substring(3,4).toLowerCase()+mn.substring(4);//恢复方法名在数据库中的原样，从而获取它们对应的值
							String typeName=method.getParameterTypes()[0].getName();//由于每次循环只有一个方法
							String [] value=map.get(keyname);
							if(value[0]!=null && !"".equals(value[0]) && value.length==1){
								if("java.lang.Integer".equals(typeName)|| "int".equals(typeName)){
									method.invoke(t, Integer.parseInt(value[0]));
								}else if("java.lang.Double".equals(typeName)|| "double".equals(typeName)){
									method.invoke(t, Double.parseDouble(value[0]));
								}else if("java.lang.Float".equals(typeName)|| "float".equals(typeName)){
									method.invoke(t, Float.parseFloat(value[0]));
								}else if("java.lang.Long".equals(typeName)|| "long".equals(typeName)){
									method.invoke(t, Long.parseLong(value[0]));
								}else{
									method.invoke(t, value[0]);
								}
								break;
							}
						}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	/**
	 * 拼接方法+set
	 * @param keys
	 * @return
	 */
	private Set<String> getMethodName( Set<String> keys){
		Set<String> result=new HashSet<String>();
		for(String key:keys){
			String newName=key.substring(0,1).toUpperCase()+key.substring(1);
			result.add("set"+newName);
		}
		return result;
	}

}
