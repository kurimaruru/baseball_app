package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="hitter")
public class H_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;

	@Column(length = 20 , nullable = false)
	@NotBlank
	private String name;
	@Column
	@Min(0)
	@Max(500)
	@NotNull(message="打数が未入力です。")
	private Double at_bat;
	@Column
	@Min(0)
	@Max(500)
	@NotNull(message = "ヒット数が未入力です。")
	private Double hit;
	@Column
	@Min(0)
	@Max(500)
	@NotNull(message = "本塁打数が未入力です。")
	private Integer homerun;
	@Column
	@Min(0)
	@Max(500)
	@NotNull(message = "打点が未入力です。")
	private Integer point;
	@Column
	@Min(0)
	@Max(500)
	@NotNull(message = "四死球が未入力です。")
	private Integer four;
	@Column
	@Min(0)
	@Max(500)
	@NotNull(message = "盗塁数が未入力です。")
	private Integer stolen;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Double getAt_bat() {
		return at_bat;
	}
	public void setAt_bat(Double at_bat) {
		this.at_bat = at_bat;
	}
	public Double getHit() {
		return hit;
	}
	public void setHit(Double hit) {
		this.hit = hit;
	}
	public Integer getHomerun() {
		return homerun;
	}
	public void setHomerun(Integer homerun) {
		this.homerun = homerun;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Integer getFour() {
		return four;
	}
	public void setFour(Integer four) {
		this.four = four;
	}
	public Integer getStolen() {
		return stolen;
	}
	public void setStolen(Integer stolen) {
		this.stolen = stolen;
	}



	;

}
