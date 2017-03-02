$(function(){
	$.ajax({
		url : "tag.action",
		type : "POST",
		data:"op=findAllTag",
		dataType : "JSON",
		success : function(data) {
			for( var i=0;i<data.length;i++){
				$(".labels_table").append("<tr><td ><a href='javascript:show("+data[i].tid+")'>"+data[i].tname+"</a></td></tr>");
			}
			$(".labels_table").append("<tr><td><a style='font-weight:bold;' href='javascript:cloud()'>云图</a></td></tr>")
		}
	});
});
function add() {
	window.open(
			'fav.html?op=toAdd',
			'newwindow',
			'height=300, width=400, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no'
	);
}
function show(tid) {
	$.ajax({
		url : "fav.action",
		type : "POST",
		data:"op=toList&tid="+tid,
		dataType : "JSON",
		success : function(data) {
			var str;
			$(".content_links").html("");
			for( var i=0;i<data.length;i++){
				str="<div style='padding: 6px 10px;'><div><a href='"+data[i].furl+"' style='color: blue; font-size: 18px;' target='_blank'>"+data[i].flabel+"</a></div><div style='color: black; font-size: 16px;'>"+data[i].fdesc+"</div><div style='color: green; font-size: 14px;'>"+data[i].furl+"</div></div>";
				$(".content_links").append(str);
			}
			
		}
	});
}
function cloud(){
	$.ajax({
		url : "fav.action",
		type : "POST",
		data:"op=toCloud",
		dataType : "JSON",
		success : function(data) {
			var str;
			$(".content_links").html("");
            for(var i=0;i<data.length;i++){
            	str="&nbsp;&nbsp;&nbsp;&nbsp;<a style='font-size:"+data[i].tcount*10+";' href='#'>"+data[i].tname+"</a>";
            	$(".content_links").append(str);
            }
            
		}
	});
}