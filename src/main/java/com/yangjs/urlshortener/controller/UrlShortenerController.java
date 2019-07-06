package com.yangjs.urlshortener.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.yangjs.urlshortener.service.UrlShortenerService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UrlShortenerController {
	
	@Value("${baseurl}")
	private String baseUrl;
	
	@Value("${api.shorturl}")
	private String apiShortUrl;
	
	@Autowired
	private UrlShortenerService urlShortenerService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView shortUrlList() {
		ModelAndView mav = new ModelAndView();

		List<Map<String, Object>> list = urlShortenerService.getShortUrlList();
        mav.addObject("list", list);
        mav.addObject("apiShortUrl", apiShortUrl);
		mav.setViewName("shorturl");
		
		return mav;
	}
	
	@RequestMapping(value = "/{token}", method = RequestMethod.GET)
	public RedirectView redirectUrl(@PathVariable("token") String token) {
		String redirectUrl = urlShortenerService.getShortUrl(token);
		
		if (redirectUrl == null) {
			redirectUrl = baseUrl;
		}
		
		RedirectView redirectView = new RedirectView(redirectUrl);
		redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
		
		return redirectView ;
	}

    @ExceptionHandler(value=Exception.class)
    public String handleDemoException(Exception e) {
        log.error(e.getMessage());
        return "/error/error";
    }
}
