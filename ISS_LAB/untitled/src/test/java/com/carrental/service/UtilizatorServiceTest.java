package com.carrental.service;

import com.carrental.domain.Utilizator;
import com.carrental.repository.UtilizatorRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UtilizatorServiceTest {

    @Mock
    private UtilizatorRepo utilizatorRepository;

    @InjectMocks
    private UtilizatorService utilizatorService;

    @Test
    public void testLogin_Success() {
        // Given
        String email = "test@test.com";
        String parola = "testParola";
        Utilizator utilizator = new Utilizator() {};
        when(utilizatorRepository.findByEmailAndParola(email, parola)).thenReturn(utilizator);

        // When
        Utilizator result = utilizatorService.login(email, parola);

        // Then
        assertEquals(utilizator, result);
    }

    @Test
    public void testLogin_Failure() {
        // Given
        String email = "test@test.com";
        String parola = "wrongParola";
        when(utilizatorRepository.findByEmailAndParola(email, parola)).thenReturn(null);

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            utilizatorService.login(email, parola);
        });
    }
}
