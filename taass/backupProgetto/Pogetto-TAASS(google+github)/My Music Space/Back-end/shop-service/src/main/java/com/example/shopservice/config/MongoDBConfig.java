package com.example.shopservice.config;

import com.example.shopservice.model.Item;
import com.example.shopservice.model.ItemType;
import com.example.shopservice.service.ShopService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
public class MongoDBConfig {


    @Autowired
    private ShopService service;

    @Autowired
    ApplicationContext appContext;

    @Bean
    public int initItemDB() {

        String strJson = null;
        ClassPathResource classPathResource = new ClassPathResource("init.json");
        try {
            byte[] binaryData = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
            strJson = new String(binaryData, StandardCharsets.UTF_8);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement jsonElement = gson.fromJson(strJson, JsonElement.class);
            JsonArray jsonArray = jsonElement.getAsJsonArray();

            for (JsonElement element : jsonArray) {
                if (element.isJsonObject()) {

                    JsonObject jsonObject = element.getAsJsonObject();
                    String itemName = jsonObject.get("itemName").getAsString();

                    if (!service.checkItemExits(itemName)) {

                        Item item = null;
                        String type = jsonObject.get("type").getAsString();
                        String artistName = jsonObject.get("artistName").getAsString();
                        String vendor = jsonObject.get("vendor").getAsString();
                        String description = jsonObject.get("description").getAsString();
                        String imageURL = jsonObject.get("imageURL").getAsString();
                        double price = jsonObject.get("price").getAsDouble();
                        int availability = jsonObject.get("availability").getAsInt();

                        if (type.equals("PRODUCT")) {
                            String productType = jsonObject.get("productType").getAsString();
                            item = new Item(itemName, artistName, vendor, description, imageURL, ItemType.PRODUCT, productType, price, availability);
                        } else {
                            String location = jsonObject.get("location").getAsString();
                            String date = jsonObject.get("date").getAsString();
                            item = new Item(itemName, artistName, vendor, description, imageURL, location, date, ItemType.EVENT, price, availability);
                        }
                        service.addNewItem(item);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}