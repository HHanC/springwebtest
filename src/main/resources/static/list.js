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
                    '<a href="/view?bno='+list[i]["bno"]+'"><span> '+list[i].bcontent+' </span></a>'+
                    '<span> '+list[i].bwrite+' </span> <br>';
            }
            $("#board").html(html);
        }
    })
}

