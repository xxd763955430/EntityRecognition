<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<head>
<title>Sim</title>
<meta charset="utf-8"/> 
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
    <h1 style="text-align: center">电商网站评论文本相似度</h1>
    <form>
    <div class="col-md-6">
        <textarea class="form-control" rows="5" placeholder="文本1" name="sentence1">个头大质量不错好吃。</textarea>
    </div>
    <div class="col-md-6">
        <textarea class="form-control" rows="5" placeholder="文本2" name="sentence2">好大，拿来还是冰的，明天吃</textarea>
    </div>
        <table class="table table-striped table-hover ">
            <thead>
            <tr>
                <th class="col-md-1">#</th>
                <th class="col-md-3">相似度</th>
                <th class="col-md-3">结果</th>
                <th class="col-md-5">计算公式</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>1</th>
                <td>《知网》相似度</td>
                <td><label id="label1">howSim</label></td>
                <td><img src="/Similarity/resource/1.png"></td>
            </tr>
            <tr>
                <th>2</th>
                <td>公共词相似度</td>
                <td><label id="label2">comWordSim</label></td>
                <td><img src="/Similarity/resource/2.png"></td>
            </tr>
            <tr>
                <th>3</th>
                <td>长度相似度</td>
                <td><label id="label3">lenSim</label></td>
                <td><img src="/Similarity/resource/3.png"></td>
            </tr>
            <tr>
                <th>4</th>
                <td>综合相似度</td>
                <td><label id="label4">sim</label></td>
                <td><img src="/Similarity/resource/4.png"></td>
            </tr>
            </tbody>
        </table>
<button id="getResult" class="btn btn-info" type="button">计算</button>
</form>
</div>
</body>
<script>
	$("#getResult").click(function(){
	getSim();
	}
	);
	function getSim()
	{
				$.ajax({
					url : 'http://localhost:8080/Sim_Maven/servlet/SimServlet',
					data:{sentence1:encodeURI($("textarea[name=sentence1]").val()),
					sentence2:$("textarea[name=sentence2]").val()
					},
					dataType : 'json',
					type : 'post',
					async : false,
					success : function(data) {
					console.log(data);
					$("#label1").text(data.howSim);		 
					$("#label2").text(data.comSim);	
					$("#label3").text(data.lenSim);	
					$("#label4").text(data.sim);	
					}
					});
	}
</script>
</html>
