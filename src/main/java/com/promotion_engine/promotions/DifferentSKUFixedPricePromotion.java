package com.promotion_engine.promotions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.promotion_engine.common.PromotionProperties;
import com.promotion_engine.common.PromotionsConstant;
import com.promotion_engine.model.SkuItem;

public class DifferentSKUFixedPricePromotion implements Promotions {

	PromotionProperties properties = new PromotionProperties();

	@Override
	public int applyPromotionAndGetTotal(List<SkuItem> cartItems) throws IOException {

		String[] promoSKUs = (properties.getPropValues(PromotionsConstant.DIFF_SKU_FIXED_PRICE_FILE, "sku")).split(",");
		int price = Integer.parseInt(properties.getPropValues(PromotionsConstant.DIFF_SKU_FIXED_PRICE_FILE, "price"));

		int total = 0;

		Map<String, Integer> itemsMap = new HashMap<>();

		for (SkuItem item : cartItems) {
			String skuId = Character.toString(item.getSkuId());
			itemsMap.put(skuId, item.getQuantity());

			if (itemsMap.containsKey(promoSKUs[0]) && itemsMap.containsKey(promoSKUs[1])) {
				int quantity = Math.min(itemsMap.get(promoSKUs[0]), itemsMap.get(promoSKUs[1]));
				itemsMap.put(promoSKUs[0], itemsMap.get(promoSKUs[0]) - quantity);
				itemsMap.put(promoSKUs[1], itemsMap.get(promoSKUs[1]) - quantity);
				total += price * quantity;
			}
		}

		for (SkuItem item : cartItems) {

			String skuId = Character.toString(item.getSkuId());
			if (itemsMap.get(skuId) != 0) {
				total += itemsMap.get(skuId) * item.getPrice();
			}

		}

		return total;
	}

}
