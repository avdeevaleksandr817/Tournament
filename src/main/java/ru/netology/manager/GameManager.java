package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.data.PlayerData;
import ru.netology.exception.NotRegisteredException;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GameManager {
    //хранить всех зарегистрированных игроков в поле класса Game в виде списка
    private List<PlayerData> players = new ArrayList<>();
    //метод регистрации игрока
    public boolean register(PlayerData player) {
        return players.add(player);
    }
    //метод соревнования между двумя игроками
    public int round(String playerName1, String playerName2) {
        int indexOne = indexFound(playerName1);
        int indexTwo = indexFound(playerName2);
        if (indexOne == -1) {
            throw new NotRegisteredException("Первый игрок не зарегистрирован для участия в турнире!");
        } else if (indexTwo == -1) {
            throw new NotRegisteredException("Второй игрок не зарегистрирован для участия в турнире!");
        }
        if (players.get(indexOne).getStrength() > players.get(indexTwo).getStrength()) {
            return 1;
        } else if (players.get(indexOne).getStrength() < players.get(indexTwo).getStrength()) {
            return 2;
        } else {
            return 0;
        }
    }

    public int indexFound(String playerName) {
        for (PlayerData player : players) {
            if (player.getName().equalsIgnoreCase(playerName)) {
                return players.indexOf(player);
            }
        }
        return -1;
    }
}
