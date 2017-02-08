package net.liavontsibrechka.spittr.web;

import net.liavontsibrechka.spittr.Spitter;
import net.liavontsibrechka.spittr.data.SpitterRepository;
import net.liavontsibrechka.spittr.web.controller.SpitterController;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class SpitterControllerTest {
    @Test
    public void shouldShowRegistrationForm() throws Exception {
        SpitterRepository spitterRepository = Mockito.mock(SpitterRepository.class);
        SpitterController spitterController = new SpitterController(spitterRepository);
        MockMvc mockMvc = standaloneSetup(spitterController).build();

        mockMvc.perform(get("/spitter/register"))
                .andExpect(view().name("registerForm"));
    }

    @Test
    public void shouldProcessRegistration() throws Exception {
        SpitterRepository spitterRepository = Mockito.mock(SpitterRepository.class);
        Spitter unsaved = new Spitter("leontibrechko", "password", "Leonti", "Brechko");
        Spitter saved = new Spitter(23L, "leontibrechko", "password", "Leonti", "Brechko");
        Mockito.when(spitterRepository.save(unsaved)).thenReturn(saved);

        SpitterController spitterController = new SpitterController(spitterRepository);
        MockMvc mockMvc = standaloneSetup(spitterController).build();

        mockMvc.perform(post("/spitter/register")
                .param("firstName", "Leonti")
                .param("lastName", "Brechko")
                .param("username", "leontibrechko")
                .param("password", "password"))
                .andExpect(redirectedUrl("/spitter/leontibrechko"));

        Mockito.verify(spitterRepository, Mockito.atLeastOnce()).save(unsaved);
    }
}
