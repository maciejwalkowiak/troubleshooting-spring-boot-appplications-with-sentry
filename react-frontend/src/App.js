import './App.css';
import React from "react";
import * as Sentry from "@sentry/react";
import Movie from "./Movie";

const headers = new Headers();

// for the sake of simplicity, basic auth credentials are hardcoded here
headers.append('Authorization', 'Basic ' + btoa("maciej:123456"));

class MovieList extends React.Component {

    constructor(props, context) {
        super(props, context);

        this.state = {
            movies: [],
            isLoading: false
        }
    }

    loadMovies() {
        this.setState({isLoading: true})
        fetch("http://localhost:8080/movies", {credentials: 'same-origin', headers: headers})
            .then(res => res.json())
            .then(json => this.setState({
                movies: json,
                isLoading: false
            })).catch(e => this.setState({movies:[], message: '🤷‍♂️'}))
    }

    render() {
        return (
            <div>
                <h1>Movies App</h1>
                <div className="AppMenu">
                    <button onClick={() => this.loadMovies()}>Load movies</button>
                    {this.state.isLoading && <span className="Loading">Loading</span>}
                </div>
                {this.state.movies && <ul style={{display: 'flex'}}>
                    {this.state.movies.map(movie => <li><Movie movie={movie}/></li>)}
                </ul>}
            </div>);
    }
}

function App() {
    return (
        <div className="App">
            <MovieList/>
        </div>
    );
}

export default Sentry.withProfiler(App);
