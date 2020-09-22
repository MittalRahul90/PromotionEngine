package com.promotion_engine.promotions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MultipleItemsSameSKUPromotion implements Promotions {

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

		String priceValue = getPropValues("promotions_sameSKUPrice.properties", Character.toString(skuId));
		return Integer.parseInt(priceValue);
	}

	private int fetchPromotionQuantity(char skuId) throws IOException {

		String promotionQuantity = getPropValues("promotions_sameSKUQuantity.properties", Character.toString(skuId));
		return Integer.parseInt(promotionQuantity);
	}

	public String getPropValues(String fileName, String skuId) throws IOException {
		
		InputStream inputStream = null;
		Properties prop = new Properties();
		
		try {
			String propFileName = fileName;
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStream.close();
		}
		return prop.getProperty(skuId);
	}

}
