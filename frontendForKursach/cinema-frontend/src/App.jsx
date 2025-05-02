import { useState } from 'react'

import './App.css'
import ListMovieComponent from './components/ListMovieComponent'
import HeaderComponent from './components/HeaderComponent'
import FooterComponent from './components/FooterComponent'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import MovieComponent from './components/MovieComponent'

function App() {


  return (
    <>
      <BrowserRouter>
        <HeaderComponent />
        <Routes>
            <Route path='/' element= {<ListMovieComponent /> }></Route>
            <Route path='/movies' element= {<ListMovieComponent /> }></Route>
            <Route path='/add-movie' element= {<MovieComponent /> }></Route>
        </Routes>
        <FooterComponent />
      </BrowserRouter>
    </>
  )
}

export default App
