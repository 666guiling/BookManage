let id = window.sessionStorage.getItem('id');
$(function (){
    $.ajax({
        url:"/BookManage/AdminController",
        data:{
            action:'toBook',
            id:id
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

$(function (){
    $("#BookName").blur(function (){
        $.ajax({
            url:'/BookManage/AdminController',
            data:{
                action:"checkBookName",
                username:$("#BookName").val()
            },
            dataType:'json',
            type:'post',
            success:function (respBean){
                let color;
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

function btn(respBean){
    $("#bookName").val(respBean.data.bookName);
    $("#detail").val(respBean.data.detail)
    $("#counts").val(respBean.data.counts)
    $("#bookClass_id").val(respBean.data.bookclassId)
    $("#file").val(respBean.data.file)
    $("#author").val(respBean.data.author)
    $("#img").attr("src",respBean.data.imgUrl)
}

$(function (){
    $("#button_id").click(function () {
        let bookName = $("#bookName").val();
        let detail = $("#detail").val();
        let counts = $("#counts").val();
        let bookClassId = $("#bookClass_id").val();
        let author = $("#author").val();
        let file = $("#file")[0].files[0];

        let formData = new FormData;
        formData.append("action", 'updateBook');
        formData.append("file", file);
        formData.append("bookName", bookName);
        formData.append("detail", detail);
        formData.append("counts", counts);
        formData.append("bookClassId", bookClassId);
        formData.append("author", author);
        formData.append("id",id)
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
                    location.href="../admin/getBookList.html";
                }
            }
        })
    })
})