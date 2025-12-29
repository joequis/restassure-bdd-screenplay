package com.company.reqres.infra.config;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

public class ConfigLoader {
    private static ConfigLoader instance;
    private final Properties props;

    private ConfigLoader() {
        props = new Properties();
        String file = "config.properties";
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(file)) {
            if (is == null) throw new IllegalStateException("No se encontró " + file);
            props.load(is);
        } catch (Exception e) {
            throw new IllegalStateException("Error cargando " + file, e);
        }
    }

    public static ConfigLoader getInstance() {
        if (instance == null) { instance = new ConfigLoader(); }
        return instance;
    }

    public String get(String key) {
        String v = props.getProperty(key);
        if (v == null) throw new IllegalArgumentException("Clave no encontrada: " + key);
        return v.trim();
    }

    public String baseUri() { return get("baseUri"); }
    public int timeoutMs() { return Integer.parseInt(get("timeout.ms")); }

    public Properties defaultHeaders() {
        Properties out = new Properties();
        props.forEach((k, v) -> {
            String key = String.valueOf(k);
            if (key.startsWith("default.headers.")) {
                String headerName = key.substring("default.headers.".length());
                out.put(headerName, v);
            }
        });
        return out;
    }

    public String endpoint(String key) { return get(key); }

    /** Usa placeholders ordinales {0}, {1}, ... */
    public String endpointFmt(String key, Object... args) {
        return MessageFormat.format(get(key), args);
    }
}
