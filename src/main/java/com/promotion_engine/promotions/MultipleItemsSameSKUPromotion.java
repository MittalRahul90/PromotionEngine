package com.promotion_engine.promotions;

import java.io.IOException;

import com.promotion_engine.common.PromotionProperties;
import com.promotion_engine.common.PromotionsConstant;

public class MultipleItemsSameSKUPromotion implements Promotions {
	
	PromotionProperties properties = new PromotionProperties();

	@Override
	public int applyPromotionAndGetTotal(char skuId, int quantity, int price) throws IOException {

		int noPromotionQuantity = quantity % fetchPromotionQuantity(skuId);
		int promotionPrice = 0;
		int total = 0;

		if (noPromotionQuantity == 0) {
			promotionPrice = (quantity / fetchPromotionQuantity(skuId)) * fetchPromotionPrice(skuId);
			total = promotionPrice;
		} else {
			promotionPrice = (quantity / fetchPromotionQuantity(skuId)) * fetchPromotionPrice(skuId);
			total = promotionPrice + (noPromotionQuantity * price);
		}

		return total;

	}

	private int fetchPromotionPrice(char skuId) throws IOException {

		String priceValue = properties.getPropValues(PromotionsConstant.SAME_SKU_PRICE_FILE, Character.toString(skuId));
		return Integer.parseInt(priceValue);
	}

	private int fetchPromotionQuantity(char skuId) throws IOException {

		String promotionQuantity = properties.getPropValues(PromotionsConstant.SAME_SKU_QUANTITY_FILE, Character.toString(skuId));
		return Integer.parseInt(promotionQuantity);
	}

	

}
