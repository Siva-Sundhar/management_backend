package com.cloud9.Management.task_requirement.controller;

import com.cloud9.Management.task_requirement.dto.request.RequirementRequest;
import com.cloud9.Management.task_requirement.dto.response.DayBookResponse;
import com.cloud9.Management.task_requirement.dto.response.RequirementResponse;
import com.cloud9.Management.task_requirement.dto.response.RequirementResponseWithoutItems;
import com.cloud9.Management.task_requirement.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskRequirementController {


    private final TaskService taskService;

    @PostMapping("/save")
    public ResponseEntity<?> saveTask(@RequestBody RequirementRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.saveTask(request));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<RequirementResponse>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("get-task/{id}")
    public ResponseEntity<RequirementResponse> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.taskById(id));
    }

    @GetMapping("/day_book")
    public ResponseEntity<List<RequirementResponseWithoutItems>> getDayBookData(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        List<RequirementResponseWithoutItems> tasks = taskService.getAllTasksByDate(date);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/day_book_flat")
    public ResponseEntity<List<DayBookResponse>> getDayBook(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        List<DayBookResponse> tasks = taskService.dayBookFlatList(date);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody RequirementRequest request) {

        log.info("updateTask: id={}, request={}", id, request);
        taskService.updateTask(id, request);
        return ResponseEntity.ok().build();
    }


}


//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .csrf(csrf -> csrf.disable()) // Disable CSRF for REST APIs if needed
//                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
//
//        return http.build();
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:5173"));
//        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(List.of("*"));
//        configuration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//}