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
@Table ( name ="sys_role" )
public class SysRole  implements Serializable {

	private static final long serialVersionUID =  930216074914280905L;

	/**
	 * 角色ID
	 */
	@Id
   	@Column(name = "role_id" )
	private Long roleId;

	/**
	 * 角色名称
	 */
   	@Column(name = "role_name" )
	private String roleName;

}
