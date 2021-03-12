# Hackathon số 2


Thời gian: ngày 13/3/2021, 8:00 đến 6:30. Muộn nhất 9:30 sáng sinh viên cần có mặt
Địa điểm: phòng lab số 1, tầng 12A, 48 Tố Hữu
Quy định:
1. Sinh viên làm hackathon độc lập không theo nhóm.
2. Giữ yên lặng trong phòng lab. Cần trao đổi, ra hành lang hoặc nhắn tin trong group riêng.
3. Sinh viên có thể bắt đầu lập trình ngay kể từ lúc nhận đề bài này.


## Đề bài

Dựa vào code [jpa/04Hackathon/08Pagination/blog](https://github.com/TechMaster/SpringBootBasic/tree/main/jpa/04Hackathon/08Pagination/blog) hãy áp dụng Spring Security để đáp ứng những yêu cầu sau đây:

1. Có ba role chính: AUTHOR, EDITOR, ADMIN
2. AUTHOR viết, sửa, xoá bài của chính mình. Được comment bài của người khác.
3. EDITOR như AUTHOR, nhưng được phép sửa, xoá bài bất kỳ ai.
4. ADMIN quyền như EDITOR được phép nâng cấp người dùng từ role AUTHOR lên EDITOR.
hoặc giáng quyền.

Lập trình các màn hình sau:

1. Đăng ký mới user (không cần xác nhận lại bằng email)
2. Trang dành cho role Admin: Liệt kê danh sách các user để nâng cấp quyền hoặc hạ quyền.
3. Bonus (làm được thì tốt + điểm): trang để EDITOR và ADMIN liệt kê các bài viết mới (tác giả | tiêu đề | ngày giờ viết). Link để mở bài viết ra để sửa.