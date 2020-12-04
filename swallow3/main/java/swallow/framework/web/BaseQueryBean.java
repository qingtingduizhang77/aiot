package swallow.framework.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 查询bean对象的基础类
 * @author aohanhe
 *
 */
@ApiModel(value="查询对象",description="向服务器传递查询要求")
public class BaseQueryBean {
	
	@ApiModelProperty("查询条件列表")
	private String[] sorts;

	@JsonIgnore
	private List<Order> orders;
	
	/**
	 * 对排序条件进行初始化
	 */	
	public void init() {		
		
		//从 sort中提取排序列表
		if(sorts!=null) {
			orders=Stream.of(sorts)
				.filter(v->!StringUtils.isEmpty(v))
				.filter(v->!((v.length()==1)&&(v=="+"||v=="-")))
				.map(v->{
					if(v.startsWith("+"))
						return Order.asc(v.substring(1));
					if(v.startsWith("-"))
						return Order.desc(v.substring(1));
					return Order.asc(v);
				}).collect(Collectors.toList());
		}else {
			orders=new ArrayList<>();
		}
	}

	

	public List<Order> getOrders() {
		// 如果order对象没有初始化，则初始化
		if(sorts!=null&&orders==null)
			this.init();
		return orders;
	}



	public String[] getSorts() {
		return sorts;
	}



	public void setSorts(String[] sorts) {
		this.sorts = sorts;
	}

	

}
