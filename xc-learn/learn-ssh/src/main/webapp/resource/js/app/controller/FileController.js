Ext.define('XC.controller.FileController', {
    extend: 'Ext.app.Controller',    
    views: ['file.FileListView'],
    init: function() {
    	console.info("init FileController");
    	var main = Ext.getCmp("content-panel3");
    	if(main) {
	        alert("tab is " + tab);
	        var tab = main.getComponent("fileList");
	        tab.add(Ext.widget("filelist"));
	        
	        //var p = main.add(Ext.widget("userlist"));
	        main.setActiveTab(tab);
    	}
    }
    
});