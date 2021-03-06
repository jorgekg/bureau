package br.com.bureau.details.dto;

import java.util.Date;
import java.util.List;

import br.com.bureau.details.models.enuns.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	
	private Integer id;
	private String email;
	private String personId;
	private List<Role> roles;
	private Date createAt;
	private Date updateAt;

}
