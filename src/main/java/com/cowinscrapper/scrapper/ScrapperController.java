package com.cowinscrapper.scrapper;

import com.cowinscrapper.scrapper.model.Total;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class ScrapperController {

    private static final ObjectMapper objectMapper =
            new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @GetMapping("/scrappers")
    String all() {
        return "Hello";
    }

    public static void main(String[] args) throws IOException, JSONException {
        JSONObject json = new JSONObject(IOUtils.toString(new URL("https://data.covid19india.org/v4/min/data.min.json"), StandardCharsets.UTF_8));
        Iterator<?> keys = json.keys();

        List<String[]> dataList = new ArrayList<>();
        // adding header to csv
        String[] header = {"country", "other", "deceased", "recovered", "tested", "vaccinated1", "vaccinated2",};
        dataList.add(header);
        while (keys.hasNext()) {
            String key = (String) keys.next();
            System.out.println(key);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Total total = objectMapper.readValue(json.getJSONObject(key).get("total").toString(), Total.class);
            System.out.println(total);



            //
            String[] data = {key, String.valueOf(total.getOther()), String.valueOf(total.getDeceased()), String.valueOf(total.getRecovered()),
                    String.valueOf(total.getTested()), String.valueOf(total.getVaccinated1()), String.valueOf(total.getVaccinated2())};


            dataList.add(data);

        }


        // default all fields are enclosed in double quotes
        // default separator is a comma
        try (CSVWriter writer = new CSVWriter(new FileWriter("D:\\file\\test.csv"))) {
            writer.writeAll(dataList);
        }


    }

}
