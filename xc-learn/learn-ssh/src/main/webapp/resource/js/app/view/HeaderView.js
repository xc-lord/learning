Ext.define('XC.view.HeaderView', {
    extend: 'Ext.Component',
    initComponent: function() {
        Ext.applyIf(this, {
            xtype: 'box',
            cls: 'header',
            region: 'north',
            html: '<h4 style="text-align:center">时光的灰烬管理系统</h4>',
            height: 40
        });
        this.callParent(arguments);
    }
});