package com.kerwin.spring.mycrm.permision.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @className: TestVO
 * @version: v1.0.0
 * @author: d.w
 * @date: 2019-09-09 16:09
 */
@Setter
@Getter
@ToString
public class TestVO
{
    @NotNull
    private Integer id;

    @NotBlank
    private String name;

    private List list;

    private Integer sex;
}
