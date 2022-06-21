list(1);
category_list();
function list(cno){
    $.ajax({
        url : "/lists",
        data : {"cno" : cno} ,
        method : "get",
        success : function(lists){
        console.log(lists);
            let html = "";
            for(let i = 0; i<lists.length; i++){
                html +=
                    '<span> '+lists[i].bno+' </span>'+
                    '<span><a href="/view/'+lists[i].bno+'"> '+lists[i].bcontent+' </a></span>'+
                    '<span> '+lists[i].bwrite+' </span> <br>';
            }
            $("#board").html(html);
        }
    })
}

function category_list(){

    $.ajax({
        url : "/getcategorylist",
        success : function(categorylist){
        console.log(categorylist);
            let html = "";
            for(let i=0; i<categorylist.length; i++){
                html +=
                    '<button onclick="list('+categorylist[i].cno+')">'+categorylist[i].cname+'</button>';
            }
            $("#categorybox").html(html);
        }
    })

}


