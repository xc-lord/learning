Ext.define('XC.controller.MainController',{
	extend: 'Ext.app.Controller',
	init: function() {
		var application = XC.getApplication();
		var menuController = application.getController('MenuController');
		console.info("new panel 1");
        application.controllers.add(application);
        console.info("new panel 2");
        menuController.init(application);
        console.info("new panel 3");
        menuController.onLaunch(application);
        console.info("new panel 4");
	}
	
})