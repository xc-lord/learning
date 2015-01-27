Ext.define('XC.view.user.UserListView' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.userlist',

    title: '用户管理',
    id: 'userlist',
    store: 'Users',
    layout : 'fit',
    stateful: true,
    stripeRows: true,
    closable: true,

    initComponent: function() {
        this.columns = [
            {header: 'ID',  dataIndex: 'id',  flex: 1},
            {header: 'userName', dataIndex: 'userName', flex: 1},
            {header: 'email', dataIndex: 'email', flex: 1},
            {header: 'mobile', dataIndex: 'mobile', flex: 1},
            {header: 'level', dataIndex: 'level', flex: 1}
        ];

        this.callParent(arguments);
    }
});