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
$(function (){
    $("#form").bootstrapValidator({
        //设置校验图标
        feedbackIcons:{
            //校验通过时输入框的图标
            valid:'glyphicon glyphicon-ok',
            //校验失败时输入框的图标
            invalid:'glyphicon glyphicon-remove',
            //校验过程中的输入框的图标
            validating:'glyphicon glyphicon-refresh'
        },
        //设置需要校验的属性
        fields:{
            //根据表单的name属性匹配
            username:{
                //设置校验规则
                validators:{
                    //非空校验
                    notEmpty:{
                        //提示语句
                        message:"用户名不能为空"
                    },
                    //长度限制
                    stringLength:{
                        min:2,
                        max:12
                    }
                }
            },
            password:{
                //设置校验规则
                validators:{
                    //非空校验
                    notEmpty:{
                        //提示语句
                        message:"密码不能为null"
                    }
                }
            },
            phone:{
                //设置校验规则
                validators:{
                    //正则表达式
                    regexp:{
                        //正则表达式规则
                        regexp:'^(1[0-9][0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$',
                        message:'手机号码不符合规范'
                    }
                }
            }
        }
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
        let username = $("#username").val();
        let password = $("#password").val();
        let phone = $("#phone").val();
        let sex = $("#sex").val();
        let file = $("#file")[0].files[0];
        let formData = new FormData;
        formData.append("action", 'register');
        formData.append("file", file);
        formData.append("username", username);
        formData.append("password", password);
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
                    location.href="../login.html";
                }
            }
        })
    })
})