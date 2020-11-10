import React from "react";
import {Container} from "react-bootstrap";
import Header from "./components/Header";
import NotificationForm from "./components/NotificationForm";
import './App.css';

function App() {
    return (
        <Container>
            <Header/>
            <NotificationForm />
        </Container>
    );
}

export default App;
