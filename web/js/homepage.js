$(function(){
    $(".carousel-content").carousel({
        carousel : ".carousel",//轮播图容器
        indexContainer : ".img-index",//下标容器
        prev : ".carousel-prev",//左按钮
        next : ".carousel-next",//右按钮
        timing : 3000,//自动播放间隔
        animateTime : 700,//动画时间
        autoPlay : true,//是否自动播放 true/false
        direction : "left"//滚动方向 right/left
    });

    $(".carousel-content").hover(function(){
        $(".carousel-prev,.carousel-next").fadeIn(300);
    },function(){
        $(".carousel-prev,.carousel-next").fadeOut(300);
    });

    $(".carousel-prev").hover(function(){
        $(this).find("img").attr("src","../img/left2.png");
    },function(){
        $(this).find("img").attr("src","../img/left1.png");
    });
    $(".carousel-next").hover(function(){
        $(this).find("img").attr("src","../img/right2.png");
    },function(){
        $(this).find("img").attr("src","../img/right1.png");
    });
});

$(document).ready(function(){

    $.ajax({
        type:"GET",
        contentType:"charset=utf-8",
        url: "/homepage",
        success: function (data) {
             MovieList(data)
        },
        error: function () {
            alert("Error");
        },
    })
});

function MovieList(data) {
    for(var i=0, len=data.length;i<len;i++){
        //添加多余的文件
        if(i>=1){
            $("#movie-list-hot-play").append($("#movie-item-hot-play1").clone());
            $("#movie-list-hot-play li:last").attr('id','#movie-item-hot-play'+i+1);
        }

        var name = data["Mname"];
        var rate = data["Mrating"];
        var pic = data["MposterPath"];
        $("#movie-item-hot-play"+i+1+" #movie-rate").text(rate);
        $("#movie-item-hot-play"+i+1+" #movie-name").text(name);
    }
}