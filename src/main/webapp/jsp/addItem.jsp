<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加商品</title>
    <link rel="stylesheet" href="/layui/css/layui.css"  media="all">
    <script src="/js/jquery-2.1.0.min.js"></script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>添加商品信息</legend>
</fieldset>
<form class="layui-form" action="" style="margin-top: 20px">
    <!--商品标题-->
    <div class="layui-form-item">
        <label class="layui-form-label">商品标题</label>
        <div class="layui-input-block">
            <input type="text" id="title" name="title" style="width: 200px" placeholder="请输入标题" class="layui-input">
        </div>
    </div>
    <!--商品卖点-->
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">商品卖点</label>
        <div class="layui-input-block">
            <textarea id="sellPoint" name="sellPoint" style="width: 200px" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <!--商品卖点-->
    <div class="layui-form-item">
        <label class="layui-form-label">商品价格</label>
        <div class="layui-input-inline">
            <input type="text" id="price" name="price" style="width: 200px"  placeholder="请输入价格"  class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">不超过小数点后两位</div>
    </div>
    <!--商品库存-->
    <div class="layui-form-item">
        <label class="layui-form-label">商品库存</label>
        <div class="layui-input-block">
            <input type="text" id="num" name="num" style="width: 200px" class="layui-input">
        </div>
    </div>
    <!--条形码-->
    <div class="layui-form-item">
        <label class="layui-form-label">条形码</label>
        <div class="layui-input-block">
            <input type="text" id="barcode" name="barcode" style="width: 200px" class="layui-input">
        </div>
    </div>
    <!--图片上传-->
    <div class="layui-form-item">
        <div class="layui-upload">
            <label class="layui-form-label">商品图片</label>
            <button type="button" class="layui-btn" id="test1">上传图片</button>
            <blockquote class="layui-elem-quote layui-quote-nm" style="width:300px;margin-top:10px;margin-left: 50px">
                预览图：
                <div class="layui-upload-list" id="demo2" style="width:300px">
                    <img class="layui-upload-img" id="demo1" style="width:300px">
                    <p id="demoText"></p>
                </div>
            </blockquote>
        </div>
    </div>
    <!--提交-->
    <div class="layui-form-item">
        <div class="layui-input-block" style="margin-left: 50px">
            <button type="button" class="layui-btn layui-btn-normal" id="btn-add">添加</button>
        </div>
    </div>
</form>
<script src="/layui/layui.js" charset="utf-8"></script>
<script src="/js/addItem.js"></script>
</body>
