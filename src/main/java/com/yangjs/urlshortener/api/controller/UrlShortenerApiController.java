package com.yangjs.urlshortener.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yangjs.urlshortener.api.service.UrlShortenerApiService;
import com.yangjs.urlshortener.model.ResponseMessage;
import com.yangjs.urlshortener.model.ShortUrl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/api")
@Api(value = "HelloController", description = "헬로 에이피아이")
public class UrlShortenerApiController {
	
	@Autowired
	UrlShortenerApiService urlShortenerApiService;
	
	@RequestMapping(value = "/shorturl", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessage> getShortUrlList() {
		
		List<ShortUrl> list = urlShortenerApiService.getAllShortUrl();
		
		ResponseMessage message = new ResponseMessage("success", "", list); 
		
		return new ResponseEntity<ResponseMessage>(message, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/shorturl/{token}", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessage> getShortUrl(@PathVariable("token") String token) {
		
		ShortUrl shortUrl = urlShortenerApiService.getOneShortUrl(token);

		ResponseMessage message = new ResponseMessage("success", "", shortUrl); 

		return new ResponseEntity<ResponseMessage>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "/shorturl", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> setShortUrl(@RequestBody String url) {
		
		ShortUrl shortUrl = urlShortenerApiService.saveShortUrl(url);
		
		ResponseMessage message = new ResponseMessage("success", "", shortUrl); 
		
		return new ResponseEntity<ResponseMessage>(message, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/shorturl/{token}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseMessage> delShortUrl(@PathVariable("token") String token) {
		ResponseMessage message = new ResponseMessage("success", "", ""); 
		
		urlShortenerApiService.delelteShortUrl(token);
		
		return new ResponseEntity<ResponseMessage>(message, HttpStatus.OK);
	}
}
