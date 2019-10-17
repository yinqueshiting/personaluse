package com.example.code.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2019-10-17 
 */

@Setter
@Getter
@ToString
@Entity
@Table ( name ="sys_user_role" )
public class SysUserRole  implements Serializable {

	private static final long serialVersionUID =  1829824603526763873L;

	/**
	 * ID
	 */
	@Id
   	@Column(name = "id" )
	private Long id;

	/**
	 * 用户ID
	 */
   	@Column(name = "user_id" )
	private Long userId;

	/**
	 * 角色ID
	 */
   	@Column(name = "role_id" )
	private Long roleId;

}
