/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: BaseDO
 * Author:   Allen
 * Date:     2019/9/19
 * Description: 基础DO
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.model.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 基础DO
 * @author Allen
 * @date 2019/9/19
 */
@Setter
@Getter
public abstract class BaseDO {

    /**
     * 是否启用：0-不可用，1-可用
     */
    private Boolean enabled;

    /**
     * 删除标示：0-未删除，1-已删除
     */
    @TableLogic
    private Boolean deleted;

    /**
     * 创建人ID
     */
    protected Long creatorId;

    /**
     * 创建人
     */
    protected String creator;

    /**
     * 创建时间
     */
    @SuppressFBWarnings("EI_EXPOSE_REP")
    protected Date dateCreated;

    /**
     * 修改人ID
     */
    protected Long modifierId;

    /**
     * 修改人
     */
    protected String modifier;

    /**
     * 更新时间
     */
    @SuppressFBWarnings("EI_EXPOSE_REP")
    protected Date lastModified;

}
