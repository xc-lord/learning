Ext.application({
    requires: ['Ext.container.Viewport'],
    name: 'XC',
    appFolder: 'resource/js/app',
    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: {
                xtype: 'userlist'
            }
        });
    },
    controllers: [
        'UserController'
    ]
    
});

console.dir(Ext.Loader.config.paths); 
