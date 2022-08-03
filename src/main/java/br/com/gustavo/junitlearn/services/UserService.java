package br.com.gustavo.junitlearn.services;

import br.com.gustavo.junitlearn.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO findById(Integer id);
    List<UserDTO> findAll();
    UserDTO create(UserDTO userDTO);
    void delete(Integer id);
}
