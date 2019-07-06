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

	@SuppressWarnings("unchecked")
	@Override
	public String getShortUrl(String token) {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<ResponseMessage> responseEntity = restTemplate.getForEntity(apiShortUrl + "/'" + token, ResponseMessage.class);
		ResponseMessage responseMessage = responseEntity.getBody();
		Map<String, Object> data = (Map<String, Object>) responseMessage.getData();
		
		if (data == null) {
			return null;
		}
				
		return (String) data.get("url");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getShortUrlList() {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<ResponseMessage> responseEntity = restTemplate.getForEntity(apiShortUrl, ResponseMessage.class);
		ResponseMessage responseMessage = responseEntity.getBody();
		List<Map<String, Object>> list = (List<Map<String, Object>>) responseMessage.getData();
				
		return list;
	}
}