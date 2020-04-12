package comjava.webbanhang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product extends BaseEntity{


	private static final long serialVersionUID = 1L;

	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private int price;
	
	@Column(name = "active")   
	private boolean active;
	
	@Column(name = "url_image")
	private String urlImage;
	
	@ManyToOne
	@JoinColumn(name = "id_category")
	private Category category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
