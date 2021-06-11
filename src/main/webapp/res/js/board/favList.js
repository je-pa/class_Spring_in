const listElem = document.querySelector("#list");

function getListAjax(){
    fetch('fav')
    .then(res => res.json())
    .then(myJson =>{
        console.log(myJson);
        makeView(myJson);
    });
    // fetch('fav')
    // .then(function (res){
    //     return res.json();
    // })
    // .then(function (myJson){
    //     console.log(myJson);
    // })
}
function makeView(data){
    listElem.innerHTML='';
    const table = document.createElement('table');
    listElem.append(table);

    table.innerHTML=`
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>글쓴이</th>
            <th>작성일시</t>
        </tr>
    `;//`값넣기 편함
    data.forEach(item =>{
       const tr =document.createElement('tr');
       table.append(tr);
       tr.classList.add('record');
       tr.addEventListener('click',()=>{
           moveToDetail(item.iboard)
       });

       let imgSrc = '/res/img/noprofile.jpg';
       if(item.profileImg != null){
           imgSrc=`/img/${item.iuser}/${item.profileImg}`;
       }
        //el식아니고 자바스크립트문법
       tr.innerHTML=`
            <td>${item.iboard}</td>
            <td>${item.title}</td>
            <td>${item.writerNm}<img src="${imgSrc}" class="profileImg"></td>
            <td>${item.regdt}</td>       
       `;
    });
}
function moveToDetail(iboard){
    location.href='/board/detail?iboard='+iboard;
}
getListAjax();