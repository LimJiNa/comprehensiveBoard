var getParameters = function (paramName) {

    // 현재 URL 가져오기
    var url = location.href;

    // get 파라미터 값을 가져올 수 있는 ? 를 기점으로 slice 한 후 split 으로 나눔
    var parameters = (url.slice(url.indexOf('?') + 1, url.length)).split('&');
    // 리턴값을 위한 변수 선언
    var returnValue = [];

    // 나누어진 값의 비교를 통해 paramName 으로 요청된 데이터의 값만 return
    for (var i = 0; i < parameters.length; i++) {
        var varName = parameters[i].split('=')[0];
        if (varName.toUpperCase() == paramName.toUpperCase()) {
            returnValue.push(decodeURIComponent(parameters[i].split('=')[1]));
        }
    }
    return returnValue;
};

//NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
//입력양식에 value 값을 삽입하거나 체크해주는 함수 선언
//매개변수로 선택자가 들어온다
//NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
function inputData(selector, data ){
 try{
    //selector가 가르키는 입력양식을 관리하는 JQuery 객체 생성하기
    var obj = $(selector);
    //selector가 가르키는 입력 양식이 없으면 경고하고 함수 중단하기
    if( obj.length==0 ){
       alert("inputData2(" +selector+ "," +data+")함수 호출 시 [" +selector+ "]란 선택자가 가르키는 입력양식이 없습니다.");
    }
    
    //만약 입력양식이 checkbox 또는 radio면 value 값으로 변수 data 안의 데이터를 가진 놈을 체크하기
    if(obj.is(":checkbox") || obj.is(":radio")){
       obj.filter("[value='"+data+"']").prop("checked", true);
    }
    
    //만약 입력양식이 checkbox 또는 radio가 아니면 입력양식의 value값으로 매개변수 data 안의 데이터를 삽입하기
    else{
       obj.val(data);
    }
    
 }catch(e){
    alert("inputData( '"+selector+"','"+data+"' )함수 호출 시 에러 발생!");
 }
}