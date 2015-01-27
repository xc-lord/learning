Ext.define('XC.view.user.UserEditView', {
    extend: 'Ext.window.Window',
    alias: 'widget.useredit',

    title: 'Edit User',
    layout: 'fit',
    autoShow: true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                items: [
                    {
                        xtype: 'textfield',
                        name : 'id',
                        fieldLabel: 'id'
                    },
                    {
                        xtype: 'textfield',
                        name : 'userName',
                        fieldLabel: 'userName'
                    },
                    {
                        xtype: 'textfield',
                        name : 'mobile',
                        fieldLabel: 'mobile'
                    },
                    {
                        xtype: 'textfield',
                        name : 'level',
                        fieldLabel: 'level'
                    },
                    {
                        xtype: 'textfield',
                        name : 'email',
                        fieldLabel: 'Email'
                    }
                ]
            }
        ];

        this.buttons = [
            {
                text: 'Save',
                action: 'save'
            },
            {
                text: 'Cancel',
                scope: this,
                handler: this.close
            }
        ];

        this.callParent(arguments);
    }
});