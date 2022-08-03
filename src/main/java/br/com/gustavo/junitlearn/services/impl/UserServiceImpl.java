package br.com.gustavo.junitlearn.services.impl;

import br.com.gustavo.junitlearn.domain.User;
import br.com.gustavo.junitlearn.dto.UserDTO;
import br.com.gustavo.junitlearn.repositories.UserRepository;
import br.com.gustavo.junitlearn.services.UserService;
import br.com.gustavo.junitlearn.services.exceptions.DataIntegratyViolationException;
import br.com.gustavo.junitlearn.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Transactional(readOnly = true)
    public UserDTO findById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return mapper.map(user, UserDTO.class);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDTO create(UserDTO userDTO) {
        checkDuplicateEmail(userDTO);
        User user = userRepository.save(mapper.map(userDTO, User.class));
        return mapper.map(user, UserDTO.class);
    }

    @Transactional
    public void delete(Integer id) {
        findById(id);
        userRepository.deleteById(id);
    }

    private void checkDuplicateEmail(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());
        if(Objects.nonNull(user) && !user.getId().equals(userDTO.getId())) {
            throw new DataIntegratyViolationException("E-mail já cadastrado no sistema");
        }
    }
}
