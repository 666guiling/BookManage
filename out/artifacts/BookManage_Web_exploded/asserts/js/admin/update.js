$(function (){
    $.ajax({
        url:"/BookManage/AdminController",
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
    $("#adminName").val(respBean.data.adminName);
    $("#img").attr("src",respBean.data.imgUrl)
}

$(function (){
    $("#button_id").click(function () {
        let adminName = $("#adminName").val();
        let file = $("#file")[0].files[0];
        let formData = new FormData;
        formData.append("action", 'updateAdmin');
        formData.append("file", file);
        formData.append("adminName", adminName);
        $.ajax({
            url: '/BookManage/AdminController',
            type: 'post',
            dataType: 'json',
            data: formData,
            contentType: false,
            processData: false,
            success: function (respBean) {
                alert(respBean.msg);
                if(respBean.status == 200){
                    location.href="toAdmin.html";
                }
            }
        })
    })
})