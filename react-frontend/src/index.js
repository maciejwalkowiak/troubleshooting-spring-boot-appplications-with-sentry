import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
// import reportWebVitals from './reportWebVitals';
import * as Sentry from "@sentry/react";
import {Integrations} from "@sentry/tracing";


// Sentry.init({
//     // replace dsn with a dsn specific for your Sentry project
//     dsn: "https://bc24c172d95c4034bd40c3ae2e387baf@o420886.ingest.sentry.io/6000357",
//     // BrowserTracing integration turns on measuring performance
//     integrations: [new Integrations.BrowserTracing()],
//
//     // We recommend adjusting this value in production, or using tracesSampler
//     // for finer control
//     tracesSampleRate: 1.0,
// });

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
// reportWebVitals();
