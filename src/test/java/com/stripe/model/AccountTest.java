package com.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.net.APIResource;
import com.stripe.net.RequestOptions;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AccountTest extends BaseStripeTest {
	static String accountHash =
		"{" +
		"  \"id\": \"acct_1032D82eZvKYlo2C\"," +
		"  \"email\": \"site@stripe.com\"," +
		"  \"statement_descriptor\": null," +
		"  \"display_name\": \"Stripe.com\"," +
		"  \"timezone\": \"US/Pacific\"," +
		"  \"details_submitted\": false," +
		"  \"charges_enabled\": false," +
		"  \"transfers_enabled\": false," +
		"  \"currencies_supported\": [" +
		"    \"usd\"," +
		"    \"aud\"" +
		"  ]," +
		"  \"legal_entity\": {" +
		"    \"type\": \"company\"," +
		"    \"address\": {" +
		"      \"city\": \"San Francisco\"" +
		"    }" +
		"  }," +
		"  \"default_currency\": \"usd\"," +
		"  \"country\": \"US\"," +
		"  \"object\": \"account\"," +
		"  \"business_name\": \"Stripe.com\"," +
		"  \"business_url\": null," +
		"  \"support_phone\": null," +
		"  \"managed\": null" +
		"}";

	@Test
	public void testDeserialize() throws StripeException {
		Account acc = APIResource.GSON.fromJson(accountHash, Account.class);

		assertEquals("acct_1032D82eZvKYlo2C", acc.getId());
		assertEquals(false, acc.getChargesEnabled());
		assertEquals(false, acc.getDetailsSubmitted());
		assertEquals(false, acc.getTransfersEnabled());

		List<String> currenciesSupported = new LinkedList<String>();
		currenciesSupported.add("usd");
		currenciesSupported.add("aud");
		assertEquals(currenciesSupported, acc.getCurrenciesSupported());

		assertEquals("company", acc.getLegalEntity().getType());
		assertEquals("San Francisco", acc.getLegalEntity().getAddress().getCity());

		assertEquals("site@stripe.com", acc.getEmail());
		assertEquals(null, acc.getStatementDescriptor());
		assertEquals("usd", acc.getDefaultCurrency());
		assertEquals("US", acc.getCountry());
		assertEquals("US/Pacific", acc.getTimezone());
		assertEquals("Stripe.com", acc.getDisplayName());
	}

	@Test
	public void testRetrieve() throws StripeException {
		Account.retrieve();

		verifyGet(Account.class, "https://api.stripe.com/v1/account");
		verifyNoMoreInteractions(networkMock);
	}

	@Test
	public void testOverloadedSingleArgumentRetrieve() throws StripeException {
		Account.retrieve("sk_foobar");

		RequestOptions options = requestOptionsBuilder.setApiKey("sk_foobar").build();
		verifyGet(Account.class, "https://api.stripe.com/v1/account", options);

		Account.retrieve("anything_else");

		verifyGet(Account.class, "https://api.stripe.com/v1/accounts/anything_else");
		verifyNoMoreInteractions(networkMock);
	}

	@Test
	public void testRetrieveAccountWithId() throws StripeException {
		Account.retrieve("acct_something", null);

		verifyGet(Account.class, "https://api.stripe.com/v1/accounts/acct_something");
		verifyNoMoreInteractions(networkMock);
	}

	@Test
	public void testAccountUpdateById() throws StripeException {
		stubNetwork(Account.class, accountHash);
		Account acc = Account.retrieve("acct_1032D82eZvKYlo2C");
		verifyGet(Account.class, "https://api.stripe.com/v1/accounts/acct_1032D82eZvKYlo2C");

		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> legalEntity = new HashMap<String, Object>();
		legalEntity.put("type", "individual");
		params.put("legal_entity", legalEntity);
		acc.update(params);

		verifyPost(Account.class, "https://api.stripe.com/v1/accounts/acct_1032D82eZvKYlo2C", params);
		verifyNoMoreInteractions(networkMock);
	}
}
