/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: GlobeExceptionHandler
 * Author:   Allen
 * Date:     2019/5/21
 * Description: 通用异常拦截器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.rest.interceptor;

import com.xueying.seeker.common.core.constant.CodeEnum;
import com.xueying.seeker.common.core.model.dto.RestDTO;
import com.xueying.seeker.common.web.interceptor.DefaultExceptionHandler;
import io.undertow.util.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolationException;

/**
 * 〈通用异常拦截〉<br>
 *
 * @author Allen
 * @create 2019/5/21
 * @since 1.0.0
 */
@ControllerAdvice
public class GlobeExceptionHandler extends DefaultExceptionHandler {
    /**
     * 拦截BindException异常
     *
     * @param e BindException异常
     * @return RestDTO
     */
    @Override
    @ExceptionHandler(value = {BindException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RestDTO handle(BindException e) {
        return super.handle(e);
    }

    /**
     * 拦截ConstraintViolationException异常
     *
     * @param e ConstraintViolationException异常
     * @return RestDTO
     */
    @Override
    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RestDTO handle(ConstraintViolationException e) {
        return super.handle(e);
    }

    /**
     * 拦截MissingServletRequestParameterException异常
     *
     * @param e MissingServletRequestParameterException异常
     * @return RestDTO
     */
    @Override
    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RestDTO handle(MissingServletRequestParameterException e) {
        return super.handle(e);
    }

    /**
     * Servlet异常处理
     *
     * @param e 异常
     * @return Object
     */
    @Override
    @ExceptionHandler(ServletException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RestDTO servletErrorHandler(ServletException e) {
        return super.servletErrorHandler(e);
    }

    /**
     * 默认异常 500(默认)
     *
     * @param e 异常
     * @return Object
     */
    @Override
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RestDTO defaultErrorHandler(Exception e) {
        //默认处理异常
        RestDTO restDTO = super.defaultErrorHandler(e);
        restDTO.setMessage(e.getMessage());
        //自定义参数异常
        if (e instanceof BadRequestException) {
            restDTO = new RestDTO(CodeEnum.PARAMS_ERROR);
        }
        return restDTO;
    }

}
