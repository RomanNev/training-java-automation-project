package tests.ownerLibrary.config;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class LegacyWebDriverConfig {
    public String getBaseUrl(){
        String baseUrl = System.getProperty("baseUrl");
        if (Objects.isNull(baseUrl)){ // если параметр не был передан при запуске, берем дефолтное значение
            baseUrl = "https://github.com";
        }
        return baseUrl;
    }

    public Browser getBrowser(){
        String browser = System.getProperty("browser"); //читаем занчение
        if (Objects.isNull(browser)){  // обрабатываем дефолтное
            return Browser.CHROME; // конвентируем результат
        }
        return Browser.valueOf(browser);
    }

    public boolean isRemote(){
        String isRemote = System.getProperty("isRemote");
        if (Objects.isNull(isRemote)){
            return false;
        }
        return Boolean.parseBoolean(isRemote);
    }

    public URL getRomoteUrl()  {
        String remoteUrl = System.getProperty("remoteUrl");
        if (Objects.isNull(remoteUrl)){
            return null;
        }
        try {//конвертируем результат
            return new URL(remoteUrl);
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
