# Định nghĩa interface Repository và viết JUnit test

Sau khi định nghĩa Model xong, chúng ta tiếp tục:
1. Khai báo các Repository Interface kế thừa ```JpaRepository```
2. Bổ xung JPQL Query hoặc Native Query cần thiết
3. Tạo JUnit5 + AssertJ để kiểm thử các thao tạo Create - Edit - Delete, nhớ cover cả trường hợp ngoại lệ