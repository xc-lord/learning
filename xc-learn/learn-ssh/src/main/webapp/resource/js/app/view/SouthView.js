Ext.define('XC.view.SouthView',{
    extend: 'Ext.Toolbar',
    initComponent : function(){
        Ext.apply(this,{
            id:"bottom",
            //frame:true,
            region:"south",
            height:23,
            items:["当前用户：Lord2013",'->',"技术支持:<a href='http://blog.csdn.net/zhaoqiliang527/article/category/877716/1' target='_blank' style='text-decoration:none;'><font color='#0000FF'>Extjs4.X中文教程</font></a>&nbsp;&nbsp;"]
        });
        this.callParent(arguments);
    }
})