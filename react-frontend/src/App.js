import './App.css';
import React from "react";
import * as Sentry from "@sentry/react";

const headers = new Headers();

headers.append('Authorization', 'Basic ' + btoa("maciej:123456"));


class Rating extends React.Component {
    render() {
        let score = "";
        if (!this.props.score) {
            score = "🤷‍♂️";
        } else {
            for (let i = 0; i < this.props.score; i++) {
                score += "⭐️&nbsp;";
            }
        }
        return (
            <span dangerouslySetInnerHTML={{__html: score}}></span>
        );
    }
}

class Movie extends React.Component {

    constructor(props, context) {
        super(props, context);
    }

    render() {
        return (
            <div>
                <h3>{this.props.movie.title}</h3>
                <div>
                    <Rating score={this.props.movie.rating}/>
                </div>
                <img style={{height: 350}} src={this.props.movie.thumbnail}/>
            </div>
        )
    }
}

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
