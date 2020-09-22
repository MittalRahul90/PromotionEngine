package com.promotion_engine.main;

import java.io.IOException;

import com.promotion_engine.model.SkuItem;
import com.promotion_engine.promotions.DifferentSKUFixedPricePromotion;
import com.promotion_engine.promotions.MultipleItemsSameSKUPromotion;
import com.promotion_engine.service.PromotionEngineService;

public class PromotionEngine {

	private static PromotionEngineService promotionEngineService = new PromotionEngineService();
	
	public static void main(String args[]) throws IOException {
		
		int total = 0;
		setItemsList();
		System.out.println("Applying promotion - 1!!");
		total = promotionEngineService.getTotalAfterPromotion(new MultipleItemsSameSKUPromotion());
		System.out.println("Total After applying promotion - 1 : " + total);
		
		System.out.println("Applying promotion - 2!!");
		total = promotionEngineService.getTotalAfterPromotion(new DifferentSKUFixedPricePromotion());
		System.out.println("Total After applying promotion - 2 : " + total);
	}
	
	private static void setItemsList() {
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
		item3.setQuantity(1);
		item3.setSkuId('C');
		
		SkuItem item4 = new SkuItem();
		item4.setPrice(15);
		item4.setQuantity(1);
		item4.setSkuId('D');
		
		promotionEngineService.addItem(item1);
		promotionEngineService.addItem(item2);
		promotionEngineService.addItem(item3);
		promotionEngineService.addItem(item4);
	}
}
