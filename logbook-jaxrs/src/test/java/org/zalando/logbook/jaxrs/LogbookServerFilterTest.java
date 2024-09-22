package org.zalando.logbook.jaxrs;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.zalando.logbook.Logbook;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

final class LogbookServerFilterTest {

    private final ContainerRequestContext request = mock(ContainerRequestContext.class);
    private final ContainerResponseContext response = mock(ContainerResponseContext.class);
    private final Logbook logbook = mock(Logbook.class);

    private final ContainerResponseFilter unit = new LogbookServerFilter(logbook);

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void filterShouldDoNothingIfCorrelatorIsNotPresent(boolean hasEntity) throws IOException {
        when(response.hasEntity()).thenReturn(hasEntity);
        unit.filter(request, response);
        verifyNoInteractions(logbook);
    }

}
