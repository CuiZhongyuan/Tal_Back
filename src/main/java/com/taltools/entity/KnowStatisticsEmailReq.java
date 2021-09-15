package com.taltools.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("知识统计结果发送到Email")
public class KnowStatisticsEmailReq {

    @ApiModelProperty(value = "主题")
    @NotBlank(message = "主题 不能为空")
    String subject;

    @ApiModelProperty(value = "收件人列表，分号分割")
    @NotBlank(message = "收件人 不能为空")
    String toList;

    @ApiModelProperty(value = "抄送人列表，分号分割")
    String ccList;

    @ApiModelProperty(value = "附件流")
    @NotNull(message = "附件流 不能为空")
    @Size(min = 1)
    List<ByteArrayOutputStream> attachmentList;

    @ApiModelProperty(value = "附件名称,和附件流需要一一对应")
    @NotNull(message = "附件名称 不能为空")
    List<String> attachmentNameList;

    @ApiModelProperty(value = "正文")
    @NotBlank(message = "正文 不能为空")
    String content;
}
