package swallow.framework.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 带有分页信息的基础查询bean
 * @author aohanhe
 *
 */
@ApiModel(value="基础分页查询对象",description="带有分页信息的查询对象")
public class BasePageQueryBean extends BaseQueryBean{
	@ApiModelProperty(name="页码号",value="页码号，从1开始")
	private int page=1;
	@ApiModelProperty(name="每页记录数",value="返回记录数")
	private int pageSize=10;
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
