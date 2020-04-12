package comjava.webbanhang.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@ManyToMany(mappedBy = "roles")
	private List<Account> accounts;


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

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
