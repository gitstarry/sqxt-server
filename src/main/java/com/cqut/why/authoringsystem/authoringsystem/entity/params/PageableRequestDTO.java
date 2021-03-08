package com.cqut.why.authoringsystem.authoringsystem.entity.params;

import com.cqut.why.authoringsystem.authoringsystem.config.util.GlobalExceptionHandler.BusinessException;
import com.cqut.why.authoringsystem.authoringsystem.config.util.page.Pageable;

public class PageableRequestDTO implements Pageable {
    private Integer page = 1;
    private Integer pageSize = 20;

    /**
     * 获取 当前页
     *
     * @return page 当前页
     */
    public Integer getPage() {
        return page;
    }

    /**
     * 设置 当前页
     *
     * @param page 当前页
     */
    public void setPage(Integer page) {
        if (page < 1) {
            throw new BusinessException("页码错误");
        } else {
            this.page = page;
        }
    }

    /**
     * 获取 分页大小
     *
     * @return pageSize 分页大小
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置 分页大小
     *
     * @param pageSize 分页大小
     */
    public void setPageSize(Integer pageSize) {
        if (page < 1) {
            throw new BusinessException("页面大小错误");
        } else {
            this.pageSize = pageSize;
        }
    }
}
