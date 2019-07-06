package com.yangjs.urlshortener.api.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yangjs.urlshortener.api.dao.UrlShortenerApiDAO;
import com.yangjs.urlshortener.model.ShortUrl;

@Service
public class UrlShortenerApiServiceImpl implements UrlShortenerApiService {

	@Value("${baseurl}")
	private String baseUrl;
	
	@Value("${tokenlength}")
	private int tokenLength;
	
	@Autowired
	UrlShortenerApiDAO urlShortenerApiDAO;

	@Override
	public ShortUrl getOneShortUrl(String token) {
		return urlShortenerApiDAO.getOneShortUrl(token);
	}

	@Override
	public List<ShortUrl> getAllShortUrl() {
		return urlShortenerApiDAO.getAllShortUrl();
	}

	@Override
	public ShortUrl saveShortUrl(String url) {
		ShortUrl shortUrl = existUrl(url);
		
		if (shortUrl != null) {
			shortUrl.setShortUrl(baseUrl + shortUrl.getToken());
			shortUrl.setDuplYn(true);
			return shortUrl;
		}
		
		Random random = new Random();
		StringBuffer token = new StringBuffer();
		
		for (int i = 0; i < tokenLength; i++) {
		    int type = random.nextInt(3);
		    if (type == 0) {
		    	char alphabet = (char)(random.nextInt(26) + 97);	// a-z
		    	token.append(alphabet);	
		    } else if (type == 1) {
		    	char alphabet = (char)(random.nextInt(26) + 65);	// A-Z
		    	token.append(alphabet);	
		    } else if (type == 2) {
		    	int number = random.nextInt(10);					// 0-9
		    	token.append(number);	
		    }
		}
		
		shortUrl = urlShortenerApiDAO.saveShortUrl(token.toString(), url);
		shortUrl.setShortUrl(baseUrl + shortUrl.getToken());
		shortUrl.setDuplYn(false);
		
		return shortUrl;
	}

	@Override
	public void delelteShortUrl(String token) {
		ShortUrl shortUrl = new ShortUrl();
		
		shortUrl.setToken("test");

		urlShortenerApiDAO.deleteShortUrl(shortUrl);
	}

	@Override
	public ShortUrl existUrl(String url) {
		List<ShortUrl> shortUrlList = urlShortenerApiDAO.getQueryUrl(url);
		
		if (shortUrlList.size() == 0) {
			return null;
		} 

		return shortUrlList.get(0);
	}
	

}
