package vn.techmaster.bookstore.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BookPOJO {
  @NotBlank
  @Size(min = 5, max = 400, message = "Tên sách từ 4 đến 400 ký tự")
  @Schema(description = "Tên sách", example = "Dế mèn phiêu lưu ký", required = true)
  private String title;

  @NotBlank
  @Size(min = 5, max = 200)
  @Schema(description = "Tác giả", example = "Tô Hoài", required = true)
  private String author;
}

