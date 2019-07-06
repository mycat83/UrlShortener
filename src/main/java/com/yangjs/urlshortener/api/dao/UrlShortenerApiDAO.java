package com.yangjs.urlshortener.api.dao;

import java.util.List;

import com.yangjs.urlshortener.model.ShortUrl;

public interface UrlShortenerApiDAO {
	
	public ShortUrl getOneShortUrl(String token);

	public List<ShortUrl> getAllShortUrl();
	
	public ShortUrl saveShortUrl(String token, String url);
	
	public void deleteShortUrl(ShortUrl shortUrl);

	public List<ShortUrl> getQueryUrl(String url);
}
