Ext.define('XC.store.Users', {
    extend: 'Ext.data.Store',
    model: 'XC.model.User',
    autoLoad: true,
    
    proxy: {
	    type: 'ajax',
	    api: {
	        read: dynSite + '/ajax/user_list',
	        update: dynSite + '/ajax/user_list'
	    },
	    reader: {
	        type: 'json',
	        root: 'users',
	        successProperty: 'success'
	    }
	}
    /*
    proxy: {
        type: 'ajax',
        url: 'http://localhost:8080/lord/ajax/jsonData.jsp',
        reader: {
            type: 'json',
            root: 'users',
            successProperty: 'success'
        }
    },data: [
        {name: '掌上电脑',    email: 'ed@sencha.com'},
        {name: 'Tommy', email: 'tommy@sencha.com'}
    ]*/
    
});