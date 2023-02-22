package com.kazyonplus.files.model;

import javax.persistence.*;

@Entity
@Table(name="docs")
public class Doc {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String docName;
	private String docType;
	/*private String category;
	private long categoryId;*/
	@Lob
	private byte[] data;
	
	public Doc() {}

	public Doc(String docName, String docType, byte[] data/*,String category, long categoryId*/) {
		super();
		this.docName = docName;
		this.docType = docType;
		this.data = data;
		/*this.category = category;
		this.categoryId = categoryId;*/
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	/*public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}*/
}
