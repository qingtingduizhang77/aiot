package swallow.framework.web;

import java.util.List;

import org.springframework.util.Assert;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页列表数据
 * @author aohanhe
 *
 */
@ApiModel(value="分页列表结果对象",description="分布查询时返回的查询结果对象")
public class PageListData <T>{
	//数据列表
	@ApiModelProperty("数据结果列表")
	private List<T> items;
	//数据总量
	@ApiModelProperty("符合查询条件的数据总量")
	private long total;
	//每页的大小
	@ApiModelProperty("符合查询条件的数据总页数")
	private int pageSize;
	
	public PageListData(int pageSize,long total,List<T> items) {
		Assert.notNull(items,"参数items不允许为空");
		Assert.isTrue(pageSize>0,"参数pageSize必需大于0");
		
		this.items=items;
		this.total=total;
		this.pageSize=pageSize;
	}
	
	/**
	 * 取得页面的数量
	 * @return
	 */
	public int getPageCount() {
		return (int) Math.ceil((double)total/(double)pageSize);
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	

}
