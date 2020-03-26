package com.example.mapper;

        import com.example.entity.Workuser;
        import com.baomidou.mybatisplus.core.mapper.BaseMapper;
        import org.apache.ibatis.annotations.Param;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wanghao
 * @since 2020-03-04
 */
public interface WorkuserMapper extends BaseMapper<Workuser> {
    Workuser Login(@Param("wuser") String wuser, @Param("wpassword") String wpassword);
    int insert(@Param("wuser")String wuser, @Param("wpassword")String wpassword, @Param("wphone")String wphone, @Param("wemail")String wemail);
}
