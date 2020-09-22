package com.promotion_engine.promotions;

import java.io.IOException;

public interface Promotions {

	int applyPromotionAndGetTotal(char skuId, int quantity, int price) throws IOException;

}
