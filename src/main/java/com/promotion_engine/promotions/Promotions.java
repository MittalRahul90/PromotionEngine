package com.promotion_engine.promotions;

import java.io.IOException;
import java.util.List;

import com.promotion_engine.model.SkuItem;

public interface Promotions {

	int applyPromotionAndGetTotal(List<SkuItem> cartItems) throws IOException;

}
