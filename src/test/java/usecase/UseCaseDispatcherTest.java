package usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UseCaseDispatcherTest {
    UseCaseDispatcher dispatcher;
    UseCase useCaseOne = mock(UseCase.class);
    UseCase useCaseTwo = mock(UseCase.class);

    @BeforeEach
    void setUp() {
        Map<String, UseCase> map = new HashMap<>();
        map.put("one", useCaseOne);
        map.put("two", useCaseTwo);

        dispatcher = new UseCaseDispatcher(map, null, null);
    }

    @Test
    void invokes_the_use_case_matching_the_first_token_of_commmand_line() {
        dispatcher.acceptCommand("one xxx yyy");
        verify(useCaseOne).acceptCommand("one xxx yyy");

        dispatcher.acceptCommand("two xxx yyy");
        verify(useCaseTwo).acceptCommand("two xxx yyy");
    }

    @Test
    void raise_an_exception_when_command_is_not_recognized() {
        assertThrows(Exception.class,
                () -> dispatcher.acceptCommand("unknown xxx yyy"));
    }
}