<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="hsx" uri="http://www.hsx.cn.com/jsp/hsx/tag/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload = function(){
		var area = document.getElementById("fileUploadArea");
		area.ondragenter = function(e){
			e.preventDefault();
			e.stopPropagation();
		}

		area.ondragleave = function(e){
			e.preventDefault();
			e.stopPropagation();
		}

		area.ondragover = function(e){
			e.preventDefault();
			e.stopPropagation();
		}

		area.ondrop = function(e){
			e.preventDefault();
			e.stopPropagation();
			var files = e.dataTransfer.files;
			var formData = new FormData();
			for(var i = 0;i<files.length;i++){
				formData.append("file",files[i]);
			}

			var request = new XMLHttpRequest();

			request.open("post","<%=application.getContextPath() %>/FileUploadTest");
			request.onload = function() {
				if (request.readyState == 4 && request.status == 200) {
					area.innerHTML = "file upload is success!";
				} else {
					area.innerHTML = "Error" + request.status;
				}
			}
			request.send(formData);
		}

		document.getElementById("show").onclick = function(){
			document.myForm.action="<%=application.getContextPath()%>
	/doShow.action";
			document.myForm.submit();
		}
	}
</script>
<style>
#fileUploadArea {
	width: 500px;
	height: 500px;
	border: 5px solid black;
	border-style: dotted;
}
</style>
</head>
<body>
	<form action="doFileUpload.action" method="post" name="myForm"
		enctype="multipart/form-data">
		<input type="file" name="file" /><br> <input type="file"
			name="file" /><br> <input type="submit" value="submit" />
	</form>
	<div id="fileUploadArea">このエリアにファイルをドラッグ＆ドラックして下さい！</div>
	<br>
	<input type="button" id="show" name="show" value="show" />
	<hsx:if test="${requestScope.fileList }">
		<hsx:forEach var="data" item="${requestScope.fileList }">
			<a
				href="/doDownload?path=${data.absolutePath }">${data.name }</a>
			<br>
		</hsx:forEach>
	</hsx:if>
</body>
</html>