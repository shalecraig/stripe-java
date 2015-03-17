package com.stripe.model;

import com.stripe.net.APIResource;
import java.util.List;

public class Verification extends APIResource {
	String status;
	List<String> fieldsNeeded;
	Long dueBy;
	Boolean contacted;
	String document;

	public String getStatus() {
		return status;
	}

	public List<String> getFieldsNeeded() {
		return fieldsNeeded;
	}

	public Long getDueBy() {
		return dueBy;
	}

	public Boolean getContacted() {
		return contacted;
	}

	public String getDocument() {
		return document;
	}
}

