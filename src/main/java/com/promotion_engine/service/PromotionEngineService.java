package com.promotion_engine.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.promotion_engine.model.SkuItem;
import com.promotion_engine.promotions.Promotions;

public class PromotionEngineService {

	List<SkuItem> items;

	public PromotionEngineService() {
		this.items = new ArrayList<SkuItem>();
	}

	public void addItem(SkuItem item) {
		this.items.add(item);
	}

	public int getTotalAfterPromotion(Promotions promotions) throws IOException {

		return promotions.applyPromotionAndGetTotal(items);
	}

}
