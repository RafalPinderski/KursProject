package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the order_products database table.
 * 
 */
@Embeddable
public class OrderProductPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="order_id", insertable=false, updatable=false)
	private Integer orderId;

	@Column(name="product_code", insertable=false, updatable=false)
	private String productCode;

	public OrderProductPK() {
	}
	public Integer getOrderId() {
		return this.orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getProductCode() {
		return this.productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrderProductPK)) {
			return false;
		}
		OrderProductPK castOther = (OrderProductPK)other;
		return 
			this.orderId.equals(castOther.orderId)
			&& this.productCode.equals(castOther.productCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orderId.hashCode();
		hash = hash * prime + this.productCode.hashCode();
		
		return hash;
	}
}