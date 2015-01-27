Ext.Loader.setConfig({enabled:true});

Ext.application({
	name: 'XC',
	appFolder: 'resource/js/app',
	controllers: ['MenuController'],
	autoCreateViewport: true
});