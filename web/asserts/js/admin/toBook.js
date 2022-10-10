let id = window.sessionStorage.getItem('id');
$(function (){
    $.ajax({
        url:"/BookManage/AdminController",
        data:{
            action:'toBook',
            id:id
        },
        type:'get',
        dataType:"json",
        success:function (respBean){
            if(respBean.status == 500){
                alert(respBean.msg);
                location.href='queryBorrow.html';
            }else if(respBean.status == 200){
                btn(respBean);
            }
        }
    })
})
function btn(respBean){
    tr = "<tr>" +
        "<td>" + respBean.data.bookName + "</td>" +
        "<td>" + respBean.data.id + "</td>" +
        "<td class='td_img'><img width='100px' src='" + respBean.data.imgUrl + "' class='img-circle'></td>" +
        "<td>" + respBean.data.detail + "</td>" +
        "<td>" + respBean.data.counts + "</td>" +
        "<td>" + respBean.data.author + "</td>" +
        "</tr>";
    $("#tbody").append(tr);
}