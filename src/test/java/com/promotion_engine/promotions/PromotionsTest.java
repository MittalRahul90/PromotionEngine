package com.promotion_engine.promotions;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class PromotionsTest {

	@Test
	public void testMultipleItemsSameSKUPromotion() throws IOException {
		Promotions promotion = new MultipleItemsSameSKUPromotion();
		int quantity = 5;
		char skuId = 'A';
		int price = 50;
		int total = promotion.applyPromotionAndGetTotal(skuId, quantity, price);
		assertEquals(230, total);
	}
}
