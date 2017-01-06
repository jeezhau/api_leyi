<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
 <title>WeUI</title>
 <link rel="stylesheet" href="/m_leyi/css/weui.css"/>
</head>
<body>
	<a href="#" class="weui-btn weui-btn_disabled weui-btn_primary">按钮</a>
<a href="#" class="weui-btn weui-btn_warn">确认</a>
<a href="#" class="weui-btn weui-btn_disabled weui-btn_warn">确认</a>
<a href="#" class="weui-btn weui-btn_default">按钮</a>
<a href="#" class="weui-btn weui-btn_disabled weui-btn_default">按钮</a>
<div class="button_sp_area">
    <a href="#" class="weui-btn weui-btn_plain-default">按钮</a>
    <a href="#" class="weui-btn weui-btn_plain-primary">按钮</a>

    <a href="#" class="weui-btn weui-btn_mini weui-btn_primary">按钮</a>
    <a href="#" class="weui-btn weui-btn_mini weui-btn_default">按钮</a>
</div>
<div class="weui-cells__title">带说明的列表项</div>
<div class="weui-cells">
    <div class="weui-cells">
        <div class="weui-cell__bd weui-cell_primary">
            <p>标题文字</p>
        </div>
        <div class="weui-cell__ft">
            说明文字
        </div>
    </div>
</div>
<div class="weui-dialog_confirm">
    <div class="weui-mask"></div>
    <div class="weui-dialog">
        <div class="weui-dialog__hd"><strong class="weui-dialog__title">弹窗标题</strong></div>
        <div class="weui-dialog__bd">自定义弹窗内容，居左对齐显示，告知需要确认的信息等</div>
        <div class="weui-dialog__ft">
            <a href="#" class="weui-btn_default">取消</a>
            <a href="#" class="weui-btn_primary">确定</a>
        </div>
    </div>
</div>
</body>
</html>