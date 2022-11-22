package com.bitacademy.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.vo.GalleryVo;

@Repository
public class GalleryRepository {
	@Autowired
	private SqlSession sqlSession;

	public Boolean insert(GalleryVo galleryVo) {
		int count = sqlSession.insert("gallery.insert", galleryVo);
		return count == 1;
	}

	public Boolean deleteByNo(Long no) {
		int count = sqlSession.delete("gallery.deleteByNo", no);
		return count ==1;
	}

}
