package app.service.external;

import app.config.PoolConfig;
import app.dto.MovieDTO;
import app.dto.MovieWrapperDTO;
import app.exception.ApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class TMDBService {

    // Attributes
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());;
    private final String apiKey = System.getenv("API_KEY");

    // ______________________________________________________

    public CompletableFuture<MovieDTO> getMovieInfo(int movieId) {

        // Initial
        String url = String.format(
                "https://api.themoviedb.org/3/movie/%d?api_key=%s&language=en-US",
                movieId, apiKey
        );

        // Request
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // Async call
        return PoolConfig.getClient()
                .sendAsync(httpRequest, BodyHandlers.ofString())
                .thenApply(response -> {

                    // Status validation
                    if (response.statusCode() != 200) {
                        throw new ApiException(response.statusCode(), "API returned status: " + response.statusCode());
                    }

                    // Request
                    try {
                        MovieDTO movieDTO = objectMapper.readValue(response.body(), MovieDTO.class);
                        return movieDTO;
                    } catch (Exception e){
                        throw new ApiException("Failed parsing API | TMDBService - getMovieInfo: ", e);
                    }

                });

    }

    // ______________________________________________________

    public CompletableFuture<List<MovieDTO>> getMoviesByRating(double minRating, double maxRating, boolean highestFirst, int page) {

        // Initial Setup
        String sortBy = highestFirst ? "vote_average.desc" : "vote_average.asc";
        String url = String.format(
                "https://api.themoviedb.org/3/discover/movie?api_key=%s&language=en-US&vote_average.gte=%.1f&vote_average.lte=%.1f&sort_by=%s&page=%d",
                apiKey, minRating, maxRating, sortBy, page
        );

        // Request
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // Async call
        return PoolConfig.getClient()
                .sendAsync(httpRequest, BodyHandlers.ofString())
                .thenApply(response -> {

                    // Validation
                    if (response.statusCode() != 200) {
                        throw new ApiException(response.statusCode(), "API returned status: " + response.statusCode());
                    }

                    // Request
                    try {
                        MovieWrapperDTO wrapper = objectMapper.readValue(response.body(), MovieWrapperDTO.class);
                        return wrapper.getResults();
                    } catch (Exception e) {
                        throw new ApiException("Failed parsing API | TMDBService - getMoviesByRating: ", e);
                    }

                });
    }

    // ______________________________________________________

    public CompletableFuture<List<MovieDTO>> getSortedByReleaseDate(String query) {

        // Query could be "avatar" for example.
        // This would give more than 1 as result, meaning we can now sort it.
        // If we just searched "Mifune" for example we'd only get 1 result. Which we can't really sort.

        // Initial setup
        String url = String.format(
                "https://api.themoviedb.org/3/search/movie?api_key=%s&query=%s&language=en-US&page=1",
                apiKey, query.replace(" ", "%20")
        );

        // Request
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // Async call
        return PoolConfig.getClient()
                .sendAsync(httpRequest, BodyHandlers.ofString())
                .thenApply(response -> {

                    // Validation
                    if (response.statusCode() != 200) {
                        throw new ApiException(response.statusCode(), "API returned status: " + response.statusCode());
                    }

                    // Call
                    try {

                        // JSON -> Java MovieWrapperDTO object
                        MovieWrapperDTO wrapper = objectMapper.readValue(response.body(), MovieWrapperDTO.class);

                        // Sort
                        List<MovieDTO> sortedMovies = wrapper.getResults()
                                .stream()
                                .sorted(Comparator.comparing(MovieDTO::getReleaseDate, Comparator.nullsLast(Comparator.reverseOrder())))
                                .collect(Collectors.toList());

                        // Return
                        return sortedMovies;

                    } catch (Exception e) {
                        throw new ApiException("Failed parsing API | TMDBService - getSortedByReleaseDate: ", e);
                    }
                });
    }

}