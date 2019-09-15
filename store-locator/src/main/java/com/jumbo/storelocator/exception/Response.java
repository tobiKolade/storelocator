package com.jumbo.storelocator.exception;

import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by tobi.oladimeji on 09/12/2019
 */
public class Response {
    private LocalDateTime timestamp;
    private String code;
    private String description;
    private List<Error> errors;

    public Response() {
    }

    public Response(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        String value = "";
        if (StringUtils.hasText(this.code) || StringUtils.hasText(this.description) || this.errors != null) {
            value = "Response{";
            if (StringUtils.hasText(this.code)) {
                value = value + "code='" + this.code + '\'';
            }

            if (StringUtils.hasText(this.description)) {
                value = value + ", description='" + this.description + '\'';
            }

            if (this.errors != null && !this.errors.isEmpty()) {
                value = value + this.errors;
            }

            value = value + '}';
        }

        return value;
    }
}
