package de.dhbw.cm.application;

import de.dhbw.cm.domain.User;
import de.dhbw.cm.domain.UserManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class LoginViewTest {
    @Mock
    private ConsoleReader cr;

    @Mock
    private ConsoleWriter cw;

    @Mock
    private UserManager um;

    private LoginView loginView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginView = spy(new LoginView(um, cw, cr));
    }

    @Test
    void testValidateInputOne() {
        // Arrange
        doNothing().when(loginView).login();
        when(loginView.cr.readLine()).thenReturn("1");

        // Act
        loginView.validateInput();

        // Assert
        verify(loginView).login();
    }

    @Test
    void testValidateInputTwo() {
        // Arrange
        doNothing().when(loginView).createAccount();
        when(loginView.cr.readLine()).thenReturn("2");

        // Act
        loginView.validateInput();

        // Assert
        verify(loginView).createAccount();
    }

    @Test
    void testValidateInputThree() {
        // Arrange
        when(loginView.cr.readLine()).thenReturn("3");

        // Act
        loginView.validateInput();

        // Assert
        verify(cw).write(AnsiCodes.YELLOW, "\nExiting...");
    }

    @Test
    void testValidateInputInvalidInput() {
        // Arrange
        when(loginView.cr.readLine()).thenReturn("false").thenReturn("3");

        // Act
        loginView.validateInput();

        // Assert
        verify(cw).write(AnsiCodes.RED, "\nInvalid choice. Please try again.\n");
        verify(cw).write(AnsiCodes.YELLOW, "\nExiting...");
    }

    @Test
    void testLogin() {
        // Arrange
        doNothing().when(loginView).performLogin();

        // Act
        loginView.login();

        // Assert
        verify(cw).clearConsole();
        verify(cw).write(AnsiCodes.CYAN, "Login\n");
        verify(loginView).performLogin();
    }

    @Test
    void testCreateAccount() {
        // Arrange
        doNothing().when(loginView).performCreateAccount();

        // Act
        loginView.createAccount();

        // Assert
        verify(cw).clearConsole();
        verify(cw).write(AnsiCodes.CYAN, "Create New Account\n");
        verify(loginView).performCreateAccount();
    }

    @Test
    void testPerformLoginGoBack() {
        // Arrange
        when(loginView.cr.readLine()).thenReturn("b");
        doNothing().when(loginView).show();

        // Act
        loginView.performLogin();

        // Assert
        verify(cw).writeInLine(any(), any());
    }

    @Test
    void testPerformLoginValid() {
        // Arrange
        when(loginView.cr.readLine()).thenReturn("username").thenReturn("password");
        when(um.loginUser(any(), any())).thenReturn(new User("username", "password"));
        OverviewView mockOverviewView = mock(OverviewView.class);
        doNothing().when(mockOverviewView).show();

        // Act
        loginView.performLogin();

        // Assert
        verify(cw, times(2)).writeInLine(any(), any());
    }

    @Test
    void testPerformLoginInvalid() {
        // Arrange
        when(loginView.cr.readLine()).thenReturn("username").thenReturn("password").thenReturn("b");
        when(um.loginUser(any(), any())).thenReturn(null);
        doNothing().when(loginView).show();

        // Act
        loginView.performLogin();

        // Assert
        verify(cw, times(3)).writeInLine(any(), any());
        verify(cw).write(any(), any());
        verify(loginView).show();
    }

    @Test
    void testPerformCreateAccountGoBack() {
        // Arrange
        when(loginView.cr.readLine()).thenReturn("b");
        doNothing().when(loginView).show();

        // Act
        loginView.performCreateAccount();

        // Assert
        verify(cw).writeInLine(any(), any());
    }

    @Test
    void testPerformCreateAccountInvalid() {
        // Arrange
        when(loginView.cr.readLine()).thenReturn("username").thenReturn("password").thenReturn("b");
        when(um.createUser(any(), any())).thenReturn(false);
        doNothing().when(loginView).show();

        // Act
        loginView.performCreateAccount();

        // Assert
        verify(cw, times(3)).writeInLine(any(), any());
        verify(cw).write(any(), any());
        verify(loginView).show();
    }

    @Test
    void testPerformCreateAccountValid() {
        // Arrange
        when(loginView.cr.readLine()).thenReturn("username").thenReturn("password").thenReturn("b");
        when(um.createUser(any(), any())).thenReturn(true);
        doNothing().when(loginView).show();

        // Act
        loginView.performCreateAccount();

        // Assert
        verify(cw, times(2)).writeInLine(any(), any());
        verify(cw).write(any(), any());
        verify(loginView).show();
    }

}
