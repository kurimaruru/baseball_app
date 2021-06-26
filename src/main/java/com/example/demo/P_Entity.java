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
@Table(name="picher")
public class P_Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;
	
	@Column(length = 20 ,nullable = false)
	@NotBlank
	private String name;
	
	@Column(nullable = false)
	@Min(0)
	@Max(500)
	@NotNull(message = "投球回数が未入力です。")
	private Integer inning;
	
	@Column(nullable = false)
	@Min(0)
	@Max(500)
	@NotNull(message = "失点数が未入力です。")
	private Double givven;
	
	@Column(nullable = false)
	@Min(0)
	@Max(500)
	@NotNull(message = "四死球が未入力です。")
	private Integer four_ball;

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

	public Integer getInning() {
		return inning;
	}

	public void setInning(Integer inning) {
		this.inning = inning;
	}

	public Double getGivven() {
		return givven;
	}

	public void setGivven(Double givven) {
		this.givven = givven;
	}

	public Integer getFour_ball() {
		return four_ball;
	}

	public void setFour_ball(Integer four_ball) {
		this.four_ball = four_ball;
	}
}
