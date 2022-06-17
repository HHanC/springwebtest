list();

function list(){
    $.ajax({
        url : "/list",
        method : "POST",
        success : function(list){
        console.log(list);
            let html = "";
            for(let i = 0; i<list.length; i++){
                html +=
                    '<span> '+list[i].bno+' </span>'+
                    '<span onclick="view('+list[i].bno+')"> '+list[i].bcontent+' </span>'+
                    '<span> '+list[i].bwrite+' </span>';
            }
            $("#board").html(html);
        }
    })
}

function view(bno){
    $.ajax({
        url : "/view",
        data : {"bno" : bno},
        method : "POST",
        success : function(view){
            if(view == true){
            alert("해당 게시물 출력");
                html +=
                '<span> '+view.bno+' </span>'+
                '<span> '+view.bcontent+' </span>'+
                '<span> '+view.bwrite+' </span>';
            }
            $("#view").html(html);
        }

    })

}