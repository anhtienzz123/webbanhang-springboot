package comjava.webbanhang.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_tableorder")
public class ProductOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_product")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "id_order")
	private TableOrder tableOrder;

	@Column(name = "number")
	private int number;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public TableOrder getTableOrder() {
		return tableOrder;
	}

	public void setTableOrder(TableOrder tableOrder) {
		this.tableOrder = tableOrder;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
