package swallow.framework.jpaentity;

import java.io.Serializable;

/**
 * 拥有ID的实体的接口
 * @author aohanhe
 *
 */
public interface IOnlyIdEntity {
	public abstract	Serializable getId();
}
