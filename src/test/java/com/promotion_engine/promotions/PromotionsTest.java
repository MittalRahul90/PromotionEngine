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
	Promotions diffSKUPromotion;
	List<SkuItem> cartItems;

	@Before
	public void setup() {
		sameSKUPromotion = new MultipleItemsSameSKUPromotion();
		diffSKUPromotion = new DifferentSKUFixedPricePromotion();
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
		
		SkuItem item3 = new SkuItem();
		item3.setPrice(20);
		item3.setQuantity(2);
		item3.setSkuId('C');

		cartItems.add(item1);
		cartItems.add(item2);
		cartItems.add(item3);

		int total = sameSKUPromotion.applyPromotionAndGetTotal(cartItems);
		assertEquals(390, total);
	}
	
	@Test
	public void testDifferentSKUFixedPricePromotion() throws IOException {
		cartItems = new ArrayList<SkuItem>();

		SkuItem item1 = new SkuItem();
		item1.setPrice(20);
		item1.setQuantity(2);
		item1.setSkuId('C');

		SkuItem item2 = new SkuItem();
		item2.setPrice(15);
		item2.setQuantity(1);
		item2.setSkuId('D');
		
		cartItems.add(item1);
		cartItems.add(item2);

		int total = diffSKUPromotion.applyPromotionAndGetTotal(cartItems);
		assertEquals(50, total);
	}
}
