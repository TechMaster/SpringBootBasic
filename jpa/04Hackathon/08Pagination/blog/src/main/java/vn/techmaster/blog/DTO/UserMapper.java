package vn.techmaster.blog.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import vn.techmaster.blog.model.User;
//Đây là interface để chuyển đổi dữ liệu từ đối tượng kiểu A sang đối tượng kiểu B
@Mapper
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
  UserInfo userToUserInfo(User user);
  User userInfoToUser(UserInfo userInfo);
}
