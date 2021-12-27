package ru.bolgov.exchangegif.controller;

import feign.Feign;
import feign.Target;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bolgov.exchangegif.client.giphy.GifClient;
import ru.bolgov.exchangegif.client.giphy.ImgClient;
import ru.bolgov.exchangegif.client.openexchange.ExchangeClient;
import ru.bolgov.exchangegif.model.Currency;
import ru.bolgov.exchangegif.model.gif.GifDescription;
import ru.bolgov.exchangegif.settings.ProgramSettings;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@RestController
@Import(FeignClientsConfiguration.class)
public class CurrencyController {

    private ExchangeClient exchangeClient;
    private GifClient gifClient;
    private ImgClient imgClient;

    private ProgramSettings programSettings;

    @Autowired
    public CurrencyController(ExchangeClient exchangeClient, GifClient gifClient,
                              Decoder decoder, Encoder encoder, ProgramSettings programSettings){
        this.exchangeClient = exchangeClient;
        this.gifClient = gifClient;
        this.programSettings = programSettings;
        imgClient = Feign.builder().encoder(encoder).decoder(decoder)
                .target(Target.EmptyTarget.create(ImgClient.class));
    }

    public CurrencyController() {

    }


    @GetMapping(value = "/compare", produces = "image/gif")
    public ResponseEntity<Resource> compareCurrency(@RequestParam Map<String, String> headers, HttpServletResponse res) throws IOException, URISyntaxException {
        String baseCur = Optional.ofNullable(headers.get("base")).orElse(programSettings.getBase());

        String yesterday = LocalDate.now().minusDays(1).toString();
        Currency todayCourse = exchangeClient.getTodayCurrency(programSettings.getOpenexchangeKey(), baseCur);
        Currency yesterdayCourse = exchangeClient.getYesterdayCurrency(programSettings.getOpenexchangeKey(), yesterday, baseCur);
        String currencyToCompare = headers.get("currency");
        int difference = todayCourse.getRates().get(currencyToCompare)
                .compareTo(yesterdayCourse.getRates().get(currencyToCompare));

        GifDescription gifDescription = null;
        String gifName = "equals";
        if(difference > 0){
            gifDescription = gifClient.getRandomGif(programSettings.getGiphyKey(), programSettings.getRichTag());
            gifName = "rich";
        }
        if(difference < 0){
            gifDescription = gifClient.getRandomGif(programSettings.getGiphyKey(), programSettings.getBrokeTag());
            gifName = "poor";
        }

        String imgUrl = gifDescription.getData().getImages().get("original").get("url");

        imgUrl = imgUrl.replaceAll("%3F", "?");
        imgUrl = imgUrl.replaceAll("%3D", "=");
        imgUrl = imgUrl.replaceAll("%26", "&");

        URI uri = new URI(imgUrl);

        Resource file = imgClient.getGifPicture(uri);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + gifName).body(file);
    }

}
