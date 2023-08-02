package edu.poly.assignment.entity;

import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Orders")
public class Order implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long maDH;

	@NotEmpty(message = "Vui lòng nhập địa chỉ đơn hàng")
	private String address;

	@NotEmpty(message = "Vui lòng nhập số điện thoại khách hàng")
	private String phone;

	@NotNull(message = "Vui lòng chọn ngày đặt hàng")
	@Temporal(TemporalType.DATE)
	@Column(name = "CreateDate")
	private Date createDate = new Date();

	@NotNull(message = "Vui lòng chọn phương thức thanh toán")
	private String payment;

	@NotNull(message = "Vui lòng chọn trạng thái đơn hàng")
	private String status;

	@NotEmpty(message = "Vui lòng nhập tên khách hàng")
	@ManyToOne
	@JoinColumn(name = "Username")
	private Account account;
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails;

	public String getMaDH() {
		return "HD" + String.format("%02d", maDH);
	}
}