import React from "react";

const Rating = ({score}) => {
    let result = "";
    if (!score) {
        result = "🤷‍♂️";
    } else {
        for (let i = 0; i < score; i++) {
            result += "⭐️&nbsp;";
        }
    }
    return (
        <span dangerouslySetInnerHTML={{__html: result}}></span>
    );
}

export default Rating;