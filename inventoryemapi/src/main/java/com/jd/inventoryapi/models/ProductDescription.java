package com.jd.inventoryapi.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDescription {
	@Column(name="Name",nullable = false,length = 100)
	private String name;
	@Enumerated(EnumType.STRING)
	@Column(name="Consumption_Type")
	private ConsumptionType consumptionType;
	
}
