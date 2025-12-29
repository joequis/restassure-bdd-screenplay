package com.company.reqres.infra.http;

import com.company.reqres.infra.config.ConfigLoader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

public class HttpSpec {

    public static RequestSpecification build() {
        //var cfg = ConfigLoader.getInstance();
        var cfg = ConfigLoader.getInstance();
        var builder = new RequestSpecBuilder()
                .setBaseUri(cfg.baseUri())
                .setRelaxedHTTPSValidation()
                .log(LogDetail.ALL);

        cfg.defaultHeaders().forEach((k, v) ->
                builder.addHeader(String.valueOf(k), String.valueOf(v)));

        // Si deseas timeouts: configura RestAssuredConfig con HttpClientConfig (opcional)

        return builder.build();
    }
}
