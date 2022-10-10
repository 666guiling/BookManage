
    $(function (){
    $.ajax({
        url:"/BookManage/UserController",
        data:{
            action:'toUser'
        },
        type:'get',
        dataType:"json",
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
    tr = "<tr>" +
        "<td class='td_img'><img width='100px' src='" + respBean.data.imgUrl + "' class='img-circle'></td>" +
        "<td>" + respBean.data.username + "</td>" +
        "<td>" + respBean.data.phone  + "</td>" +
        "<td>" + respBean.data.sex  + "</td>" +
        "</tr>";
    $("#tbody").append(tr)
}
