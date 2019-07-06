package com.yangjs.urlshortener.service;

import java.util.List;
import java.util.Map;

public interface UrlShortenerService {

	public List<Map<String, Object>> getShortUrlList();
	
	public String getShortUrl(String token);
}
