package com.game.service;

import java.util.List;

import com.game.controller.PlayerOrder;

import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.entity.Player;
import com.game.exception.PlayerNotFoundException;
import com.game.exception.PlayerBadRequestException;

public interface PlayerService {
    List<Player> getPlayers(String name, String title, Race race, Profession profession
            , Long after, Long before, Boolean banned, Integer minExperience
            , Integer maxExperience, Integer minLevel, Integer maxLevel, PlayerOrder order
            , Integer pageNumber, Integer pageSize);
    int getCount(String name, String title, Race race, Profession profession
            , Long after, Long before, Boolean banned, Integer minExperience
            , Integer maxExperience, Integer minLevel, Integer maxLevel);
    Player create(Player player) throws PlayerBadRequestException;
    Player getPlayerById(Long id) throws PlayerNotFoundException, PlayerBadRequestException;
    Player update(Player player, Long id) throws PlayerBadRequestException, PlayerNotFoundException;
    void delete(long id) throws PlayerNotFoundException, PlayerBadRequestException;
}