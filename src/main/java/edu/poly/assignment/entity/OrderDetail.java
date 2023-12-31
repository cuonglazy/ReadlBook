package edu.poly.assignment.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "OrderDetails")
public class OrderDetail implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double price;
	private Integer quantity;
	@ManyToOne @JoinColumn(name = "ProductId")
	private Product product;
	@ManyToOne @JoinColumn(name = "OrderId")
	private Order order;

	public String getMaCTDH(){
		return "CTHD" + String.format("%02d", id);
	}
}

