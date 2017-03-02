function addfavorite(){
	$.ajax({//提交订单按钮事件
		url:"fav.action",
		data:"op=toadd&flabel="+$("#flabel").val()+"&furl="+$("#furl").val()+"&ftags="+$("#ftags").val()+"&fdesc="+$("#fdesc").val(),
		type:"post",
		dataType:"JSON",
		success:function( data ){
			if(data==1){
				alert("添加书签成功");
				window.close();
			//	history.go(0);
			}else{
				alert("添加书签失败");
			}
		}
	});
}

