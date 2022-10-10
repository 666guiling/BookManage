$(function (){
    //总记录数
    var totalNum = 0;
    //总页数
    var totalPage = 0;
    //默认每页显示数
    var avageNum = 3;
    //默认显示第一页
    var currentPage = 1;

    getUserList(currentPage,avageNum);
    initPagination(totalNum,totalPage);
    function getUserList(pageNum,pageSize){
        currentPage = pageNum;
        $.ajax({
            url:"/BookManage/AdminController",
            data:{
                action:"getBookList2",
                "pageNum":pageNum,
                "pageSize":pageSize
            },
            type: 'get',
            dataType: 'json',
            async:false,
            success:function(respBean){

                //alert(respBean.data.list[0].book.id)
                //alert(respBean.data.list[0].bookClassIfyList[0].name)
                btn(respBean)
            }
        });
    }

    function btn(respBean){
        $("#tbody").html(' ');
        let bookClass;
        totalPage = respBean.data.totalCount;
        totalNum= respBean.data.pageCount;
        for (let i = 0;i < respBean.data.list.length;i++){
            //根据索引获取借阅集合
            let bookClassIfyList = respBean.data.list[i].bookClassIfyList;
            for (let j = 0;j<bookClassIfyList.length;j++){
                if (j ==0){
                    bookClass = bookClassIfyList[j].name+' ';
                }else {
                    bookClass += bookClassIfyList[j].name+' ';
                }
            }
            tr = "<tr>" +
                "<td>" + bookClass + "</td>" +
                "<td>" + respBean.data.list[i].book.bookName + "</td>" +
                "<td>" + respBean.data.list[i].book.id + "</td>" +
                "<td class='td_img'><img width='100px' src='" + respBean.data.list[i].book.imgUrl + "' class='img-circle'></td>" +
                "<td>" + respBean.data.list[i].book.detail + "</td>" +
                "<td>" + respBean.data.list[i].book.counts + "</td>" +
                "<td>" + respBean.data.list[i].book.author + "</td>" +
                "<td>" +
                //点击编辑发送请求把员工 id 存放到 session ,响应回前端,前端跳转到修改页面,修改页面再发送ajax请求根据 id 获取 对象信息
                "<input class='btn btn-default' type='button' value='编辑' onclick='update(" + respBean.data.list[i].book.id + ")'>" +
                //点击删除发送请求把员工 id 发送到后端进行删除，删除成功后重新加载页面
                "<input class='btn btn-default' type='button' value='删除' onclick='del(" + respBean.data.list[i].book.id + ")'>" +
                "</td>" +
                "</tr>";
            $("#tbody").append(tr);
        }
    }
    //初始化分页栏
    function initPagination(totalPage,totalNum) {
        $('#pagination').html(" ");
        $('#pagination').append(
            '<ul class="pagination" style="display:inline;">' +
            '<span style="float: right;">每页显示' +
            '<select id="dataNum">' +
            '<option value="1">1</option>' +
            '<option value="2">2</option>' +
            '<option value="3">3</option>' +
            '</select>条记录,总共有' + totalPage + '页，总共' + totalNum + '条记录</span>' +
            '</ul>'
        )
        // onclick="getUserList('+ (currentPage - 1) + ','+ avageNum + ')"
        $("#pagination ul").append(
            '<li><a href="javascript:void(0);" id="prev">上一页</a>'
        )
        for (var i = 1; i <= totalPage; i++) {
            $("#pagination ul").append(
                '<li id="page'+i+'"><a href="javascript:void(0);"  onclick="getUserList(' + i + ',' + avageNum + ')">' + i + '</a>'
            )
        }
        $("#pagination ul").append(
            '<li><a href="javascript:void(0);"  id="next">下一页</a>'
        )
        $("select option[value=" + avageNum + "]").attr('selected', true)
        $("#page1").addClass("active");
        checkA();
    }
    $(document).off('change','#dataNum').on('change','#dataNum',function(){
        avageNum = $(this).children('option:selected').val();
        currentPage = 1;
        getUserList(currentPage,avageNum);
        initPagination(totalPage,totalNum);
    });
    function checkA() {
        currentPage == 1 ? $("#prev").addClass("btn btn-default disabled") : $("#prev").removeClass("btn btn-default disabled");
        currentPage == totalPage ? $("#next").addClass("btn btn-default disabled") : $("#next").removeClass("btn btn-default disabled");
    }
    //设置分页栏点击时候的高亮
    $("#pagination").on("click","li",function(){
        var aText = $(this).find('a').html();
        checkA();
        if (aText == "上一页"){
            $(".pagination > li").removeClass("active");
            $("#page"+(currentPage -1)).addClass("active");
            getUserList(currentPage - 1,avageNum);
            checkA();
        }else if(aText == "下一页"){
            $(".pagination > li").removeClass("active");
            $("#page"+(currentPage + 1)).addClass("active");
            getUserList(currentPage + 1,avageNum);
            checkA();
        }else{
            $(".pagination > li").removeClass("active");
            $(this).addClass("active");
        }
    })
})

//============================================================================
/*$(function (){
    $.ajax({
        url:"/BookManage/AdminController",
        data:{
            action:'getBookList'
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
})*/

//模糊搜索
$(function () {
    $("#selectBookName").click(function () {
        //1.清空 tbody 里的内容
        $("#tbody").html ('');
        //2.获取输入框的value值
        let value = $("#bookName").val();
        //3.发送 ajax 请求
        $.ajax({
            url: "/BookManage/AdminController",
            data: {
                action: "getBookBoListByBookName",
                bookName: value
            },
            type: 'get',
            dataType: 'json',
            success: function (respBean) {
                if (respBean.status==200){
                    btn1(respBean);
                }
            }
        })
    })
})
function btn1(respBean){
    let bookClass;
    for (let i = 0;i < respBean.data.length;i++){
        //根据索引获取借阅集合
        let bookClassIfyList = respBean.data[i].bookClassIfyList;
        for (let j = 0;j<bookClassIfyList.length;j++){
            if (j ==0){
                bookClass = bookClassIfyList[j].name+' ';
            }else {
                bookClass += bookClassIfyList[j].name+' ';
            }
        }
        tr = "<tr>" +
            "<td>" + bookClass + "</td>" +
            "<td>" + respBean.data[i].book.bookName + "</td>" +
            "<td>" + respBean.data[i].book.id + "</td>" +
            "<td class='td_img'><img width='100px' src='" + respBean.data[i].book.imgUrl + "' class='img-circle'></td>" +
            "<td>" + respBean.data[i].book.detail + "</td>" +
            "<td>" + respBean.data[i].book.counts + "</td>" +
            "<td>" + respBean.data[i].book.author + "</td>" +
            "<td>" +
            //点击编辑发送请求把员工 id 存放到 session ,响应回前端,前端跳转到修改页面,修改页面再发送ajax请求根据 id 获取 对象信息
            "<input class='btn btn-default' type='button' value='编辑' onclick='update(" + respBean.data[i].book.id + ")'>" +
            //点击删除发送请求把员工 id 发送到后端进行删除，删除成功后重新加载页面
            "<input class='btn btn-default' type='button' value='删除' onclick='del(" + respBean.data[i].book.id + ")'>" +
            "</td>" +
            "</tr>";
        $("#tbody").append(tr);
    }
}
function update(obj){
    let id=obj
    location.replace("updateBook.html");
    window.sessionStorage.setItem('id', id);
}
function del(obj){
    let id=obj
    alert(id)
    $.ajax({
        url:"/BookManage/AdminController",
        data:{
            action:'delBook',
            id:id
        },
        type:'get',
        dataType:'json',
        success:function (respBean){
            alert(respBean.msg)
            location.href="../admin/getBookList.html"
        }
    })

}