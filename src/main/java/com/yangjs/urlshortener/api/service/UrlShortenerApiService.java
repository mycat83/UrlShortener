package com.yangjs.urlshortener.api.service;

import java.util.List;

import com.yangjs.urlshortener.model.ShortUrl;

public interface UrlShortenerApiService {
	public ShortUrl getOneShortUrl(String token);

	public List<ShortUrl> getAllShortUrl();
	
	public ShortUrl saveShortUrl(String token);
	
	public void delelteShortUrl(String token);

	public ShortUrl existUrl(String url);
}
