package app.service;

import app.dto.MovieDTO;
import app.service.external.TMDBService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MovieService {

    // Attributes
    private final TMDBService tmdbService;

    // _________________________________________________

    public MovieService(TMDBService tmdbService) {
        this.tmdbService = tmdbService;
    }

    // _________________________________________________

    public CompletableFuture<MovieDTO> getMovieById(int movieId) {
        return tmdbService.getMovieInfo(movieId);
    }

    // _________________________________________________

    public CompletableFuture<List<MovieDTO>> getMoviesByRating(double minRating, double maxRating, boolean highestFirst, int page) {
        return tmdbService.getMoviesByRating(minRating,maxRating,true, page);
    }

}