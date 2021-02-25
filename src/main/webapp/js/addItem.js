$(function () {
    var image;
    layui.use(['form','upload'] ,function(){
        var form = layui.form
            ,layer = layui.layer
            ,upload = layui.upload;

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#test1'
            ,url: '/item/upload'
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                //上传成功 java发送过来的数据 url地址 绑定到页面上
                image = res.data;
                //console.log(res);
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });

        //提交添加商品的按钮点击
        $("#btn-add").click(function () {
            var t = $("#title").val();
            var sp = $("#sellPoint").val();
            var p = $("#price").val();
            var num = $("#num").val();
            var b = $("#barcode").val();
            //ajax请求
            $.ajax({
                type:"post",
                url:"/item/addItem",
                data:"title="+t+"&sellPoint="+sp+"&price="+p*100+"&num="+num+"&barcode="+b+"&image="+image,
                success:function(msg){
                    layer.alert(msg.data);
                }
            })
        })
    });


})