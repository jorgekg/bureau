package br.com.bureau.earnings.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
	
	private Integer id;
	
	@NotNull(message = "Birth is required")
	private Date birth;

}
