package com.yuanlong.moiverss.utils;
import java.io.Serializable;

@SuppressWarnings("serial")
public class PageInfo
  implements Serializable
{
  public static final String CURRENT_PAGE_NAME = "_pn";
  public static final int FIRST_PAGE_NO = 1;
  public static final int DEFAULT_PAGE_SIZE = 20;
  public static final int MAX_PAGE_SIZE=100;
  private int pageSize = 20;
  private int currentPageNo;
  private Integer totalCount = null;
private boolean hasNext=false;
private int skip=0;
public int hashCode()
  {
    int result = 1;
    result = 31 * result + this.currentPageNo;
    result = 31 * result + this.pageSize;
    result = 31 * result + (this.totalCount == null ? 0 : this.totalCount.intValue());
    return result;
  }

  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PageInfo other = (PageInfo)obj;
    if (this.currentPageNo != other.currentPageNo)
      return false;
    if (this.pageSize != other.pageSize) {
      return false;
    }
    return this.totalCount == other.totalCount;
  }

  public PageInfo()
  {
    this(1, 20);
  }

  public PageInfo(int start, int pageSize)
  {
    this.currentPageNo = start;
    this.pageSize = pageSize;
  }

  public String makePage(String url, int CASE)
  {
    StringBuffer str = new StringBuffer("");
    int tostart = this.currentPageNo - CASE;
    int toend = this.currentPageNo + CASE;
    if (this.currentPageNo - CASE < 1) {
      toend = this.currentPageNo + CASE - (this.currentPageNo - CASE - 1);
    }
    if (this.currentPageNo + CASE > getTotalPage()) {
      tostart = this.currentPageNo - CASE - (this.currentPageNo + CASE - getTotalPage());
    }
    if (toend > getTotalPage()) toend = getTotalPage();
    if (tostart < 1) tostart = 1;

    for (int i = 1; i <= getTotalPage(); i++) {
      if ((i >= tostart) && (i <= toend)) {
        if (i != this.currentPageNo) {
          str.append("<a href=\"");
          str.append(url);
          str.append(i);
          str.append("\">");
          str.append(i);
          str.append("</a>");
          str.append("&nbsp;&nbsp;");
        } else {
          str.append(i);
          str.append("&nbsp;&nbsp;");
        }
      }
    }
    return str.toString();
  }

  public Integer getTotalCount()
  {
    return this.totalCount;
  }

  public int getTotalPage()
  {
    return this.pageSize > 0 ? (this.totalCount.intValue() + this.pageSize - 1) / this.pageSize : 0;
  }

  public int getPageSize()
  {
    return this.pageSize;
  }

  public boolean isFirstPage() {
    return getCurrentPageNo() == 1;
  }

  public boolean hasNextPage()
  {
    return getCurrentPageNo() < getTotalPage() - 1;
  }

  public boolean hasPreviousPage()
  {
    return getCurrentPageNo() > 1;
  }

  public boolean isLastPage() {
    return getCurrentPageNo() == getTotalPage();
  }

  public int getCurrentPageStart()
  {
    return getPageSize() * (getCurrentPageNo() - 1)+skip;
  }

  public int getSkip() {
	return skip;
}

public void setSkip(int skip) {
	this.skip = skip;
}

public void setPageSize(int pageSize)
  {
    this.pageSize = pageSize>MAX_PAGE_SIZE?MAX_PAGE_SIZE:pageSize;
  }

  public void setTotalCount(int totalCount)
  {
    this.totalCount = Integer.valueOf(totalCount);
  }

  public int getCurrentPageNo() {
    return this.currentPageNo;
  }

  public void setCurrentPageNo(int currentPageNO) {
    this.currentPageNo = currentPageNO;
  }

public boolean isHasNext() {
	return hasNext;
}

public void setHasNext(boolean hasNext) {
	this.hasNext = hasNext;
}
  
  
}