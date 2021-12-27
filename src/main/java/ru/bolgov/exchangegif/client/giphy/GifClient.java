package ru.bolgov.exchangegif.client.giphy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bolgov.exchangegif.model.gif.GifDescription;

@FeignClient(value = "giphy", url = "${giphyUrl}")
public interface GifClient {

    @RequestMapping("/random?api_key={api_key}&tag={tag}")
    public GifDescription getRandomGif(@PathVariable("api_key") String apiKey, @PathVariable("tag") String tag);
}
