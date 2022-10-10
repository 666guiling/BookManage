let flag = false;
$(function (){
    $("#pwd1").blur(function (){
        $.ajax({
            url:'/BookManage/AdminController',
            data:{
                action:'checkPassword',
                password:$("#pwd1").val(),
            },
            dataType:'json',
            type:'post',
            success:function (respBean){
                if (respBean.status==200){
                    $("span:eq(0)").text(respBean.msg);
                    flag = true;
                }
                if (respBean.status==500){
                    flag = false;
                    $("span:eq(0)").css("color", "red");
                    $("span:eq(0)").text(respBean.msg);
                }
            }
        })
    })
    let pwd2
    let pwd3
    $("#pwd2").blur(function (){
        pwd2 = $("#pwd2").val();
        if (pwd2 ==""){
            flag = false;
            $("span:eq(1)").css("color", "red");
            $("span:eq(1)").text("密码不能为空");
        }else {
            flag = true
            $("span:eq(1)").text("");
        }
    })
    $("#pwd3").blur(function (){
        pwd3 = $("#pwd3").val();
        if (pwd3 ==""){
            flag = false;
            $("span:eq(2)").css("color", "red");
            $("span:eq(2)").text("密码不能为空");
        }else if (pwd3 != pwd2){
            flag = false
            $("span:eq(2)").css("color", "red");
            $("span:eq(2)").text("两次密码不相同");
        }else {
            flag = true
            $("span:eq(2)").text("");
        }
    })

})
$(function (){
    $("#button_id").click(function (){
        console.log(flag)
        if (!flag){
            alert("请验证密码是否正确或为空")
        }else{
            $.ajax({
                url: '/BookManage/AdminController',
                data: {
                    action: 'rPassword',
                    password: $("#pwd2").val()
                },
                type: "get",
                dataType: "json",
                success:function (respBean){
                    if (respBean.status==200){
                        alert("修改成功")
                        location.href="../login.html"
                    }else if (respBean.status==500){
                        alert("修改失败")
                    }
                }
            })
        }
    })
})