package com.game.controller;

import java.util.List;

import com.game.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.exception.PlayerNotFoundException;
import com.game.service.PlayerService;
import com.game.exception.PlayerBadRequestException;


@RestController
public class PlayerController {

    private final PlayerService service;

    @Autowired
    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping("/rest/players")
    public @ResponseBody List<Player> getPlayersList(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "title", required = false) String title
            , @RequestParam(value = "race", required = false) Race race, @RequestParam(value = "profession", required = false) Profession profession
            , @RequestParam(value = "after", required = false) Long after, @RequestParam(value = "before", required = false) Long before
            , @RequestParam(value = "banned", required = false) Boolean banned, @RequestParam(value = "minExperience", required = false) Integer minExperience
            , @RequestParam(value = "maxExperience", required = false) Integer maxExperience, @RequestParam(value = "minLevel", required = false) Integer minLevel
            , @RequestParam(value = "maxLevel", required = false) Integer maxLevel, @RequestParam(value = "order", required = false) PlayerOrder order
            , @RequestParam(value = "pageNumber", required = false) Integer pageNumber, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return service.getPlayers(name, title, race, profession, after, before, banned, minExperience, maxExperience, minLevel, maxLevel, order, pageNumber, pageSize);
    }

    @GetMapping("/rest/players/count")
    public @ResponseBody int getPlayersCount(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "title", required = false) String title
            , @RequestParam(value = "race", required = false) Race race, @RequestParam(value = "profession", required = false) Profession profession
            , @RequestParam(value = "after", required = false) Long after, @RequestParam(value = "before", required = false) Long before
            , @RequestParam(value = "banned", required = false) Boolean banned, @RequestParam(value = "minExperience", required = false) Integer minExperience
            , @RequestParam(value = "maxExperience", required = false) Integer maxExperience, @RequestParam(value = "minLevel", required = false) Integer minLevel
            , @RequestParam(value = "maxLevel", required = false) Integer maxLevel) {
        return service.getCount(name, title, race, profession, after, before, banned, minExperience, maxExperience, minLevel, maxLevel);
    }

    @PostMapping("/rest/players")
    public @ResponseBody Player createPlayer(@RequestBody Player player) {
        Player createdPlayer;
        try {
            createdPlayer = service.create(player);
        }
        catch (PlayerBadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return createdPlayer;
    }

    @GetMapping("/rest/players/{id}")
    public @ResponseBody Player getPlayer(@PathVariable("id") Long id) {
        Player player;
        try {
            player = service.getPlayerById(id);
        }
        catch (PlayerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        catch (PlayerBadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return player;
    }

    @PostMapping("/rest/players/{id}")
    public @ResponseBody Player updatePlayer(@PathVariable("id") Long id, @RequestBody Player player) {
        Player updatedPlayer;
        try {
            updatedPlayer = service.update(player, id);
        }
        catch (PlayerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        catch (PlayerBadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return updatedPlayer;
    }

    @DeleteMapping("/rest/players/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePlayer(@PathVariable("id") Long id) {
        try {
            service.delete(id);
        }
        catch (PlayerNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        catch (PlayerBadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}