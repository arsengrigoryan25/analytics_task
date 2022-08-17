package org.task.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ResponseParser {

  private final RestTemplate restTemplate;

  public ResponseParser(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public Document parse(String url) {
    return Jsoup.parse(getResponse(url));
  }

  private String getResponse(String url) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(List.of(MediaType.APPLICATION_JSON));
      HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
      return restTemplate.exchange(url, HttpMethod.GET, entity, String.class).toString();
    } catch (HttpClientErrorException exception) {
      // TO:DO log failed urls
      System.out.println(url);
      return "";
    }
  }
}
