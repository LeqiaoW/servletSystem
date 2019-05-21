window.onload=function (){
    debugger;
    //对应方法2
    var key=localStorage.getItem('key');
    var time=localStorage.getItem('Sdate');
    var pinpai=localStorage.getItem('Tbrand');
    var xingzhengqu=localStorage.getItem('location');
    var teshuting=localStorage.getItem('roomType');
    var tno=localStorage.getItem('Tno');

    $.ajax({
        type:"GET",
        url:"",
        contentType:"charset=utf-8",
        sucess:function (data) {
            showTheater(data)
        },
        error:function () {
            alert("连接服务器出错")
        }
    })
};

function showTheater(data) {
    $(".cinema-brief-container h3").text();
    $(".address").text();
    $(".telphone p").text();
}