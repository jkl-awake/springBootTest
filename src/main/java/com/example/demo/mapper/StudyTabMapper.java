package com.example.demo.mapper;

import com.example.demo.model.vo.study.StudyTabWithCategoryVo;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.dos.StudyTab;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudyTabMapper extends BaseMapper<StudyTab> {

    /**
     * author  work_ljk
     * 获取包含分类的学习标签
     * get study tab with its categories
     * */
    @Select("""
            select st.id as tabId,st.name as tabName,
                          sc.id as categoryId, sc.name as categoryName
                          from study_tab st
                                        left join study_category sc on st.id = sc.tab_id
                   where st.id = #{tabId} and st.is_deleted = false
                   order by sc.created_at desc
            """)
    List<StudyTabWithCategoryVo> getStudyTabWithCategories(Long tabId);
}
