package boundary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecase.reset_game.ResetService;

import static org.mockito.Mockito.*;

public class ResetInterpreterTest {
    ResetService resetService = mock(ResetService.class);
    Interpreter nextInterpeter = mock(Interpreter.class);
    ResetInterpeter resetInterpreter;

    @BeforeEach
    void setUp() {
        resetInterpreter = new ResetInterpeter(resetService, nextInterpeter);
    }

    @Test
    void invokes_reset_service_when_command_line_starts_with_reset() {
        resetInterpreter.acceptCommand("reset xxx");

        verify(resetService).doReset();
    }

    @Test
    void invokes_stop_service_when_command_line_starts_with_stop() {
        resetInterpreter.acceptCommand("stop xxx");

        verify(resetService).doStop();
    }

    @Test
    void invokes_next_interpreter_in_other_cases() {
        resetInterpreter.acceptCommand("xxx");

        verify(nextInterpeter).acceptCommand("xxx");
        verifyNoInteractions(resetService);
    }
}
