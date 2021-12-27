package ru.bolgov.exchangegif.client.openexchange;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bolgov.exchangegif.model.Currency;

@FeignClient(value = "exchange", url = "${openexchangeUrl}")
public interface ExchangeClient {

    @RequestMapping("/latest.json?app_id={app_id}&base={base}")
    public Currency getTodayCurrency(@PathVariable("app_id") String appId, @PathVariable("base") String base);

    @RequestMapping("/historical/{date}.json?app_id={app_id}&base={base}")
    public Currency getYesterdayCurrency(@PathVariable("app_id") String appId, @PathVariable("date") String date, @PathVariable("base") String base);
}
