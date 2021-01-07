# Bài tập JPA số 1

Hãy tự xây dựng chức năng người dùng và đăng nhập. Cụ thể như sau


## Màn hình
1. Màn hình **Login** gồm email và password. Email chính là NaturalID đó. Màn hình này có 2 link: tạo mới tài khoản (Create Account) và tìm lại mật khẩu (Get New Password). Nếu login thành công thì cần hiển thị thông tin:
   - Full Name
   - Email
   - Danh sách các vài trò (roles) user đó được gán
   - Hyper link để update Full Name và Email
   - Hyper link để update Password
   - Lịch sử truy cập gồm có các sự kiện sắp xếp mới nhất đến cũ nhất
     - Login - time stamp
     - Update Full Name or Email - time stamp
     - Update password - time stamp
     - Retrieve password - time stamp
     - Create Account - time stamp
   
2. Màn hình **Create Account** gồm có các trường:
   - Họ và tên
   - Email
   - Password

3. Màn hình **Get New Password** chỉ cần một trường duy nhất là Email. Khi người dùng quên mật khẩu, anh ta cung cấp email, ứng dụng kiểm tra nếu email có trong bảng User thì sinh ra một password mới, băm rồi lưu vào bảng user đồng thời gửi email chưa password mới đến user. Bạn có thể lập trình chức năng gửi email nhưng để đơn giản trước mắt chỉ cần hiển thị password mới luôn ra màn hình.

4. Màn hình **Udate Role**. Chỉ có admin khi đăng nhập, thì có thêm link để tìm kiếm user theo email. Nếu tìm được thì update Role.
Hiện thị tất cả các role dạng check box. Tick vào checkbox nào thì user được gán role đó.
Màn hình này chỉ admin mới được truy cập. Anonymous user hay user khác vào là hiển thị cảnh báo Forbidden Access ngay.

5. Màn hình **Find User**. Màn hình này chỉ admin mới được truy cập. Anonymous user hay user khác vào là hiển thị cảnh báo Forbidden Access ngay.

## Phân quyền và dữ liệu ban đầu
Ứng dụng được khởi tạo với một user đầu tiên là admin, email: admin@techmaster.vn có role là admin, password mặc định là r@0T. Hãy dùng file sql  ```user.sql``` để nạp dữ liệu.

Danh sách các vai trò gồm có:
- admin
- customer
- developer
- sales
- operator
- trainer
Danh sách các vai trò có thể bổ xung trong tương lai. Ban đầu hãy tạo file ```role.sql``` để nạp những vai trò mặc định vào ứng dụng.

## Model trong Java hay bảng trong CSDL
Từ yêu cầu trên, ta thấy cần phải có những bảng sau đây:
1. user: lưu thông tin user(Full Name, Email, Password)
2. userevent: lưu các sự kiện user đăng nhập, cập nhật, lấy lại password...
3. role: lưu danh sách các vai trò

Chú ý với trường password. Để đảm bảo mật, hệ thống không được lưu plain password mà phải dùng thuật toán băm (hash algorithm) để tạo ra hashed password, không thể dịch ngược.
Khi login, ứng dụng sẽ băm password gửi đến và so sánh với hashed password được lưu trong bảng user.

Quan hệ user - role sẽ là quan hệ ManyToMany.

## Gợi ý hướng làm bài tập

Đừng bận tâm tạo giao diện. Hãy tập trung viết model, xây dựng repository, rồi viết unit test để kiểm tra các chức năng.

Nên tạo ra một ```@Service``` tên là ```AuthService``` để lộ ra các phương thức căn bản như:
- login
- createAccount
- updateAccount
- getPassword
- updatePassword
- getRoles
- updateRoles
- hashPassword
- isAdmin

Đừng lao vào làm những hàm khó, chọn hàm dễ làm trước. Chạy ok, mới làm hàm phức tạp.

Tất cả kiến thức các bạn đều đã được học rồi. Giờ xem lại các ví dụ mẫu rồi code thôi.
Lịch chấm bài trong 7 ngày kể từ hôm giao bài tập. Nộp muộn trừ 2 điểm.