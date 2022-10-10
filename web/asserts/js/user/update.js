$(function (){
    $.ajax({
        url:"/BookManage/UserController",
        data:{
            action:'toUser',
        },
        type:'get',
        dataType:'json',
        success:function (respBean){
            if(respBean.status == 500){
                alert(respBean.msg);
                location.href='index.html';
            }else if(respBean.status == 200){
                btn(respBean);
            }
        }
    })
})

function btn(respBean){
    $("#username").val(respBean.data.username);
    $("#phone").val(respBean.data.phone)
    $("#sex").val(respBean.data.sex)
    $("#img").attr("src",respBean.data.imgUrl)
}

$(function (){
    $("#username").blur(function (){
        $.ajax({
            url:'/BookManage/UserController',
            data:{
                action:"checkUsername",
                username:$("#username").val()
            },
            dataType:'json',
            type:'post',
            success:function (respBean){
                let color;;
                if (respBean.status == 200){
                    color = "green";
                }else if (respBean.status == 500){
                    color ="red";
                }
                $("span:eq(0)").css("color",color);
                $("span:eq(0)").text(respBean.msg);
            }
        })
    })
})

//展示图片
$(function (){
    $("#file").change(function (){
        let file = $("#file")[0].files[0];
        let fr = new FileReader();
        fr.readAsDataURL(file);
        fr.onloadend = function (){
            $("#img").attr("src",fr.result);
        }
    })
})

$(function (){
    $("#button_id").click(function () {
        let username = $("#username").val();
        let phone = $("#phone").val();
        let sex = $("#sex").val();
        let file = $("#file")[0].files[0];
        let formData = new FormData;
        formData.append("action", 'update');
        formData.append("file", file);
        formData.append("username", username);
        formData.append("phone", phone);
        formData.append("sex", sex);
        $.ajax({
            url: '/BookManage/UserController',
            type: 'post',
            dataType: 'json',
            data: formData,
            contentType: false,
            processData: false,
            success: function (respBean) {
                alert(respBean.msg);
                if(respBean.status == 200){
                    location.href="../user/toUser.html";
                }
            }
        })
    })
})