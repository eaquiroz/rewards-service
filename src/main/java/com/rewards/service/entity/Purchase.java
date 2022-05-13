package com.rewards.service.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PURCHASE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Purchase implements Serializable {

	private static final long serialVersionUID = 3867733436860854825L;

	@Id
	@Column(name = "ID", nullable = false)
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	private Customer customer;
	
	@Column(name = "AMOUNT", nullable = false)
	private Integer amount;
}
