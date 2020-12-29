# Gợi ý các bước tiến hành

1. Phân tích nghiệp vụ
2. Tìm nguồn dữ liệu, lấy dữ liệu về, xử lý thô
3. Định nghĩa cấu trúc dữ liệu phù hợp
4. Nạp dữ liệu vào ```@Service``` component



## 1. Phân tích nghiệp vụ
Tỷ giá ngoại tệ luôn biến động và được cập nhật theo ngày. Ứng dụng cần phải lấy dữ liệu từ một nguồn có sẵn nào đó. Vậy đi tìm nó!

Đây là một dịch vụ cung cấp tỷ giá các đồng tiền với 1 dollar Mỹ. Dùng miễn phí cho 2000 lần gọi.
https://app.exchangerate-api.com/dashboard
Your API Key: da65c8cd73399287d4171fed
Example Request: https://v6.exchangerate-api.com/v6/da65c8cd73399287d4171fed/latest/USD

```java
// Setting URL
String url_str = "https://v6.exchangerate-api.com/v6/YOUR-API-KEY/latest/USD";

// Making Request
URL url = new URL(url_str);
HttpURLConnection request = (HttpURLConnection) url.openConnection();
request.connect();

// Convert to JSON
JsonParser jp = new JsonParser();
JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
JsonObject jsonobj = root.getAsJsonObject();

// Accessing object
String req_result = jsonobj.get("result").getAsString();
```

**Có 3 hướng xử lý:**
1. Mỗi lần có request từ phía người dùng thì gián tiếp gọi vào dịch vụ exchangerate-api.com để lấy dữ liệu mới nhất. Cách này hạ sách vì quá tốn kém. Nếu dịch vụ exchangerate-api.com tạm ngưng hoạt động thì chức năng money converter của chúng ta cũng die theo ! Ưu điểm duy nhất là luôn có dữ liệu cập nhật nhất.
2. Định thời, mỗi ngày 1 hoặc 2 lần gọi đến exchangerate-api.com sao lưu vào bộ nhớ tạm thời. Khi khách hàng dùng money converter chúng ta chỉ dùng lại dữ liệu đã được cache sẵn. Cách này tối ưu nhất nhưng chúng ta sẽ phải học lập trình định thời theo thời gian để tạo cuộc gọi.
3. Gọi một lần duy nhất, lấy file json về, chỉ lấy phần dữ liệu cần thiết để lập trình chức năng Money Converter phiên bản 1.0. Đây là cách các bạn nên bắt đầu làm để có kết quả ngay, nộp bài cho nhà tuyển dụng trong 48 tiếng.

