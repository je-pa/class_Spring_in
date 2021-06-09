var cmtFrmElem = document.querySelector('#cmtFrm');
var cmtListElem = document.querySelector('#cmtList');
var cmtModModalElem= document.querySelector('#modal');
function regCmt(){
	//console.log('cmtVal : '+cmtVal);
	console.log(cmtListElem.dataset.iboard);//data-
	var param={
		iboard:cmtListElem.dataset.iboard,
		cmt:cmtFrmElem.cmt.value
	};
	regAjax(param);
}
//서버에게 등록해줘
function regAjax(param){
	const init={
		method:'POST',
		//body:new URLSearchParams(param),
		body:JSON.stringify(param),
		headers:{
			'accept' : 'application/json',
			'content-type' : 'application/json;charset=UTF-8'
		}
	};
	
	fetch('cmt',init) //init안하면 디폴트 형식으로 돌아감
	.then(function(res){
		return res.json();
	})
	.then(function(myJson){
		console.log(myJson)
		
		switch(myJson.result){
		case 0:
			alert('등록실패');
			break;
		case 1:
			alert('등록완료');
			cmtFrmElem.cmt.value = '';
			getListAjax();
			break;	
		}
	});
}
//서버에게 댓글 리스트 자료 달라고 요청하는 함수
function getListAjax(){
	var iboard = cmtListElem.dataset.iboard;
	
	fetch('cmt/'+iboard)
	.then(function(res){
		return res.json();
	})
	.then(function(myJson){
		console.log(myJson);
		
		makeCmtElemList(myJson);
	});
}
function makeCmtElemList(data){
	cmtListElem.innerHTML='';//댓글 쓸때마다 테이블이 또 생기는것을 방지
	/*cmtListElem.innerText //태그 없는상태
	cmtListElem.append()
	cmtListElem.appendChild()*/
	var tableElem = document.createElement('table');
	var trElemTitle = document.createElement('tr');
	var thElemCtnt = document.createElement('th');
	var thElemWriter = document.createElement('th');
	var thElemRegdate = document.createElement('th');
	var thElemBigo = document.createElement('th');
	
	thElemCtnt.innerText='내용';
	thElemWriter.innerText='작성자';
	thElemRegdate.innerText='작성일';
	thElemBigo.innerText='비고';
	/*
		<table></table>
		<tr></tr>
		<th>내용</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>비고</th>
	 */
	
	trElemTitle.append(thElemCtnt);//tr에 소속시켜줌
	trElemTitle.append(thElemWriter);
	trElemTitle.append(thElemRegdate);
	trElemTitle.append(thElemBigo);
	
	tableElem.append(trElemTitle);
	
	cmtListElem.append(tableElem);
	/*
		<div id="cmtList">
			<table>
				<tr>
					<th>내용</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>비고</th>
				</tr>
			</table>
		</div>
	 */
	var loginUserPk = cmtListElem.dataset.loginuserpk;
	
	data.forEach(function(item){
		var trElemItem = document.createElement('tr');
		var tdElemCtnt = document.createElement('td');
		var tdElemWriter = document.createElement('td');
		var tdElemRegdate = document.createElement('td');
		var tdElemBigo = document.createElement('td');
		
		tdElemCtnt.append(item.cmt);
		tdElemWriter.append(item.writerNm);
		tdElemRegdate.append(item.regdate);
		//비고 - 버튼 일단 비움
		
		if(parseInt(loginUserPk)===item.iuser){
			var delBtn = document.createElement('button');
			var modBtn = document.createElement('button');
			
			//삭제버튼 클릭
			delBtn.addEventListener
			('click'/*이벤트가 무엇인지 hover*/ ,function(){
				if(confirm('삭제하시겠습니까?')){//확인(true)과 취소(false)
					delAjax(item.icmt);
				} 
			})
			//수정버튼 클릭
			modBtn.addEventListener('click',function(){
				//댓글 수정 모달창 띄우기
				openModModal(item);
			})
			
			delBtn.innerText = '삭제';
			modBtn.innerText='수정';
			
			tdElemBigo.append(delBtn);
			tdElemBigo.append(modBtn);
		}
		
		trElemItem.append(tdElemCtnt);
		trElemItem.append(tdElemWriter);
		trElemItem.append(tdElemRegdate);
		trElemItem.append(tdElemBigo);
		
		tableElem.append(trElemItem);
	});
}
function delAjax(icmt){
	console.log(icmt);
	fetch('cmt/'+icmt, {method: 'DELETE'})//호출 프로미스 객체를 리턴?
	.then(function(res){
		return res.json();
	})
	.then(function(myJson){
		console.log(myJson);
		
		switch(myJson.result){
		case 0:
			alert('댓글 삭제를 실패');
			break;
		case 1:
			getListAjax();
			break;
		}
	});
}

function modAjax(){
	var cmtModFrmElem = document.querySelector('#cmtModFrm');
	var param = {
		icmt:cmtModFrmElem.icmt.value,
		cmt:cmtModFrmElem.modCmt.value
	}
	const init={
		method:'PUT',
		body:JSON.stringify(param),
		headers:{
			'accept' : 'application/json',
			'content-type' : 'application/json;charset=UTF-8'
		}
	};
	
	fetch('cmt',init) //init안하면 디폴트 형식으로 돌아감
	.then(function(res){
		return res.json();
	})
	.then(function(myJson){
		switch(myJson.result){
		case 0:
			alert('댓글 수정 실패');
			break;
		case 1:
			closeModModal();
			getListAjax();
			break;
		}
	});
}


function openModModal({icmt,cmt}){//필요한 값만 빼올수 있다
	cmtModModalElem.className='';
	
	var cmtModFrmElem = document.querySelector('#cmtModFrm');
	console.log('icmt : '+icmt);
	console.log('cmt : '+cmt);
	cmtModFrmElem.icmt.value = icmt;
	cmtModFrmElem.modCmt.value = cmt;
}

function closeModModal(){
	cmtModModalElem.className='displayNone';
}
getListAjax();//이 파일 임포트되면 함수 호출됨