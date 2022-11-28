package com.bitacademy.mysite.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WebUtil {
	public static String encodeURL(String url, String encode) {
		String urlEncode = null;
		try {
			urlEncode = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return urlEncode;
	}
}

// 검색창에 검색어 입력 시 encoding -> UTF-8로 변환해주는 기능을 하는 페이지