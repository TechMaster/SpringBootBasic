# Gợi ý các bước tiến hành

1. Phân tích nghiệp vụ yêu cầu
2. Tìm nguồn dữ liệu, lấy dữ liệu về, xử lý thô
3. Nạp dữ liệu vào ```@Service``` component



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
