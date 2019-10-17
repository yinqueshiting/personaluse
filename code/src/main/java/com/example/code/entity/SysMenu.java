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
@Table ( name ="sys_menu" )
public class SysMenu  implements Serializable {

	private static final long serialVersionUID =  5710606465166137519L;

	/**
	 * 权限ID
	 */
	@Id
   	@Column(name = "menu_id" )
	private Long menuId;

	/**
	 * 权限名称
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 权限标识
	 */
   	@Column(name = "perms" )
	private String perms;

}
