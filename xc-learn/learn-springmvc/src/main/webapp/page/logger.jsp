<%--
  Created by IntelliJ IDEA.
  User: lord
  Date: 2015/2/23
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>日志级别动态修改</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="${cssServer}/bootstrap.min.css" rel="stylesheet">
    <link href="${cssServer}/bootstrap-responsive.min.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="//cdnjs.bootcss.com/ajax/libs/html5shiv/3.6.2/html5shiv.js"></script>
    <![endif]-->

</head>

<body>

<div class="container">
    <div class="row">
        <div class="text-center">
            <h2>日志级别动态修改</h2>

            <form class="form-inline" action="${dynSite}/loggerInfo.do" method="post">
                类全名：
                <input name="loggerName" type="text" class="input-xxlarge" value="${loggerInfo.packageName}"/>
                <button type="submit" class="btn">查询</button>
            </form>
        </div>

        <table class="table table-hover table-striped">

            <thead>
            <tr>
                <th>类名</th>
                <th>日志级别</th>
                <th>修改级别</th>
            </tr>
            </thead>

            <c:forEach items="${loggerInfo.list}" var="item">
                <tr>
                    <td class="clazzName">${item.className}</td>
                    <td class="oldLevel">
                        <c:choose>
                            <c:when test="${item.loggerLevel eq 'INFO'}">
                                <span class="label label-info">${item.loggerLevel}</span>
                            </c:when>
                            <c:when test="${item.loggerLevel eq 'WARN'}">
                                <span class="label label-warning">${item.loggerLevel}</span>
                            </c:when>
                            <c:when test="${item.loggerLevel eq 'ERROR'}">
                                <span class="label label-important">${item.loggerLevel}</span>
                            </c:when>
                            <c:when test="${item.loggerLevel eq 'DEBUG'}">
                                <span class="label label-success">${item.loggerLevel}</span>
                            </c:when>
                            <c:otherwise>
                                <span class="label">${item.loggerLevel}</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <select name="loggerLevel">
                            <option value="default">更改日志级别</option>
                            <option value="INFO">INFO</option>
                            <option value="DEBUG">DEBUG</option>
                            <option value="WARN">WARN</option>
                            <option value="ERROR">ERROR</option>
                        </select>
                    </td>
                </tr>
            </c:forEach>

        </table>
        <div>
            <ul class="breadcrumb">
                <li>
                    <strong>共有</strong> <span class="text-success">${loggerInfo.total}</span>
                    <strong>个类，其中类前缀为 ${loggerInfo.packageName} 的类有</strong>
                    <span class="text-success">${loggerInfo.count}</span>
                    <strong>个。</strong>
                </li>
            </ul>
        </div>
    </div>
</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="${jsServer}/jquery-1.10.1.min.js"></script>
<script src="${jsServer}/bootstrap.min.js"></script>

<script>
    $(document).ready(function () {
        $("select[name='loggerLevel']").change(function () {
            var loggerLevel = $(this).val();//日志级别
            var parentTr = $(this).parent().parent();
            var clazzName = parentTr.children(".clazzName").html();//类名

            if (loggerLevel != "default") {

                //发送Ajax请求修改日志级别
                $.ajax({
                    type:'GET',                                //jsonp 类型下只能使用GET,不能用POST,这里不写默认为GET
                    url:'${dynSite}/changeLoggerLevel.do',       //跨域到http://www.wp.com，另，http://test.com也算跨域
                    data:{"loggerName" : clazzName, "level" : loggerLevel },                //数据参数
                    dataType:'jsonp',                          //指定为jsonp类型
                    jsonp:'callback',                          //服务器端获取回调函数名的key，对应后台有$_GET['callback']='getName';callback是默认值
                    jsonpCallback:'getName',                   //回调函数名
                    success:function(result){                  //成功执行处理，对应后台返回的getName(data)方法。

                        alert(result.msg);

                        //设置新级别的样式
                        var labCls = " label-important";
                        if (loggerLevel == "INFO") {
                            labCls = " label-info";
                        } else if (loggerLevel == "WARN") {
                            labCls = " label-warning";
                        } else if (loggerLevel == "DEBUG") {
                            labCls = " label-success";
                        }
                        var spanStr = "<span class='label" + labCls + "'>" + loggerLevel + "</span>";

                        parentTr.children(".oldLevel").html(spanStr);
                    },
                    error:function(msg){
                        alert(msg.toSource());                 //执行错误
                    }
                });

            }

        });
    });
</script>

</body>
</html>

