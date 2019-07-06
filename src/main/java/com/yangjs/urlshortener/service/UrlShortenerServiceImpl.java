package com.yangjs.urlshortener.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yangjs.urlshortener.model.ResponseMessage;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

	@Value("${api.shorturl}")
	private String apiShortUrl;

	@Value("${api.shorturllist}")
	private String apiShortUrlList;

	@SuppressWarnings("unchecked")
	@Override
	public String getShortUrl(String token) {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<ResponseMessage> responseEntity = restTemplate.getForEntity(apiShortUrl, ResponseMessage.class, token);
		ResponseMessage responseMessage = responseEntity.getBody();
		Map<String, Object> shortUrl = (Map<String, Object>) responseMessage.getData();
				
		return (String) shortUrl.get("url");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getShortUrlList() {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<ResponseMessage> responseEntity = restTemplate.getForEntity(apiShortUrlList, ResponseMessage.class);
		ResponseMessage responseMessage = responseEntity.getBody();
		List<Map<String, Object>> list = (List<Map<String, Object>>) responseMessage.getData();
				
		return list;
	}
}