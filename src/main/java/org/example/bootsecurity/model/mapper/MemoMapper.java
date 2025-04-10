package org.example.bootsecurity.model.mapper;

import org.apache.ibatis.annotations.*;
import org.example.bootsecurity.model.domain.Memo;
import org.springframework.stereotype.Repository;

import java.util.List;

//spring
@Repository
//MyBatis
@Mapper
public interface MemoMapper {
    @Select("SELECT * FROM memo")
    List<Memo> findAll();

    @Insert("INSERT INTO memo (text) VALUES (#{text})")
//  Supabase는 알아서 Auto로 증가를 하고 있는데... 이걸 개입하려고 하니까...
//    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Memo memo);

    @Delete("DELETE FROM memo")
    void deleteAll();

    @Delete("DELETE FROM memo WHERE id = (#{id})")
    void deleteById(Long id);
}
