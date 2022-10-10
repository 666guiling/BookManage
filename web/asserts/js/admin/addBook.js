$(function (){
    $("#bookName").blur(function (){
        $.ajax({
            url:'/BookManage/AdminController',
            data:{
                action:"checkBookName",
                bookName:$("#bookName").val()
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

//注册功能
$(function (){
    $("#button_id").click(function () {
        alert(555)
        let bookName = $("#bookName").val();
        let detail = $("#detail").val();
        let counts = $("#counts").val();
        let bookClassId = $("#bookClass_id").val();
        let author = $("#author").val();
        let file = $("#file")[0].files[0];
        let formData = new FormData;
        formData.append("action", 'addBook');
        formData.append("file", file);
        formData.append("bookName", bookName);
        formData.append("detail", detail);
        formData.append("counts", counts);
        formData.append("bookClassId", bookClassId);
        formData.append("author", author);
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