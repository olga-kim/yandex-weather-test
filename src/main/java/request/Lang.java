package request;

public enum Lang {

    ru_RU("https://yandex.ru/pogoda/"),
    ru_UA("https://yandex.ua/pogoda/"),
    uk_UA("https://yandex.ua/pogoda/"),
    be_BY("https://yandex.by/pogoda/"),
    kk_KZ("https://yandex.kz/pogoda/"),
    tr_TR("https://yandex.com.tr/hava/"),
    en_US("https://yandex.com/pogoda/");

    private String url;

    Lang(String url) {
        this.url = url;
    }

    public String url() {
        return url;
    }
}
