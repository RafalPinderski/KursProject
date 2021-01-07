package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;


/**
 * The persistent class for the products database table.
 * 
 */

// Dopisuję tutaj adnotacje "bean validation" - część Java EE, obsługiwana przez Springa.
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUCTS_PRODUCTID_GENERATOR", sequenceName="PRODUCTS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTS_PRODUCTID_GENERATOR")
	@Column(name="product_id")
	private Integer productId;

	private String description;

	@NotNull
	@Length(min=1, max=50)
	private String name;

	@Min(100)
	@NotNull
	private BigDecimal price;

	//bi-directional many-to-one association to ProductCategory
	@ManyToOne
	@JoinColumn(name="category")
	private ProductCategory category;

	public Product() {
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ProductCategory getCategory() {
		return this.category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", description=" + description + ", name=" + name + ", price=" + price + "]";
	}

}
