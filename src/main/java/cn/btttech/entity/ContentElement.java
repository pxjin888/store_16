package cn.btttech.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_content_element", catalog = "store_2")
public class ContentElement implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer contentelementId;
	private Privilege privilege;
	private String contentElementName;

	public ContentElement() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "contentelement_id", unique = true, nullable = false)
	public Integer getContentelementId() {
		return this.contentelementId;
	}

	public void setContentelementId(Integer contentelementId) {
		this.contentelementId = contentelementId;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "contentElement")
	public Privilege getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	@Column(name = "content_element_name", length = 1024)
	public String getContentElementName() {
		return this.contentElementName;
	}

	public void setContentElementName(String contentElementName) {
		this.contentElementName = contentElementName;
	}

}
