package com.cityiot.guanxin.mongo;

/**
 * 排序器
 * @author hxl
 * 按指定字段排序
 */
public class Sorter {

	private String field;

	private Direction direction;
	
	public Sorter(String field) {
		this.field = field;
		this.direction = Direction.DESC;

	}

	public Sorter(String field, Direction direction) {
		this.field = field;
		this.direction = direction;

	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public enum Direction {
		/**
		 * 升序
		 */
		ASC, 
		/**
		 * 降序
		 */
		DESC

	}

}
