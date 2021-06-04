var frmElem = document.querySelector('#frm');
var uidElem = frmElem.uid;
var upwElem = frmElem.upw;
var chkUpwElem = frmElem.chkUpw;
var unmElem = frmElem.unm; 
var chkUidResultElem = frmElem.querySelector('#chkUidResult');

var btnChkIdElem = frmElem.btnChkId;
btnChkIdElem.addEventListener('click',function(){
	//클릭이 일어나면 함수를 실행하겠다~
	//onclick과 같다
	idChkAjax(uidElem.value);
});

function idChkAjax(uid){
	console.log(uid);
	
	
	fetch('/user/idChk?uid='+uid)
	.then(function(res){
		return res.json();
	})
	.then(function(myJson){
		console.log(myJson);
		switch(myJson.result){
			case 0:
				chkUidResultElem.innerText='이 아이디는 사용할 수 있습니다.';
				break;
			case 1:
				chkUidResultElem.innerText='이 아이디는 사용할 수 없다.';
				break;
		}
	})
}

function frmChk(){
	//이상이 생기면 return false;
	var uidVal = uidElem.value;
	//아이디 하나도 안적으면 alert 아이디를 작성해 주세요
	//2자 이하면 아이디는 3자이상 작성해 주세요 false리턴
	if(uidVal.length<3){
		if(uidVal.length==0){
			alert('아이디를 작성해 주세요');
		}else{
			alert('아이디는 3자이상 작성해 주세요');
		}
		return false;
	}
	console.log(uidVal);
	
	var upwVal = upwElem.value;
	var chkUpwVal = chkUpwElem.value;
	
	//비밀번호 하나도 안 적으면 alert "비밃번호 작성해"
	//3자이하면 비밀번호는 4자이상 작성해 
	//비밀번호와 확인비밀번호가 다르면 비밀번호를 확인해 false
	if(upwVal.length<4){
		if(upwVal.length==0){
			alert('비밀번호를 작성해 주세요');
		}else{
			alert('비밀번호는 4자이상 작성해 주세요');
		}
		return false;
	}else if(upwVal !== chkUpwVal){
		alert('비밀번호를 확인해 주세요');
		return false;
	}
	
	if(unmElem.value.length<2){
		alert('이름은 2자 이상 작성해');
		return false;
	}
}
//저번에 디테일 할때 textarea는 cmt.value=식으로 값입력했는데 chkUidResultElem는 왜 innerText를 쓰나욤