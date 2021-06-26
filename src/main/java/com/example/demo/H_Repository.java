package com.example.demo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface H_Repository extends JpaRepository<H_Entity,Integer>{

	// 例）SELECT e FROM Employee e WHERE e.lastname = ?1 ORDER BY e.age
    //List<Employee> findByLastnameOrderByAge(String lastname);
	
	
	//SELECT * FROM HITTER where id ORDER BY HOMERUN DESC;　これでホームラン多い順で並んだ
	//SELECT * FROM HITTER WHERE ID ORDER BY STOLEN DESC;盗塁順番
	//SELECT * FROM HITTER WHERE ID ORDER BY POINT DESC;打点順
	//SELECT * FROM HITTER WHERE ID ORDER BY HIT DESC; ヒット順番
	@Query(value = "SELECT * FROM HITTER where id ORDER BY HOMERUN DESC", nativeQuery=true)
	public List<H_Entity> findByHomerun();
	@Query(value = "SELECT * FROM HITTER WHERE id ORDER BY AT_BAT DESC", nativeQuery=true)
	public List<H_Entity> findByHit();
	@Query(value = "SELECT * FROM HITTER WHERE id ORDER BY POINT DESC",nativeQuery=true)
	public List<H_Entity> findByPoint();
	@Query(value = "SELECT * FROM HITTER WHERE id ORDER BY STOLEN DESC",nativeQuery=true)
	public List<H_Entity> findByStolen();
	
	//名前検索
	public List<H_Entity> findByNameLike(String name);
}



