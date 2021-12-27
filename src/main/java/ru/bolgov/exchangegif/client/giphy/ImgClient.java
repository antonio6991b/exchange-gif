package ru.bolgov.exchangegif.client.giphy;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;

import java.net.URI;

@FeignClient(value = "img")
public interface ImgClient {

    @RequestLine("GET")
    public Resource getGifPicture(URI baseUri);
}
