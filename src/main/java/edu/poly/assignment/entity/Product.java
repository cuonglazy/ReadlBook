package edu.poly.assignment.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@NamedQuery(name = "getInventoryByCategory", query = "select new Report (p.category, sum (p.price), count (p)) " +
		"from Product p group by p.category order by sum (p.price) desc")
@Table(name = "Products")
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer masach;
	@NotEmpty(message = "Vui lòng nhập tên sản sách")
	@Column(columnDefinition = "nvarchar(100)")
	private String name;

	@NotNull(message = "Vui lòng nhập giá tiền")
	private Double price;

	@NotNull(message = "Vui lòng nhập số lượng sách")
	private Integer quantity;

	@NotNull(message = "Vui lòng nhập số trang sách")
	private Integer pageCount;

	@NotEmpty(message = "Vui lòng nhập nhà xuất bản")
	@Column(columnDefinition = "nvarchar(100)")
	private String publisher;

	@NotEmpty(message = "Vui lòng nhập tác giả")
	@Column(columnDefinition = "nvarchar(100)")
	private String author;

	@Column(columnDefinition = "nvarchar(500)")
	private String describe;

	@NotEmpty(message = "Vui lòng chọn ngôn ngữ")
	@Column(columnDefinition = "nvarchar(50)")
	private String language;

	private String photo;

	@NotNull(message = "Vui lòng chọn ngày thêm sản phẩm")
	@Column(name = "create_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date createDate = new Date();

	@NotNull(message = "Vui lòng chọn ngày nhập sản phẩm")
	@Column(name = "product_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date productDate = new Date();

	@NotNull(message = "Vui lòng chọn trạng thái")
	@Column(name = "is_delete")
	private Boolean isDelete = true;

	@NotNull(message = "Vui lòng chọn loại sản phẩm.")
	@ManyToOne
	@JoinColumn(name = "Category_id", referencedColumnName = "id", columnDefinition = "varchar(4)")
	@ToString.Exclude
	private Category category;

	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;

	@Override
	public String toString() {
		return String.valueOf(masach);
	}
}