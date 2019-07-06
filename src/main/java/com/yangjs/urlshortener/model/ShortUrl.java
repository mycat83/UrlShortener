package com.yangjs.urlshortener.model;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@DynamoDBTable(tableName = "short_url")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShortUrl implements Serializable {
	private static final long serialVersionUID = 1L;

	@DynamoDBHashKey(attributeName = "token")
	private String token;

	@DynamoDBAttribute(attributeName = "url")
	private String url;
	
	private String shortUrl;
	
	private Boolean duplYn;
}
