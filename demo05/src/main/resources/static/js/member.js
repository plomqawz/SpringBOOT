$("#btnUpdate").click(function(){
	
	var dataParam = {
		"id" : $("#id").val(),
		"name" : $("#name").val(),
		"password" : $("#password").val(),
		"email" : $("#email").val(),
		"addr" : $("#addr").val(),
		"memo" : $("#memo").val(),
	}
	
	$.ajax({
		type : 'PUT',
		url : "/update/",
		data : JSON.stringify(dataParam),
		contentType : "application/json;charset=utf-8"
	})
	.done(function(){
		alert("수정성공")
		location.href="/list"
	})
	.fail(function(){
		alert("수정실패")
	})
})