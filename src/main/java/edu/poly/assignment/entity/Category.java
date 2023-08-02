package edu.poly.assignment.entity;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "Categories")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category implements Serializable {
	@Id
	@NotEmpty(message = "VUi lòng nhập mã")
	@Length(min = 4, max = 4)
	private String id;
	@NotBlank
	@NotEmpty(message = "Vui lòng nhập tên thể loại")
	@Length(max = 50, min = 3, message = "Độ dài từ 3 đến 50 ký tự")
	@Column(columnDefinition = "nvarchar(255)")
	private String name;
	@NotNull(message = "Vui lòng chọn trạng thái")
	@Column(name = "is_delete")
	private Boolean isDelete = false;
	@OneToMany(mappedBy = "category")
	@ToString.Exclude
	private List<Product> products;
}