package com.example.demo.basket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ProductInBasket {
	private int productId;
	private String name;
	private BigDecimal price;
	private int count;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ProductInBasket [productId=" + productId + ", name=" + name + ", price=" + price + ", count=" + count + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(count, name, price, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductInBasket other = (ProductInBasket) obj;
		return count == other.count && Objects.equals(name, other.name) && Objects.equals(price, other.price)
				&& productId == other.productId;
	}

	public void increment(int n) {
		this.count += n;
	}
	
	public BigDecimal getValue() {
		return this.getPrice().multiply(BigDecimal.valueOf(this.count)).setScale(2, RoundingMode.HALF_UP);
	}
	
}
