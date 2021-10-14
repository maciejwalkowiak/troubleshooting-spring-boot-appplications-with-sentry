import Rating from "./Rating";
import React from "react";

const Movie = ({movie}) => {
    return (
        <div>
            <h3>{movie.title}</h3>
            <div>
                <Rating score={movie.rating}/>
            </div>
            <img style={{height: 350}} src={movie.thumbnail}/>
        </div>
    )
}

export default Movie;