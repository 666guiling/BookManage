$(function (){
    $("#tbody").html ('');
    $.ajax({
        url: "/BookManage/AdminController",
        data: {
            action: "toBorrow",
        },
        type: 'get',
        dataType: 'json',
        success: function (respBean) {
            if (respBean.status==200){
                btn(respBean);
            }
        }
    })
})
function btn(respBean){
    let bookStatus;
    for (let i = 0;i < respBean.data.length;i++){
        switch (respBean.data[i].status){
            case 0:
                bookStatus  ='已还';
                break;
            case 1:
                bookStatus = '未还'
                break;
        }
        tr="<tr>" +
            "<td>" + respBean.data[i].username + "</td>" +
            "<td>" + respBean.data[i].bookName + "</td>" +
            "<td>" + bookStatus + "</td>" +
            "<td>" +
            "<input class='btn btn-default' type='button' value='查看图书详情' onclick='queryBook(" + respBean.data[i].bookId + ")'>" +
            "<input class='btn btn-default' type='button' value='查看用户详情' onclick='queryUser(" + respBean.data[i].userId + ")'>" +
            "</tr>";
        $("#tbody").append(tr);
    }
}
function queryBook(obj){
    let id=obj
    location.replace("toBook.html");
    window.sessionStorage.setItem('id', id);
}
function queryUser(obj){
    let id=obj
    location.replace("toUser1.html");
    window.sessionStorage.setItem('id', id);
}