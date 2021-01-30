# File I/O

1. Upload file từ HTML Form (demoupload example)
   - Hạn chế kích thước file upload
   - Hạn chế file upload
   - Cấu hình thư mục lưu file upload lên
2. Upload file với RestController
3. Demo tổng hợp - App quản lý file trên server  
- Chức năng của app  
    - Show thư mục root (quản lý file upload, chia ra 2 thư mục con cho user thường và admin)
     - Liệt kê file và folder thuộc trực tiếp folder đang làm việc
    - Upload fle vào folder đang làm việc
    - Xóa file/folder
     - Tạo folder con
     - Download file  
- Các bước thực hiện
     - Cấu hình thư mục gốc lưu file upload trên server + hạn chế kích thước file
    - Phân quyền với spring security và sử dụng thư mục riêng chỉ cho admin truy cập
    - Viết HomeController để show home page lên UI (thymeleaf) 
    - Viết FileRestController để cung cấp các api làm việc với file, viết các file service để xử lý business
   - Thiết kế giao diện với thymeleaf, css, js
   - Viết các function trong js để call rest api, sau đó update lại home page
4. Các kỹ thuật cần dùng<br>
a. Làm việc với Java (tài liệu dịch đi kèm)  
b. Làm việc với Multipart file trong Spring  
- Upload file:
	- Làm việc với Rest Controller (tham khảo đến FileRestController trong project)
```sh
	@PostMapping("/api/files/" + FileService.DIR_NAME)
	public ResponseEntity<List<FileInfo>> postFile(@RequestParam MultipartFile[] files, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String dirPath = HttpSessionWrapper.getDirPath(session);
		try {
			List<FileInfo> fileInfos = fileService.saveAll(FileService.DIR_NAME, dirPath, files);
			log.debug("{} files saved", fileInfos.size());
			return new ResponseEntity<>(fileInfos, HttpStatus.CREATED);
		} catch (IllegalStateException e) {
			log.debug(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
```
Kiểu trả về tùy mục đích để viết, có thể chỉ đơn giản là message với result để thể hiện kết quả. Ở đây sử dụng FileInfo là 1 class với các thuộc tính đơn giản của file để hiển thị.Response trả về sẽ có body là dạng json chứa 1 list các fileinfo chứa thông tin các file được upload lên server.  
Argument khai báo của method: @RequestParam MultipartFile[] files. Khi khai báo như thế này, param "files" được gửi từ client sẽ được spring tự động đọc vào biến files. Lưu ý là spring support gửi nhiều file 1 lúc, nên ở đây dùng MultipartFile array (tương ứng với thuộc tính multiple <input type="file" name="files" multiple/>) (Xem project demo-form-upload để hiểu rõ hơn về upload 1 file và upload nhiều file)  
Khi đã lấy được file gửi từ client, việc làm gì với file này sẽ do tùy từng bài toán. Ví dụ ở đây sẽ lưu file này vào thư mục chỉ định trong storage trên server (tham khảo đến FileServiceImpl trong project)
```sh
	public List<FileInfo> saveAll(String rootDirName, String dirPath, MultipartFile[] mpFiles) {
		List<FileInfo> fileInfos = new ArrayList<>();

		try {
			Path realDirPath = FileManagerUtils.getRealAbsoluteFilePath(rootLocation, rootDirName, dirPath);
			// Creates a new directory and parent directories that do not exist to upload
			// file into.
			Files.createDirectories(realDirPath);

			for (MultipartFile mpFile : mpFiles) {
				if (mpFile == null || mpFile.isEmpty() || FileManagerUtils.isUnSafe(mpFile.getOriginalFilename())) {
					continue;
				}
				FileInfo fileInfo = findOne(rootDirName, dirPath, mpFile.getOriginalFilename());
				if (fileInfo != null) {
					continue;
				}
				try {
					File file = FileManagerUtils.save(mpFile, realDirPath);
					fileInfo = FileManagerUtils.getFileInfo(rootDirName, dirPath, file);
					fileInfos.add(fileInfo);
				} catch (IOException e) {
					log.error("Fail to upload file.", e);
					throw new FileManagerException("Failed to upload file " + mpFile.getOriginalFilename(), e);
				}
			}
			return fileInfos;
		} catch (IOException e) {
			log.error("Fail to upload file.", e);
			throw new FileManagerException("Failed to upload files", e);
		}
	}
```
Có thể hiểu đơn giản, việc lưu file đã upload lên server là copy nội dung của file được upload và ghi vào một file trên hệ thống với đường dẫn được cung cấp và tên file mới này trùng với tên file được upload. Nếu đường dẫn này chưa được tạo thư mục trên server, thì sẽ tạo một thư mục mới(và tất cả các thư mục cha của nó nếu chưa được tạo). Method ở trên trả về info cho các file đã được upload thành công.

Về phía client, trong project sử dụng jquery ajax để gửi request và nhận response đến RestController trên. Ajax (Asynchronous JavaScript and XML) hỗ trợ việc xử lý request không đồng bộ, nghĩa là gửi và nhận data từ phía server mà không cần load lại cả page.
```sh
	<form id="upload-form" action="/" method="post" enctype="multipart/form-data">
		<div class="form-group upload-box">
			<input type="file" class="upload-box-input" id="file" name="files"
				data-multiple-caption="{count}" multiple>

			<label class="upload-box-label" for="file">Drag and drop files!</label>
		</div>
	</form>

	$('#upload-btn').click(function () {
		let uploadForm = document.getElementById('upload-form');
		let data = new FormData(uploadForm);

		$.ajax({
			method: "post",
			url: `api/files/${getRootDirName()}`,
			data,
			contentType: false,
			processData: false,
		}).done(function(data, textStatus, xhr) {
			if (xhr.status != 201) {
				alert("Can not upload file");
				return;
			}
			let fileInfos = data;
			appendFiles(fileInfos);
			$('#upload-form').children('div').children('input')[0].value = "";
		}).fail(function(xhr, status, error) {
			alert("Can not upload file. Error: " + status + xhr.responseText);
	    });
	});
```
Trên page html, form element có id là "upload-form" với enctype="multipart/form-data" được dùng để hỗ trợ user chọn 1 file hoặc nhiều từ client (máy tính) và upload lên server. Ở trong project này sẽ không submit form để load lại toàn bộ page, mà dùng ajax với data từ form này để gửi file đến server.
```sh
<form id="upload-form" action="/" method="post" enctype="multipart/form-data">
```
Đoạn javascript
```sh
$.ajax({
			method: "post",
			url: `api/files/${getRootDirName()}`,
			data,
			contentType: false,
			processData: false,
		}).done(function(data, textStatus, xhr) {
			if (xhr.status != 201) {
				alert("Can not upload file");
				return;
			}
			let fileInfos = data;
			appendFiles(fileInfos);
			$('#upload-form').children('div').children('input')[0].value = "";
		}).fail(function(xhr, status, error) {
```

được dùng để xử lý request bất đồng bộ: trong done() function sẽ xử lý dữ liệu json trả về từ server (FileRestController), trong fail() function sẽ xử lý error nếu nhận được error từ phía server.

-
	-  Làm việc với Spring MVC form upload (project demo-form-upload)  
Ở project này, làm việc với @Controller và Spring MVC để upload file theo 2 hình thức: upload 1 file và upload nhiều file từ form (submit form của html).
```sh
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        fileService.uploadFile(file);

        redirectAttributes.addFlashAttribute("message",
            "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @PostMapping("/uploadFiles")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes) {

        Arrays.asList(files)
            .stream()
            .forEach(file -> fileService.uploadFile(file));

        redirectAttributes.addFlashAttribute("message",
            "You successfully uploaded all files!");

        return "redirect:/";
    }
```
Tương ứng trên HTML
```sh
<form method="POST" th:action="@{/uploadFile}" enctype="multipart/form-data">
  <input type="file" name="file"/> <br/><br/>
  <button type="submit">Submit</button>
</form>

<h4>Upload Multiple Files:</h4>
<form method="POST" th:action="@{/uploadFiles}" enctype="multipart/form-data">
  <input type="file" name="files" multiple/> <br/><br/>
  <button type="submit">Submit</button>
</form>
```
Như ở trong code, nếu html input ko sử dụng thuộc tính multiple thì chỉ upload được 1 file, và trên Controller chỉ dùng MultipartFile file để lấy nội dung file được upload lên. Còn trường hợp multiple thì dùng array như đã nói ở trên.

Phía fileService sẽ copy nội dung file upload sang 1 file mới tạo trong thư mục được định nghĩa bởi thuộc tính: app.upload.dir. Nếu thuộc tính này ko được đinh nghĩa trong application.properties hay trong environment, thì sẽ sử dụng biến môi trường user.home làm giá trị mặc đinh (Ở window biến này trỏ vào C:\Users\user-name )
```sh
	@Value("${app.upload.dir:${user.home}}")
	public String uploadDir;
```
Ở đây sử dụng Files.copy với option là REPLACE_EXISTING dùng để replace file nếu nó đã có sẵn trên server (Hiểu đơn giản là tạo file mới và ghi đè lên file cùng tên nếu tồn tại rồi)
```sh
Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
```

-
	-  Hạn chế kích thước file
Khai báo trong application.properties các thuộc tinh sau để hạn chế kích thước file upload
```sh
spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=20MB
```
max-file-size là giới hạn kích cỡ của file được upload, còn max-request-size giới hạn kích cỡ của request được gửi đi  
Khi file được chọn từ client có kích cỡ hơn 5MB, thì spring sẽ trả ra exception. Để xử lý exception này và trả về những thông tin mình mong muốn, thì dùng 1 kỹ thuật quản lý exception hay sử dụng trong spring là @ControllerAdvice (@RestControllerAdvice) và @ExceptionHandler(MaxUploadSizeExceededException.class)  
 @ControllerAdvice: đánh dấu controller này là một global controller, nghĩa là nó xử lý chung cho tất cả các controller khác.  
 @ExceptionHandler: Được sử dụng để bắt các exception bắn ra từ tất cả các method khác ở trong Controller mà có class được khai báo trong cú pháp của nó
```sh
@ControllerAdvice
public class FileManagerControllerAdvice {
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<?> handleFileSizeLimitExceeded(MaxUploadSizeExceededException exc) {
	    return new ResponseEntity<>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

```
method handleFileSizeLimitExceeded sẽ bắt exception có class là MaxUploadSizeExceededException mà bắn ra từ các controller khác
Ngoài ra trong FileRestController các bạn cũng sẽ thấy có sử dụng:
```sh
	@ExceptionHandler(FileManagerFileNotFoundException.class)
	public ResponseEntity<?> handleFileManagerFileNotFoundException(FileManagerFileNotFoundException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(FileManagerException.class)
	public ResponseEntity<?> handleFileManagerException(FileManagerException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
```
Lúc này handleFileManagerFileNotFoundException method sẽ bắt các FileManagerFileNotFoundException mà bắn ra từ các method khác trong FileRestController class.

- Đọc nội dung file và download file từ server
Một cách đơn giản để trả về nội dung file cho việc download hoặc view là trả về đối tượng Resource của spring như trong ví dụ dưới đây
```sh
	@GetMapping("/api/files/" + FileService.DIR_NAME + "/{fileName}/fileContentDownload")
	public ResponseEntity<Resource> getFileContentToDownload(@PathVariable String fileName,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String dirPath = HttpSessionWrapper.getDirPath(session);
		Resource file = fileService.loadAsResource(FileService.DIR_NAME, dirPath, fileName);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
```
```sh
	@Override
	public Resource loadAsResource(String rootDirName, String dirPath, String fileName) {
		try {
			Path file = FileManagerUtils.getRealAbsoluteFilePath(rootLocation, rootDirName, dirPath, fileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new FileManagerFileNotFoundException(
						"Could not read file: " + fileName);
			}
		}
		catch (MalformedURLException e) {
			throw new FileManagerFileNotFoundException("Could not read file: " + fileName, e);
		}
	}
```
Phía controller, cần set thuộc tính cho header
.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
và add Resource đớ vào body để trả về 
.body(file);
Phía client, sử dụng js, html để gửi request và nhận response, tùy yêu cầu là download hay view(đọc) file mà xử lý khác nhau
```sh
				let downloadA = document.createElement("a");
				downloadA.href = 'api/files/' + encFilePath + '/fileContentDownload';
				downloadA.setAttribute('download', fileInfo.name);
				downloadA.innerHTML = '<i class="fas fa-download"></i>';
				
				downloadTd.appendChild(downloadA);
```
Đoạn js trên tạo ra 1 html element là <a> và add nó vào <td>, khi click vào link sẽ gửi request mà tương ứng với 
@GetMapping("/api/files/" + FileService.DIR_NAME + "/{fileName}/fileContentDownload")
```sh
				if ((/\.(gif|jpe?g|tiff?|png|webp|bmp)$/i).test(fileInfo.name)) {
			        $('<a>',{
			        	style: 'margin-left: 10px;',
			            href: '#',
			            click: function(){
							$('#viewFile').empty();
			            	let img = $('<img>');
			            	img.attr('src', downloadA.href);
	            			img.attr('width', 500);
	            			img.appendTo('#viewFile');
	            			return false;
			            }
			        }).html('<i class="fas fa-eye"></i>').appendTo(downloadTd);
				}
			}
```
Đoạn này sử dụng js với jquery, tạo element <a>, ở trong nó có 1 element là <img>, khi khai báo thuộc tính src cho img với giá trị là url tương ứng với việc đọc nội dung file trên controller(hoặc đường dẫn đến file nếu lưu file trong resources của app), nó sẽ load nội dung file trên server về.
