package de.dhbw.cm.application;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LoginViewTest {
    @Mock
    private ConsoleReader cr;

    @Mock
    private ConsoleWriter cw;

    private LoginView loginView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        loginView = new LoginView(cw, cr);
    }


}
