package com.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AccountTest extends BaseStripeTest {
	static String accountResponse =
		"{" +
		"\"id\": \"acct_1032D82eZvKYlo2C\"," +
		"\"email\": \"site@stripe.com\"," +
		"\"statement_descriptor\": null," +
		"\"display_name\": \"Stripe.com\"," +
		"\"timezone\": \"US/Pacific\"," +
		"\"details_submitted\": false," +
		"\"charges_enabled\": false," +
		"\"transfers_enabled\": false," +
		"\"currencies_supported\": [" +
		"	\"usd\"," +
		"\"aud\"" +
		"	]," +
		"\"default_currency\": \"usd\"," +
		"\"country\": \"US\"," +
		"\"object\": \"account\"," +
		"\"business_name\": \"Stripe.com\"," +
		"\"business_url\": null," +
		"\"support_phone\": null," +
		"\"managed\": null" +
		"}";

	@Test
	public void testRetrieve() throws StripeException {
		stubNetwork(Account.class, accountResponse);
		Account acc = Account.retrieve();

		verifyGet("https://api.stripe.com/v1/account", params, Account.class);
		verifyNoMoreInteractions(networkMock);

		assertEquals("acct_1032D82eZvKYlo2C", acc.getId());
		assertEquals(false, acc.getChargesEnabled());
		assertEquals(false, acc.getDetailsSubmitted());
		assertEquals(false, acc.getTransfersEnabled());

		List<String> currenciesSupported = new LinkedList<String>();
		currenciesSupported.add("usd");
		currenciesSupported.add("aud");
		assertEquals(currenciesSupported, acc.getCurrenciesSupported());

		assertEquals("site@stripe.com", acc.getEmail());
		assertEquals(null, acc.getStatementDescriptor());
		assertEquals("usd", acc.getDefaultCurrency());
		assertEquals("US", acc.getCountry());
		assertEquals("US/Pacific", acc.getTimezone());
		assertEquals("Stripe.com", acc.getDisplayName());
	}
}
