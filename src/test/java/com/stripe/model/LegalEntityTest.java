package com.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.LegalEntity;
import com.stripe.net.APIResource;
import com.stripe.net.RequestOptions;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LegalEntityTest extends BaseStripeTest {
	static String legalEntityHash =
		"{" +
		"	\"type\": \"sole_prop\"," +
		"	\"business_name\": \"business name\"," +
		"	\"address\": {" +
		"		\"line1\": \"12 Grove Street\"," +
		"		\"line2\": null," +
		"		\"city\": \"New York\"," +
		"		\"state\": \"NY\"," +
		"		\"postal_code\": \"10014\"," +
		"		\"country\": \"US\"" +
		"	}," +
		"	\"first_name\": \"Monica\"," +
		"	\"last_name\": \"Geller\"," +
		"	\"dob\": {" +
		"		\"day\": 4," +
		"		\"month\": 4," +
		"		\"year\": 1969" +
		"	}," +
		"	\"additional_owners\": [" +
		"		]," +
		"	\"verification\": {" +
		"		\"status\": \"verified\"," +
		"		\"document\": null," +
		"		\"details\": null" +
		"	}" +
		"}";

	@Test
	public void testDeserialize() throws StripeException {
		LegalEntity le = APIResource.GSON.fromJson(legalEntityHash, LegalEntity.class);

		assertEquals("sole_prop", le.getType());
		assertEquals("business name", le.getBusinessName());
		assertEquals("12 Grove Street", le.getAddress().getLine1());
		assertEquals(null, le.getAddress().getLine2());
		assertEquals("New York", le.getAddress().getCity());
		assertEquals("NY", le.getAddress().getState());
		assertEquals("10014", le.getAddress().getPostalCode());
		assertEquals("US", le.getAddress().getCountry());
		assertEquals("Monica", le.getFirstName());
		assertEquals("Geller", le.getLastName());
		assertEquals(new Integer(4), le.getDob().getDay());
		assertEquals(new Integer(4), le.getDob().getMonth());
		assertEquals(new Integer(1969), le.getDob().getYear());
		assertEquals(new LinkedList<Object>(), le.getAdditionalOwners());
		assertEquals("verified", le.getVerification().getStatus());
		assertEquals(null, le.getVerification().getDocument());
		assertEquals(null, le.getVerification().getDetails());
	}
}
