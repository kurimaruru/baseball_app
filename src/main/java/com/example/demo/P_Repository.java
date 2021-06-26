package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface P_Repository extends JpaRepository<P_Entity , Integer>{
	@Query(value = "SELECT * FROM PICHER WHERE id ORDER BY GIVVEN ASC",nativeQuery=true)
	public List<P_Entity> findByGivven();
	@Query(value = "SELECT * FROM PICHER WHERE ID ORDER BY FOUR_BALL ASC",nativeQuery=true)
	public List<P_Entity> findByFour();
	
	public List<P_Entity> findByNameLike(String name);
}
