package com.messagecenter.portal.mapper;

import com.messagecenter.portal.entity.*;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Eduardo Macarron
 */
@Mapper
public interface SMSTemplateMapper {

    SMSTemplateEntity getById(int id);
}