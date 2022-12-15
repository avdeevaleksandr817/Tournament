package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.PlayerData;
import ru.netology.exception.NotRegisteredException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameManagerTest {
    //подготовка данных
    GameManager manager = new GameManager();
    PlayerData player1 = new PlayerData(1, "player1", 100);
    PlayerData player2 = new PlayerData(2, "player2", 90);
    PlayerData player3 = new PlayerData(3, "player3", 90);

    //выполнить перед всеми тестами
    @BeforeEach
    void setup() {
        manager.register(player1);
        manager.register(player2);
        manager.register(player3);
    }

    @Test
    @DisplayName("Победа первого игрока")
    void shouldFirstPlayerWin() {
        assertEquals(1, manager.round("player1", "player2"));
    }

    @Test
    @DisplayName("Победа второго игрока")
    void shouldSecondPlayerWin() {
        assertEquals(2, manager.round("player3", "player1"));
    }

    @Test
    @DisplayName("Ничья")
    void shouldDraw() {
        assertEquals(0, manager.round("player3", "player2"));
    }

    @Test
    @DisplayName("Второй игрок не зарегистрирован для участия в турнире!")
    void shouldThrowNotRegisteredExceptionOne() {
        assertThrows(NotRegisteredException.class, () -> {
            manager.round("player1", "player4");
        });
    }

    @Test
    @DisplayName("Первый игрок не зарегистрирован для участия в турнире!")
    void shouldThrowNotRegisteredExceptionTwo() {
        assertThrows(NotRegisteredException.class, () -> {
            manager.round("player4", "player1");
        });
    }
}