package br.com.gustavo.junitlearn.resources;

import br.com.gustavo.junitlearn.dto.UserDTO;
import br.com.gustavo.junitlearn.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserResource {

    private UserService userService;

    @GetMapping("/{id}}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer userId) {
        return ResponseEntity.ok().body(userService.findById(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }
}
