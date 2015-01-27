package com.lord.comm.page;

public class PageStyle {

	private static final int PAGINATION_SIZE = 10;

	public String generateHtml(Pager pager) {
		
		if(!pager.isNeedPaging()) {
			return "<!-- 暂无分页 -->";
		}
		String html = "<ul>\n";
		
		if(pager.isHasPrev())
			html += "<li><a href='" + pager.getPrevUrl() + "'>上一页</a></li>\n";
		else
			html += "<li class='disabled'><span>上一页</span></li>\n";
		
		if(pager.getPageSize() != null && pager.getTotalPage() < PAGINATION_SIZE) {
			for (int i = 1; i <= pager.getTotalPage(); i++) {
				if(pager.getPage() == null && i == 1) {
					html += "<li class='disabled'><span>" + i + "</span></li>\n";
					continue;
				}
				if(pager.getPage() != null && pager.getPage() == i) {
					html += "<li class='disabled'><span>" + i + "</span></li>\n";
				} else {
					html += "<li><a href='" + pager.generateUrl(i) + "'>" + i + "</a></li>\n";
				}
			}
		}
		
	    if(pager.isHasNext()) {
	    	html += "<li><a href='" + pager.getNextUrl() + "'>下一页</a></li>\n";
	    } else {
	    	html += "<li class='disabled'><span>下一页</span></li>\n";
	    }
	    
		html += "</ul>\n";
		return html;
	}
	
}
