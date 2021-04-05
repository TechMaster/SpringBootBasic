# Bài thực hành số 1, môn Java Core

Đây là những nội dung các bạn đã được học:

1. Biến và kiểu dữ liệu
2. Nhập xuất trong java
3. Cấu trúc điều khiển
4. Mảng
5. Lớp và đối tượng
6. Tính đóng gói
7. Access Modifiers
8. Tính kế thừa
9. Tính đa hình
10. Nạp chồng, ghi đè
11. Upcasting & downcasting
12. Tính trừu tượng
14. Interface
15. Dependency injection 
16. ArrayList
17. File Handling
18. Annotation
19. Exceptions

# 01: Kiểm tra một số nhập vào từ bàn phím có phải là số nguyên tố hay không?

Số nguyên tố là số chỉ chia hết cho chính nó và 1. Các đơn giản (chậm) nhất để kiểm tra một số N có phải là số nguyên tố hay không là tạo một vòng lặp từ có biến i chạy từ 2 đến N-1. Lấy N chia cho i. Nếu chia hết thì thoát vòng lặp kết luận đây không phải số nguyên tố.

**Yêu cầu: **

Hãy tạo một vòng lặp để nhập các số từ bàn phím.
Nếu nhập ký tự 'X' hay 'Q' thì thoát khỏi ứng dụng.
Nếu nhập các chuỗi ký tự không phải là số thì in ra màn hình ```'XYZ' không phải là số```
Nếu nhập số nguyên tố thì in ra màn hình ```17 là số nguyên tố```
Ngược lại in ra màn hình ```18 không phải là số nguyên tố```

*Làm đủ chức năng trên được 1 điểm*

Ghi lại vào file nguyento.txt những gì đã in ra màn hình console. (1 điểm nữa)

# 02: Xây dựng đội hình thi đấu của đội Barcelona

2021 đội Barcelona gồm có các cầu thủ sau đây

1. Marc-André ter Stegen - GK
2. Sergiño Dest - DF
3. Gerard Piqué - DF
4. Ronald Araújo - DF
5. Sergio Busquets - MF
6. Antoine Griezmann - FW
7. Miralem Pjanić - MF
8. Martin Braithwaite - FW
9. Lionel Messi - FW
10. Ousmane Dembélé - FW
11. Riqui Puig - MF
12. Neto - GK
13. Clément Lenglet - DF
14. Pedri - MF
15. Francisco Trincão - FW 
16. Jordi Alba - DF 
17. Matheus Fernandes - MF 
18. Sergi Roberto - DF
19. Frenkie de Jong - MF
20. Ansu Fati - FW
21. Samuel Umtiti - DF
22. Junior Firpo - DF


**Yêu cầu 1 (1 điểm)**: Hãy tạo một 2 class: Play và Team
Một class là Player gồm 3 trường: 
1. Full Name kiểu String
2. Position kiểu Enum {GK, DF, MF, FW}
3. Num kiểu Integer, số áo từ 1 đến 22
(1 điểm)

Nếu bạn chưa học kiểu Enum thì tham khảo ở đây [Java Enum](https://www.w3schools.com/java/java_enums.asp)
Giải thích về Position (vị trí trên sân của cầu thủ):
- GK: goal keeper, thủ môn
- DF: defender, hậu vệ
- MF: mid field, trung vệ
- FW: forwarder, tiền đạo

Đội hình ra sân luôn giới hạn 11 cầu thủ, trong đó chắc chắn chỉ có 1 thủ môn (GK), 4 hậu vệ (DF), 4 trung vệ (MF), 2 tiền đạo (FW)

**Yêu cầu 2 (3 điểm)**: Hãy xây dựng một class Team có mảng chứa tất cả các cầu thủ nói trên và một phương thức buildTeam trả về danh sách 11 cầu thủ

```java
List<Player> buildTeam()
```

Hãy làm sao 11 cầu thủ trả về của phương thức buildTeam phải có đủ 1 thủ môn (GK), 4 hậu vệ (DF), 4 trung vệ (MF), 2 tiền đạo (FW). Chú ý có thể chọn ngẫu nhiên. Sau đó in ra màn hình danh sách ra sân như sau:
```
1 - Marc-André ter Stegen - GK

3 - Gerard Piqué - DF
16 - Jordi Alba - DF 
18 - Sergi Roberto - DF
21 - Samuel Umtiti - DF

5 - Sergio Busquets - MF
19 - Frenkie de Jong - MF
7 - Miralem Pjanić - MF
11- Riqui Puig - MF

6 - Antoine Griezmann - FW
9 - Lionel Messi - FW
```

Sau đó lại đặt câu hỏi "Do you want to see another option? Type Y or N"

Nếu người dùng gõ "Y' tiếp tục tạo ra một đội hình ngẫu nhiên mới theo sơ đồ 4-4-2
Nếu người dùng gõ 'N' thì thoát.


**Yêu cầu 3 (1 điểm)** Xây dựng đội hình ra sân theo các chiến thuật khác nhau.
Trong bóng đá có mấy sơ đồ chiến thuật phổ biến:
- 4-4-2: 4 hậu vệ - 4 trung vệ - 2 tiền đạo
- 3-5-2: 3 hậu vệ - 5 trung vệ - 2 tiền đạo
- 4-3-3: 4 hậu vệ - 3 trung vệ - 3 tiền đạo

Hãy tạo ra hàm buildTeam có 3 tham số đầu vào, rồi sinh ngẫu nhiên danh sách 11 cầu thủ ra sân có số cầu thủ theo các vị trí bằng với 3 tham số đầu vào 

```java
List<Player> buildTeam(int defender, int midfielder, int forwarder) {

}
```