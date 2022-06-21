
function update(){
    let form = $("#updateform")[0];
    let formdata = new FormData(form);
    console.log(form);
    $.ajax({
        url : "/update",
        data : formdata ,
        method : "put" ,
        processData : false,
        contentType : false,
        success : function(re){
            alert(re);
        }
    })

}