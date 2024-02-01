import React from 'react';
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import Navbar from './layout/Navbar';
import AddMusic from './music/AddMusic';
import EditMusic from './music/EditMusic';
import Home from './pages/Home';

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar/>
        <Routes>
          <Route exact path="/" element={<Home/>}/>
          <Route exact path="/addmusic" element={<AddMusic/>}/>
          <Route exact path="/editmusic/:id" element={<EditMusic/>}/>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
