package com.easyshop.common.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 128, nullable = false, unique = true)
	private String name;

	@Column(length = 64, nullable = false, unique = true)
	private String alias;

	@Column(length = 128, nullable = false)
	private String image;

	private boolean enabled;

	@Column(name = "all_parent_ids", length = 256, nullable = true)
	private String allParentIDs;

	@ManyToOne
	@JoinColumn(name = "parent_id", unique = false)
	private Category parent;

	@OneToMany(mappedBy = "parent")
	@OrderBy("name asc")
	private Set<Category> children = new HashSet<>();

	public Category(String name) {
		this.name = name;
		this.alias = name;
		this.image = "default.png";
	}

	public Category(Integer id) {
		this.id = id;
	}

	public Category(String name, Category parent) {
		this(name);
		this.parent = parent;
	}

	public static Category copyIdAndName(Category category) {
		Category copyCategory = new Category();
		copyCategory.setId(category.getId());
		copyCategory.setName(category.getName());

		return copyCategory;
	}

	public static Category copyIdAndName(Integer id, String name) {
		Category copyCategory = new Category();
		copyCategory.setId(id);
		copyCategory.setName(name);

		return copyCategory;
	}

	public static Category copyFull(Category category) {
		Category copyCategory = new Category();
		copyCategory.setId(category.getId());
		copyCategory.setName(category.getName());
		copyCategory.setImage(category.getImage());
		copyCategory.setAlias(category.getAlias());
		copyCategory.setEnabled(category.isEnabled());
		copyCategory.setHasChildren(category.getChildren().size() > 0);

		return copyCategory;
	}

	public static Category copyFull(Category category, String name) {
		Category copyCategory = Category.copyFull(category);
		copyCategory.setName(name);

		return copyCategory;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	@Transient
	private boolean hasChildren;

	@Transient
	public String getImagePath() {
		if (this.id == null)
			return "/images/default-thumbnail.png";

		return "/category-images/" + this.id + "/" + this.image;
	}

	public Category(Integer id, String name, String alias) {
		super();
		this.id = id;
		this.name = name;
		this.alias = alias;
	}

	@Override
	public String toString() {
		return "Category [alias=" + alias + ", enabled=" + enabled + ", id=" + id + ", image=" + image + ", name="
				+ name + "]";
	}

}
