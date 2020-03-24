package com.auto.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Anna.wang
 * @since 2020-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TestRule对象", description="")
public class TestRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用例编号")
    private Integer caseId;

    @ApiModelProperty(value = "验证方式")
    private String rule;

    @ApiModelProperty(value = "验证表达式")
    private String expression;

    @ApiModelProperty(value = "匹配规则")
    private String operator;

    @ApiModelProperty(value = "期望值")
    private String expected;

    @ApiModelProperty(value = "创建人")
    private Integer createUser;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
