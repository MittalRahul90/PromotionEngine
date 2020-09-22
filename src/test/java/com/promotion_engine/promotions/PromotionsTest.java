package com.promotion_engine.promotions;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.promotion_engine.model.SkuItem;

public class PromotionsTest {

	Promotions sameSKUPromotion;
	List<SkuItem> cartItems;

	@Before
	public void setup() {
		sameSKUPromotion = new MultipleItemsSameSKUPromotion();
	}

	@Test
	public void testMultipleItemsSameSKUPromotion() throws IOException {
		cartItems = new ArrayList<SkuItem>();
		SkuItem item = new SkuItem();
		item.setPrice(50);
		item.setQuantity(5);
		item.setSkuId('A');
		cartItems.add(item);
		int total = sameSKUPromotion.applyPromotionAndGetTotal(cartItems);
		assertEquals(230, total);
	}

	@Test
	public void testMultipleIdsSameSKUPromotion() throws IOException {
		cartItems = new ArrayList<SkuItem>();

		SkuItem item1 = new SkuItem();
		item1.setPrice(50);
		item1.setQuantity(5);
		item1.setSkuId('A');

		SkuItem item2 = new SkuItem();
		item2.setPrice(30);
		item2.setQuantity(5);
		item2.setSkuId('B');

		cartItems.add(item1);
		cartItems.add(item2);

		int total = sameSKUPromotion.applyPromotionAndGetTotal(cartItems);
		assertEquals(350, total);
	}
}
