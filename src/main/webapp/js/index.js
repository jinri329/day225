$(function () {
    //JavaScript代码区域 layui是模块化语言
    layui.use('element', function(){
        var element = layui.element;
    });
    $("dd>a").click(function () {
       $("#iframeMain").attr("src",$(this).attr("page"));
    })

})