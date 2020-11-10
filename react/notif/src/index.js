import React from 'react';
import ReactDOM from 'react-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import { positions, Provider } from "react-alert";
import AlertTemplate from "react-alert-template-basic";
import App from './App';
import reportWebVitals from './reportWebVitals';

const options = {
    timeout: 5000,
    position: positions.BOTTOM_CENTER
};


const Home = () => (
    <Provider template={AlertTemplate} {...options}>
        <App />
    </Provider>
);

ReactDOM.render(
  <React.StrictMode>
    <Home />
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
