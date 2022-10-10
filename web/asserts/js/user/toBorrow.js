$(function (){
    $.ajax({
        url:"/BookManage/UserController",
        data:{
            action:'queryBorrow',
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
    let bookStatus;
    for (let i = 0; i < respBean.data.length; i++){
        switch (respBean.data[i].status){
            case 0:
                bookStatus  ='已还';
                break;
            case 1:
                bookStatus = '未还'
                break;
        }
        tr = "<tr>" +
            "<td>" + respBean.data[i].username + "</td>" +
            "<td>" + respBean.data[i].bookName  + "</td>" +
            "<td>" + bookStatus  + "</td>" +
            "<td>" +
            "<input class='btn btn-default' type='button' value='还书' onclick='returnBook(" + respBean.data[i].id + ")'>" +
            "<td>" +
            "</tr>";
        $("#tbody").append(tr)
    }
}
function returnBook(obj){
    let id =obj;
    $.ajax({
        url:"/BookManage/UserController",
        data:{
            action:'returnBook',
            id:id
        },
        type:'get',
        dataType:"json",
        success:function (respBean){
            if(respBean.status == 500){
                alert(respBean.msg);
                location.href='toBorrow.html';
            }else if(respBean.status == 200){
                alert(respBean.msg);
                location.href='toBorrow.html';
            }
        }
    })
}