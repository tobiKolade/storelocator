package com.jumbo.storelocator.exception;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@ResponseBody
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public MessageSource messageSource;

    protected String localizeErrorMessage(String errorCode, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(errorCode, args, locale);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class, RequestNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleValidationException(Exception e) {
        Response response = new Response();
        response.setCode("bad_request");
        response.setTimestamp(LocalDateTime.now());
        response.setDescription(localizeErrorMessage("request.invalid"));
        BindingResult result;
        if (e instanceof MethodArgumentNotValidException) {
            result = ((MethodArgumentNotValidException) e).getBindingResult();
        } else if (e instanceof BindException) {
            result = ((BindException) e).getBindingResult();
        } else {
            result = ((RequestNotValidException) e).getBindingResult();
        }
        List<Error> errors = new ArrayList<>();
        result.getGlobalErrors()
                .forEach(error ->
                        errors.add(new Error(error.getObjectName(), messageSource.getMessage(error, Locale.getDefault()))));
        result.getFieldErrors()
                .forEach(
                        error -> {
                            FieldError resolvable = error;
                            if (error.isBindingFailure()) {
                                String[] codes = error.getCodes();
                                Object[] args = new Object[3];
                                args[0] = error.getRejectedValue();

                                resolvable = new FieldError(error.getObjectName(), error.getField(),
                                        error.getRejectedValue(), error.isBindingFailure(),
                                        codes, args, error.getDefaultMessage());
                            }
                            errors.add(new Error(error.getField(), messageSource.getMessage(resolvable, Locale.getDefault())));
                        }
                );
        response.setErrors(errors);
        return response;
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Response handleMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        Response response = new Response();
        response.setCode("method_not_allowed");
        response.setTimestamp(LocalDateTime.now());
        response.setDescription(localizeErrorMessage("method.not.allowed", e.getMethod(),
                StringUtils.join(e.getSupportedMethods(), ", ")));
        response.setErrors(new ArrayList<>());
        return response;
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response handleResourceNotFoundException(ResourceNotFoundException e) {
        String resource = e.getResource();
        Response response = new Response();
        response.setCode(resource.replaceAll(" ", "_").toLowerCase() + "_not_found");
        response.setDescription(localizeErrorMessage(e.getMessageCode(), e.getResource(), e.getResourceId()));
        return response;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleException(Exception e) {
        Response response = new Response();
        response.setCode("server_error");
        response.setTimestamp(LocalDateTime.now());
        response.setErrors(new ArrayList<>());
        String logKey = String.format("%s-%s", "ISE", RandomStringUtils.randomAlphanumeric(6).toUpperCase(Locale.ROOT));
        response.setDescription(localizeErrorMessage("server.error", logKey));
        logger.error(String.format("Server Error: %s", logKey), e);
        return response;
    }
}
