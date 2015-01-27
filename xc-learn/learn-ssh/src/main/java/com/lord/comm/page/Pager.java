package com.lord.comm.page;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Pager {
	public static final String PARAM_PAGE = "pager.page";
	private Long totalRows;			//总的记录数
	private Integer pageSize = 20;	//每页的大小
	private Integer page;			//当前页数
	private Integer totalPage;		//总页数
	private Integer startRow = 0;	//分页记录开始的行数
	private String pageHtml;		//分页显示样式
	private HttpServletRequest request; //当前的请求
	private PageStyle pageStyle;	//分布样式
	private String url;				//需要进行分页的URL
	private Map<String, Object> originalParams;	//需要进行分页的URL上的参数
	private boolean needPaging;		//是否需要分页
	private boolean hasPrev;		//是否有上一页
	private boolean hasNext;		//是否有下一页
	private String prevUrl;			//上一页的链接
	private String nextUrl;			//下一页的链接
	
	public Pager() {
	}

	public Pager(Long totalRows, Integer page) {
		super();
		this.totalRows = totalRows;
		this.page = page;
	}
	
	public void init() {
		//计算总页数和记录开始的行数
		if(totalRows != null && totalRows > 0) {
			Long varTotalPageSize = totalRows / pageSize.longValue();
			if (totalRows % pageSize.longValue() > 0)
				varTotalPageSize++;
			totalPage = varTotalPageSize.intValue();
			
			if(page != null) {
				if(page <= 0) {
					startRow = 0;
				} else if(page > totalPage) {
					startRow = totalPage * pageSize;
				} else {
					startRow = (page - 1) * pageSize;
				}
			} else {
				startRow = 0;
			}
		}
		
		if(totalPage == null) {
			needPaging = false;
		} else if(totalPage < 2) {
			needPaging = false;
		} else {
			needPaging = true;
		}
		
		
		//计算需要进行分页的链接全路径
		url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();
		Map<String, Object> map = request.getParameterMap();
		originalParams = new HashMap<String, Object>();
		
		if(map != null && map.size() > 0) {
			for (String key : map.keySet()) {
				if(PARAM_PAGE.equals(key))
					continue;
				originalParams.put(key, map.get(key));
			}
			if(originalParams.size() > 0) {
				url += "?";
				for (String key : originalParams.keySet()) {
					url += key + "=" + request.getAttribute(key) + "&";
				}
				if(url.endsWith("&")) {
					url = url.substring(0, url.length() - 1);
				}
			}
		}
		
		//判断是否有上一页和下一页
		if(totalPage == null || totalPage < 2) {
			//只有一页的情况
			hasPrev = false;
			hasNext = false;
			prevUrl = generateUrl(null);
			nextUrl = generateUrl(null);
		} else if(page != null) {
			if(page >= totalPage) {
				//最后一页的情况
				page = totalPage;
				hasPrev = true;
				hasNext = false;
				prevUrl = generateUrl(totalPage - 1);
				nextUrl = generateUrl(null);
			} else if(page <= 1) {
				//第一页的情况
				page = 1;
				hasPrev = false;
				hasNext = true;
				prevUrl = generateUrl(null);
				nextUrl = generateUrl(2);
			} else {
				//中间页的情况
				hasPrev = true;
				hasNext = true;
				prevUrl = generateUrl(page - 1);
				nextUrl = generateUrl(page + 1);
			}
		} else {
			//没有页码的情况，与第一页的情况相同
			hasPrev = false;
			hasNext = true;
			prevUrl = generateUrl(null);
			nextUrl = generateUrl(2);
		}
		
		pageHtml = pageStyle.generateHtml(this);
	}
	
	/**
	 * 按照页数,生成分页之后的完整链接
	 * @param varPage
	 * @return
	 */
	public String generateUrl(Integer varPage) {
		if(varPage == null)
			return getUrl();
		if(getOriginalParams().size() > 0) {
			return getUrl() + "&" + Pager.PARAM_PAGE + "=" + varPage;
		} else {
			return getUrl() + "?" + Pager.PARAM_PAGE + "=" + varPage;
		}
	}

	public Long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getPageHtml() {
		return pageHtml;
	}

	public void setPageHtml(String pageHtml) {
		this.pageHtml = pageHtml;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public PageStyle getPageStyle() {
		return pageStyle;
	}

	public void setPageStyle(PageStyle pageStyle) {
		this.pageStyle = pageStyle;
	}

	public Map<String, Object> getOriginalParams() {
		return originalParams;
	}

	public String getUrl() {
		return url;
	}

	public boolean isHasPrev() {
		return hasPrev;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public String getPrevUrl() {
		return prevUrl;
	}

	public String getNextUrl() {
		return nextUrl;
	}

	public boolean isNeedPaging() {
		return needPaging;
	}
}
