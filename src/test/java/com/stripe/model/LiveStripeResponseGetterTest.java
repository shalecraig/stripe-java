package com.stripe.net;

import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.net.LiveStripeResponseGetter;
import com.stripe.net.RequestOptions;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LiveStripeResponseGetterTest {
	LiveStripeResponseGetter srg = new LiveStripeResponseGetter();

	@Test
	public void testCreateQuery() throws StripeException, UnsupportedEncodingException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("a", "b");
		assertEquals("a=b", srg.createQuery(params));
	}

	@Test
	public void testCreateQueryWithNestedParams() throws StripeException, UnsupportedEncodingException {
		/* Use TreeMap because it preserves iteration order */
		SortedMap<String, Object> params = new TreeMap<String, Object>();
		SortedMap<String, Object> nested = new TreeMap<String, Object>();
		nested.put("A", "B");
		nested.put("C", "D");
		params.put("nested", nested);
		params.put("c", "d");
		params.put("e", "f");
		assertEquals("e=f&c=d&nested%5BC%5D=D&nested%5BA%5D=B", srg.createQuery(params));
	}
}

