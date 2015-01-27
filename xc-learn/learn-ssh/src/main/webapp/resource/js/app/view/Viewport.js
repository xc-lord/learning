Ext.define('XC.view.Viewport',{
    extend: 'Ext.Viewport',
    layout: 'fit',
    hideBorders: true,
    requires : [
        'XC.view.HeaderView',
        'XC.view.MenuView',
        'XC.view.TabPanelView',
        'XC.view.SouthView'
    ],
    initComponent : function(){
        var me = this;
        Ext.apply(me, {
            items: [{
                id:'desk',
                layout: 'border',
                items: [
                    Ext.create('XC.view.HeaderView'),
                    Ext.create('XC.view.MenuView'),
                    Ext.create('XC.view.TabPanelView'),
                    Ext.create('XC.view.SouthView')
                ]
            }]
        });
        me.callParent(arguments);
    }
})