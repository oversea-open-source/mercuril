package com.messagecenter.portal.handler;

import com.messagecenter.portal.entity.base.BaseResponse;
import com.messagecenter.portal.entity.base.StatusCode;
import com.messagecenter.portal.exception.BusinessException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

/**
 * Created by Jared on 16/12/7.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * set validation error globally
     *
     * @param exception
     * @param request
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = BindException.class)
    public Object validateExceptionHandler(BindException exception, WebRequest request) throws Exception {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        return validationException(fieldErrors);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception, WebRequest request) throws Exception {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        return validationException(fieldErrors);
    }

    private Object validationException(List<FieldError> fieldErrors) {
        BaseResponse response = new BaseResponse();
        response.setCode(StatusCode.ERROR);
        if (null != fieldErrors && fieldErrors.size() > 0) {
            response.setMessage(fieldErrors.get(0).getDefaultMessage());
        }

        return response;
    }

    @ExceptionHandler(value = BusinessException.class)
    public Object businessExceptionHandler(BusinessException exception, WebRequest request) {
        BaseResponse response = new BaseResponse();
        response.setCode(StatusCode.ERROR);
        response.setMessage(exception.getMessage());
        return response;
    }
}
