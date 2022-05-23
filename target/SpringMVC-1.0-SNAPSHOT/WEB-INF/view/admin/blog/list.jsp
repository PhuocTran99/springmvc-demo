<%@ page import="com.demo.model.BlogModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>

<c:url var="apiURL" value="/api/blog"/>
<c:url var="blogURL" value="/admin-blog/list"/>

<html>
<head>
    <title>MY SUNDAY</title>
    <link rel="stylesheet" href="<c:url value="/template/admin/css/style.css"/>">

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<%--    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">--%>
    <style>
        .pagination.disabled a,  .pagination.disabled a:hover,  .pagination.disabled a:focus,  .pagination.disabled span {
            color: #eee;
            background: #fff;
            cursor: default;
        }

        .pagination { float: left; }

        .pagination.disabled li.active a {
            color: #fff;
            background: #cccccc;
            border-color: #cccccc;
        }

        .paging-container select {
            float: left;
            margin: 20px 0 20px 10px;
            padding: 9px 3px;
            border-color: #ddd;
            border-radius: 4px;
        }

        #table { margin-bottom: 0; }
    </style>
    <link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="<c:url value="/template/admin/js/pagination.js"/>"></script>

    <script>

        $(function () {
            var i = 1;
            <c:forEach var="item" items="${model.listModel}">
<%--                <c:url var="editURL" value="/admin-blog/">--%>
<%--                    <c:param name="type" value="edit"/>--%>
<%--                    <c:param name="id" value="${item.id}"/>--%>
<%--                </c:url>--%>
                $('#table').append('<tr class="data"><td>' + i + '</td><td>${item.title}</td><td>${item.shortDescription}</td><td><a class="nav-link" href="<c:url value="/admin-blog/edit?id=${item.id}"/>"><i class="fas fa-edit"></i></a></td><td><input id="checkbox_${item.id}" type="checkbox" name="id" value="${item.id}"></td></tr>');
                i++;
            </c:forEach>


            load = function() {
                window.tp = new Pagination('#tablePaging', {
                    itemsCount: ${model.listModel.size()},
                    onPageSizeChange: function (ps) {
                        console.log('changed to ' + ps);
                    },
                    onPageChange: function (paging) {
                        //custom paging logic here
                        console.log(paging);
                        var start = paging.pageSize * (paging.currentPage - 1),
                            end = start + paging.pageSize,
                            $rows = $('#table').find('.data');

                        $rows.hide();

                        for (var i = start; i < end; i++) {
                            $rows.eq(i).show();
                        }
                    }
                });
            }

            load();
        });
    </script>

</head>

<body>
    <div>
        <ul style="list-style: none; float: right; padding-left: 0; margin-right: 70px">
            <li><a style="font-size: 30px; margin-right: 20px;" href="<c:url value="/admin-blog/edit"/>" title="Create"><i class="fas fa-folder-plus"></i></a></li>
            <br>
            <li><a id="btnDelete" style="font-size: 30px" href="" title="Delete"><i class="fas fa-trash-alt"></i></a></li>
        </ul>
    </div>
    <div class="container" style="margin-top: 0; padding: 0 120px">
        <form id="form1">
            <table id="table" style="margin: auto; width: 96%" class="table table-striped">
                <tr style="background-color: #456bd8; color: whitesmoke">
                    <th>Blog ID</th>
                    <th>Title</th>
                    <th>Short Description</th>
                    <th>Update</th>
                    <th>Delete<div>(all <input id="" type="checkbox" name="" value="">)</div></th>
                </tr>
            </table>
            <div class="paging-container" id="tablePaging"></div>
            <div style="clear: both"></div>
            <a href="javascipt:void(0)" onclick="load()">reload</a> | <a href="javascipt:void(0)" onclick="tp.disable()">disble paging</a> | <a href="javascipt:void(0)" onclick="tp.enable()">enable paging</a>
        </form>
    </div>

    <script>
        $('#btnDelete').click(function (e) {
            e.preventDefault();
            var data = $('table input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteBlog(data);
        });

        function deleteBlog(data) {
            $.ajax({
                url: '${apiURL}',
                type: 'DELETE',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (result) {
                    window.location.href = '${blogURL}';
                },
                error: function (problem) {
                    console.log(problem);
                }
            });
        }
    </script>
</body>
</html>