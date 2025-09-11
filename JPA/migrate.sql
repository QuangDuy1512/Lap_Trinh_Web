-- Thêm cột userId (manager id) vào bảng categories nếu chưa có
ALTER TABLE categories ADD COLUMN userId INT;

-- Thiết lập khóa ngoại tới bảng users
ALTER TABLE categories ADD CONSTRAINT fk_user FOREIGN KEY (userId) REFERENCES users(id);

-- Cập nhật dữ liệu mẫu: Gán userId=1 cho các bản ghi cũ (bạn có thể thay đổi giá trị này cho phù hợp)
UPDATE categories SET userId = 1 WHERE userId IS NULL;

-- Tạo bảng manager_user để map manager với các user mà họ quản lý
CREATE TABLE manager_user (
  manager_id INT,
  user_id INT,
  PRIMARY KEY (manager_id, user_id),
  FOREIGN KEY (manager_id) REFERENCES users(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Ví dụ dữ liệu mẫu:
-- INSERT INTO manager_user (manager_id, user_id) VALUES (2, 1), (2, 2), (3, 3), (3, 4);