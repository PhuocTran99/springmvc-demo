<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.demo.model.BlogModel" %>
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

</head>

<body>
    <div class="container" style="margin-top: 0; padding: 0 120px">
        <form:form id="submitForm" modelAttribute="model">
<%--            <input type="hidden" id="blogId" name="id" value="${model.id}">--%>
            <form:hidden path="id" id="blogId"/>
            <table id="table" style="width: 800px" class="table table-striped">
                <tr>
                    <th style="background-color: #456bd8; color: whitesmoke">Category</th>
                    <td>
<%--                        <select name="categoryId">--%>
<%--                            <option>-- choose --</option>--%>
<%--                            <c:forEach var="item" items="${categories}">--%>
<%--                                <option value="${item.id}" <c:if test="${model.categoryId == item.id}">selected</c:if>>${item.name}</option>--%>
<%--                            </c:forEach>--%>
<%--                        </select>--%>
                        
                        <form:select path="categoryId">
                            <form:option  label="-- choose --" value="0"/>
                            <form:options items="${categories}"/>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <th style="background-color: #456bd8; color: whitesmoke">Title</th>
<%--                    <td><input style="width: 100%" type="text" name="title" value="${model.title}"></td>--%>
                    <td><form:input path="title" cssStyle="width: 100%"/></td>
                </tr>
                <tr>
                    <th style="background-color: #456bd8; color: whitesmoke">Thumbnail</th>
<%--                    <td><input style="width: 100%" type="file" name="thumbnail" value="${model.thumbnail}"></td>--%>
                    <td><form:input path="thumbnail" cssStyle="width: 100%"/></td>
                </tr>
                <tr>
                    <th style="background-color: #456bd8; color: whitesmoke">Short Description</th>
<%--                    <td><textarea name="shortDescription" cols="75" rows="3">${model.shortDescription}</textarea></td>--%>
                    <td><form:textarea path="shortDescription" cols="75" rows="3"/></td>
                </tr>
                <tr>
                    <th style="background-color: #456bd8; color: whitesmoke">Content</th>
<%--                    <td><textarea id="contentBlog" name="content" cols="75" rows="75">${model.content}</textarea></td>--%>
                    <td><form:textarea path="content" cols="75" rows="75"/></td>
                </tr>
            </table>
            </br>

            <input id="btnSubmitForm" type="submit" value="<c:choose><c:when test="${model.id == 0}">Create blog</c:when><c:otherwise>Update blog</c:otherwise></c:choose>">
        </form:form>
    </div>

    <script>
        var editor = '';
        $(document).ready(function () {
            editor = CKEDITOR.replace('contentBlog');
        });

        $('#btnSubmitForm').click(function (e) {
            e.preventDefault();
            var data = {};
            var formData = $('#submitForm').serializeArray();
            $.each(formData, function (i, v) {
               data[''+v.name+''] = v.value;
            });
            // data['content'] = editor.getData();
            var id = $('#blogId').val();
            if (id == '0') {
                createBlog(data);
            } else {
                updateBlog(data);
            }
        });

        function createBlog(data) {
            $.ajax({
                url: '${apiURL}',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (result) {
                    window.location.href = '${blogURL}';
                },
                error: function (problem) {
                    console.log(problem);
                }
            });
        }

        function updateBlog(data) {
            $.ajax({
                url: '${apiURL}',
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(data),
                dataType: 'json',
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
