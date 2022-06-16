function board(){
    // console.log( $("#saveform") );
    // 폼 데이터
    let form = $("#saveform")[0];
    let formdata = new FormData( form);
    // console.log(formdata );
    $.ajax({
        url : "/write",
        method : "post",
        data : formdata ,
        contentType : false ,
        processData : false ,
        success : function(re){
            if(re == true){
                alert("작성성공");
            }else{
                alert("작성실패");
            }
        }
    });
}