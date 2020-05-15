$(document).ready(function () {
    $("#myInputButton").click(function () {
        var reg=new RegExp("<br>","g");
        var input= $("#myInput").val();
      if(input===""){
          alert("请进行输入！");
      }
        $.ajax({
            url: "/ml/demo",
            type: "POST",
            data:{"input":input},
            success: function (res) {
                alert("成功");
                var content = res.content;
                $("#result").text(content);
            },
            error: function (res) {
                console.log("fda");
                alert("error occurred")
            }
        })
    });
});

