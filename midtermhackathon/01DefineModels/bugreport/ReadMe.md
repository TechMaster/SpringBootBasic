# Định nghĩa Entity Model
Chỉ cần hoàn thành phần này đã được 2 điểm.
Thời gian làm từ 60 - 90 phút.

- Bước 1: Khi bắt đầu Hackathon là vẽ các  màn hình ra giấy nháp. Vẽ tất cả các trường quan trọng ra để hình dung các bảng tương ứng chứa dữ liệu.

- Bước 2: Định nghĩa các model. Hãy mở một dự án mẫu có những quan hệ 1-nhiều, và nhiều - nhiều để copy cho nhanh..
Cần mô tả bằng lời các quan hệ giữa các model:
  1. Một User tạo ra Nhiều Bug
  2. Một User (role = Customer) có thể comment Bug của chính mình. User (role = OPERATOR) có thể comment bất kỳ bug nào
  3. Một Bug có thể thêm hoặc xoá Photo. Số lượng Photo cho mỗi Bug được giới hạn bởi PHOTO_LIMIT
  4. Một Bug có thể thêm hoặc xoá Comment
  5. Trạng thái của Bug chuyển từ New -> Fixed, hoặc từ New -> Forward. Forward tương đương với Escalate.

- Bước 3: Hãy bổ xung một [BugException](src/main/java/vn/techmaster/bugreport/model/BugException.java) để bắt những ngoại lệ ví dụ như:
  - Số lượng photo gắn vào 1 bug vượt quá số PHOTO_LIMIT
  - Một người tuỳ tiện comment vào một bug không phải do mình tạo ra. Trong khuôn khổ ứng dụng này, quy luật đặt ra là vậy.

## Cân nhắc giữa các lựa chọn lập trình

1. Có thể tạo bảng Role? Thời gian rất ngắn nên tạo bằng Role có thể thêm công việc, khiến bạn không còn thời gian hoàn thành Hackathon.
2. Có nên tạo quan hệ 1 - Nhiều giữa Bug và Photo? Tuỳ vào sức lập trình của bạn. Nếu ít kinh nghiệm, chỉ cần tạo một trường Photod duy nhất trong Bug. Chúng ta giải quyết được 1 phần yêu cầu upload ảnh, view ảnh. Điểm có thể còn cao hơn bạn tạo ra quan hệ 1 Bug - Nhiều Photo nhưng không hoàn thành demo được ứng dụng.

Hackathon là nơi rèn luyện kỹ năng code thực dụng. Khách hàng luôn tạo sức ép, phải xong ngay trong tuần này, thêm cho tôi chức năng A, chức năng B, nhưng chỉ là một xíu thôi, đâu có vất vả gì. Bạn gần như không thể không chấp nhận yêu cầu thay đổi nhỏ nhỏ của khách hàng. Đặc biệt là khách hàng Nhật.

Mà trong văn hoá phát triển Agile thì việc điều chỉnh ứng dụng theo yêu cầu của khách hàng cũng là một tiêu chí phải làm. Phần mềm hữu dụng tại thời điểm nó ra mắt, chứ không phải phần mềm tuân thủ đúng thiết kế trước đó 3 tháng.

## Có cần phải nắn nót ngay trong phần định nghĩa Model
Nếu bạn code nhiều, có kinh nghiệm thiết kế CSDL tốt, thì tại bước này, bạn cần hoàn thành 85-90% công việc thiết kế Model. Còn nếu bạn mới vào nghề, hãy cố gắng đạt khoảng 70% yêu cầu. Các bước sau sẽ điều chỉnh, bổ xung tiếp. Không thể cầu toàn quá. Cầu toàn là hết giờ !