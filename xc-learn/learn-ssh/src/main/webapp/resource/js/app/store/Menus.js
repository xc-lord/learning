Ext.define('XC.store.Menus',{
    extend: 'Ext.data.TreeStore',
    root: { 
        expanded: true 
    }, 
    proxy: { 
        type: 'ajax', 
        url: dynSite + '/ajax/category_showTree?catalog=' + manageRootCatalog
    } 
})