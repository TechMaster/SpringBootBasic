package vn.techmaster.simpleauthen.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {
    /*
     * Do UserDetails mở rộng interface Serializable. Bản chất của Serializable
     * object là chuyển đổi đối tượng thành dữ liệu có thể truyền qua mạng hoặc lưu
     * vào ổ cứng, rồi lại khôi phục lại. Cần có mã số phiên bản để xuất ra
     * (serialize) và khôi phục (deserialize) đúng cấu trúc, giá trị thuộc tính đối
     * tượng ban đầu Ý nghĩa của việc sử dụng serialVersionUID dùng để
     * https://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why
     * -should-i-use-it
     * 
     */
    private static final long serialVersionUID = -726614191956956687L;    
    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setAuthority(String ... stringAuthorities) {
        for (String authority : stringAuthorities) {
            //this.authorities.add(new Authority(authority)); cách này cũng được
            this.authorities.add(() -> authority);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
