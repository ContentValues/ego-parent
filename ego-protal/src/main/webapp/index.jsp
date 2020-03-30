<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
	<script type="text/javascript">
        $(function(){
            $("button").click(function(){
                // alert("点击按钮")
                $.ajax({
                    url:'http://localhost:8003/demo3',
                    type:'post',
                    dataType:'jsonp',
                    jsonpCallback:'abc',
                    jsonp:'callback',
                    success:function(data){
                        alert(data.name+"success");
                    }
                });
            })
        })

        /* function abc(data){
             alert(data);
        } */

	</script>
</head>
<body>
<button >按钮</button>
</body>
</html>