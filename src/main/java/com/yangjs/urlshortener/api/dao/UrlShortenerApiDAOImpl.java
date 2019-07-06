package com.yangjs.urlshortener.api.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.yangjs.urlshortener.model.ShortUrl;

@Repository
public class UrlShortenerApiDAOImpl implements UrlShortenerApiDAO {

	@Autowired
	private DynamoDBMapper mapper;

	@Override
	public ShortUrl getOneShortUrl(String token) {
		return mapper.load(ShortUrl.class, token);
	}

	@Override
	public List<ShortUrl> getAllShortUrl() {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		
		return mapper.scan(ShortUrl.class, scanExpression);
	}
	
	@Override
	public ShortUrl saveShortUrl(String token, String url) {
		ShortUrl shortUrl = new ShortUrl(token, url, "", null);
		mapper.save(shortUrl);
		
		return shortUrl;
	}

	@Override
	public void deleteShortUrl(ShortUrl shortUrl) {
		mapper.delete(shortUrl);
	}

	@Override
	public List<ShortUrl> getQueryUrl(String url) {
		Map<String, String> ean = new HashMap<String, String>();
		ean.put("#url", "url");
        
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":url", new AttributeValue().withS(url));

        DynamoDBQueryExpression<ShortUrl> queryExpression = new DynamoDBQueryExpression<ShortUrl>()
        		.withIndexName("url-index")
                .withKeyConditionExpression("#url = :url")
                .withExpressionAttributeNames(ean)
                .withExpressionAttributeValues(eav);
        
        queryExpression.setConsistentRead(false);

		return mapper.query(ShortUrl.class, queryExpression);
	}
}
