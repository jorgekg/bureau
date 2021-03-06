package br.com.bureau.tracking.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.util.DigestUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Debit  implements Serializable, ICertificated {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Person person;
	
	private String company;
	
	private String price;
	
	@JsonIgnore
	@Column(columnDefinition = "TEXT")
	private String certificate;

	@PrePersist
	@PreUpdate
	private void onSave() {
		this.certificate = DigestUtils.md5DigestAsHex(this.toString().getBytes());
	}

	@Override
	public String toString() {
		return "Debit [company=" + company + ", price=" + price + "]";
	}
	
}
