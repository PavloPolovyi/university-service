package com.example.universityservice.service.impl;

import com.example.universityservice.model.Lector;
import com.example.universityservice.repository.LectorRepository;
import com.example.universityservice.service.LectorService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LectorServiceImpl implements LectorService {
    private static final String RESULT_DELIMITER = ", ";
    private final LectorRepository lectorRepository;

    @Override
    public String findByNameLike(String pattern) {
        return lectorRepository.findByNameIgnoreCaseContaining(pattern).stream()
                .map(Lector::getName)
                .collect(Collectors.joining(RESULT_DELIMITER));
    }
}
