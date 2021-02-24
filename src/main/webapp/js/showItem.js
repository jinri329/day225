$(function () {
    layui.use(['table','form'], function(){
        //只能在这个function的花括号里面 才能访问这个table
        var table = layui.table,
            form = layui.form;
        form.render();
        table.render({
            elem: '#test'
            ,url:'/item/showItemPage'
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '商品表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', title:'商品编号', width:80, fixed: 'left', unresize: true, sort: true}
                ,{field:'title', title:'商品名称', width:280, edit: 'text'}
                ,{field:'sellPoint', title:'商品卖点', width:180, edit: 'text'}
                ,{field:'barcode', title:'商品价格', width:100, edit: 'text', sort: true}
                ,{field:'num', title:'商品数量', width:100}
                ,{field:'image', title:'商品图片',templet: '<div><img src="{{d.image}}" /><div/>'}
                ,{field:'status', title:'商品状态', width:120, sort: true,templet:"#itemStatus"}
                ,{field:'created', title:'创建时间', width:180, sort: true,templet:'<div>{{ layui.util.toDateString(d.created, "yyyy-MM-dd HH:mm:ss") }}</div>'}
                ,{field:'updated', title:'更新时间', width:180, sort: true,templet:'<div>{{ layui.util.toDateString(d.created, "yyyy-MM-dd HH:mm:ss") }}</div>'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
                ]]
            ,page: true
            ,limit:15
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            //封装统一方法
            var utils = function (url,op) {
                var data = checkStatus.data;
                var id = new Array();
                $.each(data,function (i,n) {
                    id.push(n["id"]);
                })
                console.log(id);
                table.reload('test', {
                    url:url
                    ,where: {
                        ids:id
                        ,op:op
                    }
                });
            }
            switch(obj.event){
                //状态3 删除
                case 'selectDel':
                    utils('/item/itemDel',3);
                    break;
                case 'selectDown':
                    utils('/item/itemDown',2);
                    break;
                case 'selectUp':
                    utils('/item/itemUp',1);
                    break;

                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            };


        });

        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                layer.prompt({
                    formType: 2
                    ,value: data.email
                }, function(value, index){
                    obj.update({
                        email: value
                    });
                    layer.close(index);
                });
            }
        });
        $("#btn_search").click(function () {
            var t =$("#title").val();
            var p_min =$("#price_min").val();
            var p_max =$("#price_max").val();
            var n =$("#num").val();
            var s =$("#status").val();
            console.log(t,p_min,p_max,n,s);
            table.reload('test', {
                url:"/item/search"
                ,where: {
                    title:t
                    ,price_min:p_min*100
                    ,price_max:p_max*100
                    ,num:n
                    ,status:s
                }
            });
        })
    });

})