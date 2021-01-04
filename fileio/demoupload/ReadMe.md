# File upload và xử lý lỗi

## Tham khảo
. http://zetcode.com/springboot/uploadfile/
. https://www.baeldung.com/spring-file-upload
## Kiến trúc

UploadController --> StorageService

Nếu có lỗi thì throw StorageException

Định nghĩa đường dẫn lưu file trong application.properties
```json
upload.path=/Users/techmaster/Desktop/upload/
```

## Hiển thị lỗi
```java
@ExceptionHandler(StorageException.class)
  public String handleStorageFileNotFound(StorageException e, Model model) {
    model.addAttribute("errorMessage", e.getMessage());
    return "failure";
  }
```

## Customize thông báo lỗi

Tạo file báo lỗi 404 ở thư mục
resources > templates > error > 404.html

## Tham khảo 

[](https://spring.io/guides/gs/uploading-files/)
