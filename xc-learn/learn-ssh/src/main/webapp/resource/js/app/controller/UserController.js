Ext.define('XC.controller.UserController', {
    extend: 'Ext.app.Controller',    
    
    views: ['user.UserListView','user.UserEditView'],
    stores: ['Users'],
    models: ['User'],
    
    init: function() {
    	console.info("init UserController");
    	var main = Ext.getCmp("content-panel3");
    	if(main) {
	        var tab = main.getComponent("userList");
	        
	        tab.add(Ext.widget("userlist"));
	        
	        //var p = main.add(Ext.widget("userlist"));
	        main.setActiveTab(tab);
    	}
        
        this.control({
            'userlist': {
                itemdblclick: this.editUser
            },
            'useredit button[action=save]': {
                click: this.updateUser
            }
        });
    },

    editUser: function(grid, record) {
        console.log('Double clicked on ' + record.get('name') + ':' + record.get('email'));
        
        var view = Ext.widget('useredit');
        view.down('form').loadRecord(record);
    },
    
    updateUser: function(button) {
    	console.log('clicked the Save button');
	    var win    = button.up('window'),
	        form   = win.down('form'),
	        record = form.getRecord(),
	        values = form.getValues();
	
	    record.set(values);
	    win.close();
	    this.getUsersStore().sync();
	}
});