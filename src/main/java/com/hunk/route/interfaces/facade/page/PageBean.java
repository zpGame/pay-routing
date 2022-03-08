package com.hunk.route.interfaces.facade.page;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author hunk
 * @date 2022/3/8
 *     <p>
 */
@Getter
@Setter
public class PageBean {
    /** 页码 */
    private int page = 1;
    /** 页大小 */
    private int rows = 3;
    /** 总记录数 */
    private int total = 0;
    /** 是否分页 */
    private boolean pagination = true;

    /** 保存上次查询的参数 */
    private Map<String, String[]> paramMap;
    /** 保存上次查询的url */
    private String url;

    public void setRequest(HttpServletRequest request) {
        String page = request.getParameter("page");
        String rows = request.getParameter("offset");
        String pagination = request.getParameter("pagination");
        this.setPage(page);
        this.setRows(rows);
        this.setPagination(pagination);
        this.setUrl(request.getRequestURL().toString());
        this.setParamMap(request.getParameterMap());
    }

    public PageBean() {
        super();
    }

    public void setPage(String page) {
        if (StringUtils.isNotBlank(page)) {
            this.page = Integer.parseInt(page);
        }
    }

    public void setRows(String rows) {
        if (StringUtils.isNotBlank(rows)) {
            this.rows = Integer.parseInt(rows);
        }
    }

    public void setTotal(String total) {
        if (StringUtils.isNotBlank(total)) {
            this.total = Integer.parseInt(total);
        }
    }

    public void setPagination(String pagination) {
        if (StringUtils.isNotBlank(pagination) && "false".equals(pagination)) {
            this.pagination = false;
        }
    }

    public int getMaxPage() {
        int max = this.total / this.rows;
        if (this.total % this.rows != 0) {
            max++;
        }
        return max;
    }

    public int getNextPage() {
        int nextPage = this.page + 1;
        if (nextPage > this.getMaxPage()) {
            nextPage = this.getMaxPage();
        }
        return nextPage;
    }

    public int getPreviousPage() {
        int previousPage = this.page - 1;
        if (previousPage < 1) {
            previousPage = 1;
        }
        return previousPage;
    }

    public int getStartIndex() {
        return (this.page - 1) * this.rows;
    }

    @Override
    public String toString() {
        return "PageBean [page="
                + page
                + ", rows="
                + rows
                + ", total="
                + total
                + ", pagination="
                + pagination
                + "]";
    }
}
