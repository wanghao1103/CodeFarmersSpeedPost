package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("rec_worker")
public class Worker implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "wid", type = IdType.AUTO)
    private Long wid;

    private String wname;

    private Integer sex;

    private LocalDate birth;

    private String wtype;

    private String title;

    private String skill;

    private Integer years;

    private String major;

    private String education;

    private Integer state;

    private Integer isPrivate;

    private Integer userid;


}
