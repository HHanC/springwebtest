
board();

function board(){
    $.ajax({
        url : "/board",
        method : "get",
        data : {"saveform" : $("#saveform").val()},
        success : function(re){
            alert(re);
        }
    });
}