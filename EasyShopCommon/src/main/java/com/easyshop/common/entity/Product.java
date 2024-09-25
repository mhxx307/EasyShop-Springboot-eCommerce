package com.easyshop.common.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true, length = 255, nullable = false)
	private String name;
	
	@Column(unique = true, length = 255, nullable = false)
	private String alias;
	
	@Column(length = 512, nullable = false, name = "short_description")
	private String shortDescription;
	
	@Column(length = 4096, nullable = false, name = "full_description")
	private String fullDescription;
	
	@Column(name = "created_time", nullable = false, updatable = false)
	private Date createdTime;
	
	@Column(name = "updated_time")
	private Date updatedTime;
	
	private boolean enabled;
	
	@Column(name = "in_stock")
	private boolean inStock;
	
	private float cost;
	
	private float price;
	
	@Column(name = "discount_percent")
	private float discountPercent;
	
	private float length;
	private float width;
	private float height;
	private float weight;
	
	@Column(name = "main_image", nullable = false)
	private String mainImage;
		
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "brand_id")	
	private Brand brand;
	
//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<ProductImage> images = new HashSet<>();
//	
//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<ProductDetail> details = new ArrayList<>();

	private int reviewCount;
	private float averageRating;
	
	@Transient private boolean customerCanReview;
	@Transient private boolean reviewedByCustomer;
	
	public Product(Integer id) {
		this.id = id;
	}
	
	public Product(String name) {
		this.name = name;
	}
}
