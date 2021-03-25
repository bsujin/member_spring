/**
 * 
 */
/**
 * 
 */

function idcheck(){
	//id는 이메일(공백,정규식)
	idvalue = $('#userid').val().trim();
	//공백
	if(idvalue.length ==''){
		$('#idCk').text("ID를 입력 해주세요").css('color', 'red');
		return false;
	}
	//정규식
	idreg = /^[0-9a-z]+$/;
	if(!(idreg.test(idvalue))){
		$('#idCk').html("ID 형식오류").css('color', 'red');
		return false;
	}else{
		$('#idCk').html("✔").css("color", "green");
	}
	return true;
}

function pwdcheck(){
	//비밀번호(공백,길이,정규식)
	pwdvalue = $('#pass').val().trim();
	pwdreg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,15}$/;

	//공백
	if(pwdvalue.length < 1){
		$("#pwCk").html("비밀번호를 입력하세요.").css("color","red")
		return false;
	}
	//길이(8~12)
	if(pwdvalue.length < 8 || pwdvalue.length > 15){
		$("#pwCk").html("8~15글자로 입력하세요.").css("color","red")
		return false;
	}
	//정규식(영대소문자, 숫자 특수문자 1개이상씩)
	if(!(pwdreg.test(pwdvalue))){
		$("#pwCk").html("영어 대소문자,숫자,특수문자 1개 이상 입력").css("color","red")
		return false;
	}else {
		$('#pwCk').html("✔").css("color", "green");
	}
}


function namecheck(){
	//이름(공백,길이,정규식)
	namevalue = $('#usernm').val().trim();	
	//공백
	if(namevalue.length<1){
		$('#nmCk').html("이름을 입력하세요").css("color","red");
		return false;
	}
	//길이(2~5)
	if(namevalue.length < 2 || namevalue.length > 10){
		$('#nmCk').html("정확한 이름을 입력하세요").css("color","red");
		return false;
	}
	//정규식
	namereg = /^[가-힣]{2,10}$/;
	if(!(namereg.test(namevalue))){
		$('#nmCk').html("이름 형식오류").css("color","red");
		return false;
	}else{
		$('#nmCk').html("✔").css("color", "green");
	}
	return true;
}


//정규식 체크
function regcheck(){
	idcheck();
	pwdcheck();
	namecheck();
	return true;
}