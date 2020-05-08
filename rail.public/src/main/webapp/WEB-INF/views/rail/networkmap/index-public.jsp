<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/11
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
<body>
    <div class="row">
        <div id="container" class="col-md-12" style="min-height: 600px;"></div>
    </div>
</body>
<critc-script>
    <script charset="utf-8" src="https://map.qq.com/api/js?v=2.exp&key=SJ3BZ-QX3LF-3AKJA-NRZCQ-ILP4J-H4FKN"></script>
    <script>
        jQuery.staticServer = "${staticServer}";
        jQuery.dynamicServer = "${dynamicServer}";

        (function ($) {
            $(function () {
                var center = new qq.maps.LatLng(39.916527, 116.397128);
                var map = new qq.maps.Map(document.getElementById('container'), {
                    center: center,
                    zoom: 4
                });
                //point marker
                var marker = new qq.maps.Marker({
                    position: center,
                    map: map
                });
                //point marker
                var marker = new qq.maps.Marker({
                    position: new qq.maps.LatLng(29.916527, 116.397128),
                    map: map
                });
            });
        })(jQuery);
    </script>
</critc-script>
</html>