package com.promotion_engine.promotions;

import java.io.IOException;
import java.util.List;

import com.promotion_engine.common.PromotionProperties;
import com.promotion_engine.common.PromotionsConstant;
import com.promotion_engine.model.SkuItem;

public class MultipleItemsSameSKUPromotion implements Promotions {

	PromotionProperties properties = new PromotionProperties();

	@Override
	public int applyPromotionAndGetTotal(List<SkuItem> itemsList) throws IOException {

		int total = 0;

		for (SkuItem item : itemsList) {
			int noPromotionQuantity = item.getQuantity() % fetchPromotionQuantity(item.getSkuId());
			int promotionPrice = 0;

			if (noPromotionQuantity == 0) {
				promotionPrice = (item.getQuantity() / fetchPromotionQuantity(item.getSkuId()))
						* fetchPromotionPrice(item.getSkuId());
				
				total += (promotionPrice != 0 ? promotionPrice : item.getQuantity() * item.getPrice());
			} else {
				promotionPrice = (item.getQuantity() / fetchPromotionQuantity(item.getSkuId()))
						* fetchPromotionPrice(item.getSkuId());
				total += promotionPrice + (noPromotionQuantity * item.getPrice());
			}

		}
		return total;
	}

	private int fetchPromotionPrice(char skuId) throws IOException {

		String priceValue = properties.getPropValues(PromotionsConstant.SAME_SKU_PRICE_FILE, Character.toString(skuId));
		if(priceValue != null)
			return Integer.parseInt(priceValue);
		else
			return 0;
	}

	private int fetchPromotionQuantity(char skuId) throws IOException {

		String promotionQuantity = properties.getPropValues(PromotionsConstant.SAME_SKU_QUANTITY_FILE,
				Character.toString(skuId));
		if(promotionQuantity != null) 
			return Integer.parseInt(promotionQuantity);
		else
			return 1;
	}

}
