view();

function view(){
    $.ajax({
        url : "/getview",
        success : function(view){
            let html =
                '<span> '+view.bno+' </span>'+
                '<span> '+view.bcontent+' </span>'+
                '<span> '+view.bwrite+' </span>'+
                '<button onclick="view_delete('+view.bno+')">삭제하기</button>'
            $("#view").html(html);
        }
    })
}

function view_delete(bno){
    $.ajax({
        url : "/delete" ,
        data : {"bno" : bno},
        method : "delete" ,
        success : function(re){
            alert(re);
        }
    })
}

