package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
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
@TableName("rec_workuser")
public class Workuser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userid;

    private String wuser;

    private String wpassword;

    private String wphone;

    private String wemail;

    private LocalDate wregtime;



}
