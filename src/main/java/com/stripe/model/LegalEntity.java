package com.stripe.model;

import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.net.APIResource;
import com.stripe.net.RequestOptions;

import java.util.List;
import java.util.Map;

public class LegalEntity extends APIResource {
	String type;
	Address address;
	String businessName;
	String businessTaxId;
	String businessVatId;
	DateOfBirth dob;
	String firstName;
	String lastName;
	Address personalAddress;
	String personalIdNumber;
	String ssnLast4;
	Verification verification;
	List<Owner> additionalOwners;

	public String getType() {
		return type;
	}
	public Address getAddress() {
		return address;
	}
	public String getBusinessName() {
		return businessName;
	}
	public String getBusinessTaxId() {
		return businessTaxId;
	}
	public String getBusinessVatId() {
		return businessVatId;
	}
	public DateOfBirth getDob() {
		return dob;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public Address getPersonalAddress() {
		return personalAddress;
	}
	public String getPersonalIdNumber() {
		return personalIdNumber;
	}
	public String getSsnLast4() {
		return ssnLast4;
	}
	public Verification getVerification() {
		return verification;
	}
	public List<Owner> getAdditionalOwners() {
		return additionalOwners;
	}

	public static class Address extends APIResource {
		String line1;
		String line2;
		String city;
		String state;
		String postalCode;
		String country;

		public String getLine1() {
			return line1;
		}
		public String getLine2() {
			return line2;
		}
		public String getCity() {
			return city;
		}
		public String getState() {
			return state;
		}
		public String getPostalCode() {
			return postalCode;
		}
		public String getCountry() {
			return country;
		}
	}

	public static class DateOfBirth extends APIResource {
		Integer day;
		Integer month;
		Integer year;

		public Integer getDay() {
			return day;
		}
		public Integer getMonth() {
			return month;
		}
		public Integer getYear() {
			return year;
		}
	}

	public static class Verification extends APIResource {
		String status;
		List<String> fieldsNeeded;
		Long dueBy;
		Boolean contacted;
		String document;
		String details;

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
		public String getDetails() {
			return details;
		}
	}

	public static class Owner extends APIResource {
		Address address;
		DateOfBirth dob;
		String firstName;
		String lastName;
		Verification verification;

		public Address getAddress() {
			return address;
		}
		public DateOfBirth getDob() {
			return dob;
		}
		public String getFirstName() {
			return firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public Verification getVerification() {
			return verification;
		}
	}
}
