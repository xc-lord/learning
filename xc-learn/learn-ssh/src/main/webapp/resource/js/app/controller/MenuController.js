Ext.define('XC.controller.MenuController', {
    extend: 'Ext.app.Controller',    
    
    views: ['MenuView'],
    
    init: function() {
        this.control({
            'panel#menu-panel': {
                itemmousedown: this.loadMenu
            }
        });
    },
	loadMenu:function(selModel, record){
        if (record.get('leaf')) {
        	var panelName = record.get('text');
        	var panelCls = record.get('cls');
            var panel = Ext.getCmp(panelCls);	//根据ID查找组件
            if(!panel){            			        
                panel = { 
                    title: 'New Tab ' + panelName, 
                    iconCls: 'tabs',
                    layout: 'fit',
                    closable: true,
                    id: panelCls
                };
                this.openTab(panel,panelCls);
            }else{
                var main = Ext.getCmp("content-panel");
                main.setActiveTab(panel); 
                console.info("find panel");
            }
        } 
    },
    openTab : function (panel,panelCls){ 
        var o = (typeof panel == "string" ? panel : panelCls || panel.panelCls);
        console.info(o);
        
        var main = Ext.getCmp("content-panel");
        var tab = main.getComponent(o);      
        if (tab) {
            main.setActiveTab(tab); 
        } else if(typeof panel!="string"){ 
            panel.panelCls = o; 
            
            var application = XC.getApplication();
			var controller = application.getController(o);
            console.info("controller : " + controller);
            if(controller) {
				application.controllers.add(controller);
				//userController.init(application);//4.2不需要
				/**
            	 * 一个控制器第一个视图为模块主功能视图
            	 */
            	var viewName = controller.views[0];
            	/***
            	 * 视图结构: 目录为模块名, 视图类型为目录内文件名的小写.
            	 * @example
            	 * App.viw.theme.Theme
            	 * alias : 'widget.theme'
            	 * viewType: theme
            	 */
            	var viewType = viewName.split('.')[1].toLowerCase();
            	
            	/**
            	 * 如果没有此视图, 创建视图.
            	 */
            	if (!main.down(viewType)) {
            		var view = controller.getView(viewName);
                    var vpanel = view.create();
                    vpanel.id = panelCls;
                    main.add(vpanel);
                    main.setActiveTab(vpanel);
                    vpanel.doLayout();
            	}
            	/**
            	 * 如果有此视图, 刷新视图.
            	 */
            	else {
            		var vpanel = main.down(viewType);
                    main.setActiveTab(vpanel);
                    vpanel.doLayout();
            	}
            } else {
            	var p = main.add(panel);
            	main.setActiveTab(p);
            }
        }
    }

})