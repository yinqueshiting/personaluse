package com.example.code.entity;

import javax.persistence.*;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
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
@Table ( name ="sys_user" )
public class SysUser  implements Serializable {

	private static final long serialVersionUID =  3521960282647607880L;

	/**
	 * 用户ID
	 */
	@Id
   	@Column(name = "user_id" )
	private int userId;

	/**
	 * 用户名
	 */
   	@Column(name = "username" )
	private String username;

	/**
	 * 密码
	 */
   	@Column(name = "password" )
	private String password;

	/**
	 * 盐值
	 */
   	@Column(name = "salt" )
	private String salt;

	/**
	 * 状态:NORMAL正常  PROHIBIT禁用
	 */
   	@Column(name = "state" )
	private String state;

}
