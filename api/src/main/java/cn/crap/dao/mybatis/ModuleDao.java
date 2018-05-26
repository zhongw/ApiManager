package cn.crap.dao.mybatis;

import cn.crap.model.mybatis.Module;
import cn.crap.model.mybatis.ModuleCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModuleDao {
    int countByExample(ModuleCriteria example);

    int deleteByExample(ModuleCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(Module record);

    int insertSelective(Module record);

    List<Module> selectByExample(ModuleCriteria example);

    Module selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Module record, @Param("example") ModuleCriteria example);

    int updateByExample(@Param("record") Module record, @Param("example") ModuleCriteria example);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
}