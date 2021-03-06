package br.com.bureau.earnings.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.util.DigestUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.bureau.earnings.models.enuns.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Assets implements Serializable, ICertificated {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer personId;
	
	private String asstes;
	
	private Double price;
	
	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)
	private List<PaymentMethod> paymentMethod;
	
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
		return "Assets [asstes=" + asstes + ", price=" + price + "]";
	}
}
