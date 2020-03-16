package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wanghao
 * @since 2020-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rec_job")
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "jid", type = IdType.AUTO)
    private Long jid;

    private String jname;

    @TableField("educationReq")
    private String educationReq;

    private Integer pid;

    @TableField("yearsReq")
    private Integer yearsReq;

    @TableField("reqPeople")
    private Integer reqPeople;

    private Float payment;

    @TableField("putDate")
    private LocalDateTime putDate;

    @TableField("cid")
    private Integer cid;

    private String cname;

    private String address;
}
