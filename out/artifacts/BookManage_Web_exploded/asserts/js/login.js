$(function (){
    $("#button_id").click(function (){
        let username = $("#username").val();
        let password = $("#password").val();
        let role = $("#role").val();
        let str;
        if (role == '用户'){
            str='User';
        }else if (role=='管理员'){
            str='Admin';
        }
        let url ="/BookManage/"+str+"Controller";
        $.ajax({
            url:url,
            data:{
                action:'login',
                username:username,
                password:password,
                role:role
            },
            dataType:'json',
            type:'get',
            success:function (respBean){
                alert(respBean.msg);
                if (respBean.status == 200){
                    let directory;
                    if (role == '用户'){
                        directory='user';
                    }else if (role=='管理员'){
                        directory='admin';
                    }
                    location.href=directory+'/index.html';
                }
            }
        })
    })
})