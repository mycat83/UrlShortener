package com.yangjs.urlshortener.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.yangjs.urlshortener.service.UrlShortenerService;

@Controller
public class UrlShortenerController {
	
	@Autowired
	private UrlShortenerService urlShortenerService;
	
	@RequestMapping(value = "/shorturl", method = RequestMethod.GET)
	public ModelAndView shortUrlList() {
		ModelAndView mav = new ModelAndView();

		List<Map<String, Object>> list = urlShortenerService.getShortUrlList();
        mav.addObject("list", list);
		mav.setViewName("shorturl");
		
		return mav;
	}
	
	@RequestMapping(value = "/shorturl/{token}", method = RequestMethod.GET)
	public RedirectView redirectUrl(@PathVariable("token") String token) {
		String redirectUrl = urlShortenerService.getShortUrl(token);
		RedirectView redirectView = new RedirectView(redirectUrl);
		redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
		
		return redirectView ;
	}

}
