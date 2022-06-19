view(bno);

function view(bno){
//
    $.ajax({
        url : "/view",
        data : {"bno" : bno},
        method : "POST",
        success : function(view){
            if(view == true){
            alert("해당 게시물 출력");
            let html = "";
                html +=
                '<span> '+view.bno+' </span>'+
                '<span> '+view.bcontent+' </span>'+
                '<span> '+view.bwrite+' </span>';
            }
            $("#view").html(html);
        }
    })
}

