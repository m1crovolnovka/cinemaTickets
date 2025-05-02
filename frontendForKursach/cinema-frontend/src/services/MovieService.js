import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:7070/movies';

export const listMovies = () => axios.get(REST_API_BASE_URL);

export const createMovie = (movie) => axios.post(REST_API_BASE_URL + '/new', movie);